package com.evmtv.cloudvideo.common.view.tool;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.evmtv.cloudvideo.common.persenter.sms.SendSMSModelImpl;
import com.evmtv.cloudvideo.common.presenter.sms.BaseSendSMSModelImpl;

public class SendSMSUtils {

    private Button btn;
    private int sendMsgTime = 60;
    private String sessionId;
    private android.os.Handler timerHandler;

    private static SendSMSUtils instance;
    private SendSMSModel sendSMSModel;

    public static SendSMSUtils getInstance() {
        synchronized (SendSMSUtils.class) {
            if (instance == null)
                instance = new SendSMSUtils();
        }
        return instance;
    }

    public String getSessionId() {
        if (sendSMSModel == null)
            return null;
        return sendSMSModel.getSessionId();
    }

    public void senMsgCode(String phone, Button btn, Boolean isRegisterPass) {
        this.btn = btn;
        createHandler();
        sendMsgTime = sendMsgTime == 0 ? 60 : sendMsgTime;
        sendSMSModel = new SendSMSModelImpl();
        if (sendMsgTime == 60) {
            sendSMSModel.dealSendMsg(phone, timerHandler,isRegisterPass);
        }
    }


    private void createHandler() {
        timerHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (btn != null) {
                    if (sendMsgTime == 0) {
                        btn.setText("获取验证码");
                        return;
                    }
                    btn.setText(sendMsgTime + "");
                    timerHandler.sendEmptyMessageDelayed(0, 1000);
                    sendMsgTime--;
                }
            }
        };
    }
}
