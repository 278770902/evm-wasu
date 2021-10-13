package com.evmtv.cloudvideo.common.presenter.scan;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.http.bindSTBUserEntity;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.AgentWebActivity;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BaseScanEventPresenterImpl implements ScanEventPresenter {
    private Context context;

    public BaseScanEventPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void startIntent(String action, IntentLocalBean bean) {
        Intent intent = new Intent();
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        intent.putExtra(AgentWebActivity.EXTRA_VALUE, bean);
        intent.setAction(action);
        context.startActivity(intent);
    }


    @Override
    public void addFriend(String GUID, String Name) {
        try {
            Name = URLDecoder.decode(Name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UMSInteractive.getInstance().applyFriend(GUID);
    }

    @Override
    public void login(String token) {

    }

    @Override
    public void bindUser(String GUID) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            bindSTBUserEntity entity;

            @Override
            public void IO() {
                String json = UMSInteractive.getInstance().bindUser(GUID);
                try {
                    entity = JSON.parseObject(json, bindSTBUserEntity.class);
                } catch (Exception e) {
                    entity = new bindSTBUserEntity(-1);
                    XLog.e("bindUser",e.getMessage());
                }
            }

            @Override
            public void Main() {
                switch (entity.getStatus()) {
                    case 1:
                        ToastUtil.setToast("机顶盒编号错误");
                        break;
                    case 2:
                        ToastUtil.setToast("非机顶盒编号错误");
                        break;
                    case 3:
                        ToastUtil.setToast("机顶盒已经和其他用户绑定");
                        break;
                    case 4:
                        ToastUtil.setToast("非机顶盒用户已和其他用户绑定");
                        break;
                    case -1:
                        ToastUtil.setToast("访问失败或系统错误");
                        break;
                    case 0:
                        //成功
                        break;
                }
            }
        });

    }

    @Override
    public void BindStb(String card) {
    }

}
