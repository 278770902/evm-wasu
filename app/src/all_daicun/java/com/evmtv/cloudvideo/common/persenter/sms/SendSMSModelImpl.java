package com.evmtv.cloudvideo.common.persenter.sms;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.http.edums.SendMsgEntity;
import com.evmtv.cloudvideo.common.presenter.sms.BaseSendSMSModelImpl;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSModel;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class SendSMSModelImpl extends BaseSendSMSModelImpl {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void dealSendMsg(String phone, Handler timerHandler, Boolean isRegisterPass) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            @Override
            public void IO() {
                if (!dealIsRegister(isRegisterPass,phone))
                    return;

                Looper.prepare();
                String json = EdumsInteractive.getInstance().sendMsg(phone);
                SendMsgEntity sendMsgEntity;
                try {
                    sendMsgEntity = JSON.parseObject(json, SendMsgEntity.class);
                } catch (Exception e) {
                    sendMsgEntity = new SendMsgEntity(-1, Text.JsonParseErrorText);
                }

                if (sendMsgEntity.getCode() == 200) {
                    sessionId = sendMsgEntity.getRows().getSessionid();
                    timerHandler.sendEmptyMessage(0);
                }
                Looper.loop();
                Log.i("litao", "json=" + json);
            }

            @Override
            public void Main() {

            }
        });
    }

    @Override
    public boolean dealIsRegister(boolean registerPass,String tel) {
//        String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
//        String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
        String json = EdumsInteractive.getInstance().findPhoneIfExist(tel);
        Object rows = null;
        try {
            JSONObject object = JSON.parseObject(json);
            String code = object.containsKey("code") ? object.getString("code") : "-1";
            String msg = object.containsKey("msg") ? object.getString("msg") : "";
            rows = object.containsKey("rows") ? object.getString("rows") : "null";
        } catch (Exception e) {
        }

        if (rows == null || rows.equals("null")) {
            XLog.i("SendSMS", "tel:" + tel + ":未注册");
            if (registerPass) {
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.setToast("帐号未注册，请先注册");
                    }
                });
                return false;
            } else {
                return true;
            }
        } else {
            XLog.i("SendSMS", "tel:" + tel + ":已注册");
            if (registerPass) {
                return true;
            } else {
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.setToast("帐号已注册");
                    }
                });
                return false;
            }
        }
    }


}
