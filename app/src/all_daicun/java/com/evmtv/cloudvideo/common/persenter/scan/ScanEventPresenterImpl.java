package com.evmtv.cloudvideo.common.persenter.scan;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.edums.BoosEntity;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.http.edums.SendMsgEntity;
import com.evmtv.cloudvideo.common.presenter.scan.BaseScanEventPresenterImpl;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class ScanEventPresenterImpl extends BaseScanEventPresenterImpl {
    private Context context;

    public ScanEventPresenterImpl(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void BindStb(String card) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            BoosEntity entity;

            @Override
            public void IO() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                String json = EdumsInteractive.getInstance().boosBinCard(SharedPreferencesUtil.getInstance().getUserLoginName(), card, "",tel,ssoToken);
                try {
                    entity = JSON.parseObject(json, BoosEntity.class);
                } catch (Exception e) {
                    entity = new BoosEntity(-1);
                }

            }

            @Override
            public void Main() {
                if (entity.getCode() != 200) {
                    ToastUtil.setToast("绑定失败");
                    return;
                }
                String row = entity.getRows();
                JSONObject jsonObject = JSON.parseObject(row);
                String code = jsonObject.containsKey("code") ? jsonObject.getString("code") : "-1";
                switch (code) {
                    case "00":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "绑定成功");
                        break;
                    case "01":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "绑定失败:无智能卡信息，该功能暂未完成，请前端暂做预留");
                        break;
                    case "02":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "绑定失败:手机已经绑定超过一次");
                        break;
                    case "03":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "绑定失败:智能卡已经绑定超过两次");
                        break;
                    case "98":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "绑定失败:数据库插入绑定数据异常");
                        break;
                    case "99":
                        ToastUtil.setToast(jsonObject.containsKey("message") ? jsonObject.getString("message") : "绑定失败:绑定时发生其他异常");
                        break;
                }
            }
        });


    }
}

