package com.evmtv.cloudvideo.common.persenter.bind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.BindStbActivity;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.adapter.BindStbAdapter;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.edums.BoosEntity;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

//http://www.bndtn.com:8564/EDUMS/edumsStudentInterface/boosCheckphone?phone=13072125607&requestURL=http://10.182.0.200:2020/api/edu/checkphone
public class BindStbModelImpl implements BindStbModel, BindStbAdapter.UnBindOnClickListener {

    private LinearLayout BindStbContextViewID;
    private Context mContext;

    public BindStbModelImpl(LinearLayout bindStbContextViewID, Context mContext) {
        BindStbContextViewID = bindStbContextViewID;
        this.mContext = mContext;
    }

    @Override
    public void getBindInfo(String value) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            private String card;

            @Override
            public void IO() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                String json = EdumsInteractive.getInstance().boosCheckphone(value, tel, ssoToken);
                XLog.e("bind", "json=" + json);
                card = extractCard(json);
            }

            @Override
            public void Main() {
                if (card == null) {
                    showNullView();
                    return;
                }
                showBindInfoView(card);
            }
        });
    }


    private void showNullView() {
        BindStbContextViewID.removeAllViews();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bind_stb_null, null);
        view.findViewById(R.id.nullViewId).setOnClickListener((BindStbActivity) mContext);
        BindStbContextViewID.addView(view);
    }

    private void showBindInfoView(String card) {
        BindStbContextViewID.removeAllViews();
        ListView listView = new ListView(mContext);
        BindStbContextViewID.addView(listView);
        BindStbAdapter adapter = new BindStbAdapter(card, mContext);
        listView.setAdapter(adapter);
        adapter.setUnBindOnClickListener(this);
    }


    private String extractCard(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        if (!jsonObject.containsKey("code")) {
            return null;
        }
        if (jsonObject.getInteger("code") != 200)
            return null;
        String value = json.substring(json.indexOf("[") + 1, json.indexOf("]"));
        value = value.replaceAll("\"", "");
        value = value.replaceAll("\\\\", "");
        String[] key = value.split(",");
        if ("null".equals(key[0]))
            return null;
        return key[0];
    }

    @Override
    public void onClick(String card) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            BoosEntity entity;

            @Override
            public void IO() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                String result = EdumsInteractive.getInstance().boosReBinCard(SharedPreferencesUtil.getInstance().getUserLoginName(), card, "", tel, ssoToken);
                try {
                    entity = JSON.parseObject(result, BoosEntity.class);
                } catch (Exception e) {
                    entity = new BoosEntity(-1);
                }

            }

            @Override
            public void Main() {
                getBindInfo(SharedPreferencesUtil.getInstance().getUserLoginName());
                if (entity.getCode() != 200) {
                    ToastUtil.setToast("解绑失败");
                    return;
                }
                String row = entity.getRows();
                JSONObject jsonObject = JSON.parseObject(row);
                String code = jsonObject.containsKey("code") ? jsonObject.getString("code") : "-1";
                switch (code) {
                    case "04":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "解绑成功");
                        break;
                    case "95":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "解绑失败:系统无绑相关绑定信息");
                        break;
                    case "97":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "解绑失败:解绑时数据库操作失败");
                        break;
                }
            }
        });
    }
}
