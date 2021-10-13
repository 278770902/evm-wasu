package com.evmtv.cloudvideo.common.presenter.sms;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.cpns.CpnsInteractive;
import com.evmtv.cloudvideo.common.model.http.cpns.StateBoolEntity;
import com.evmtv.cloudvideo.common.model.http.cpns.StateIntEntity;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSModel;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.csInteractive.cpns.CPNSInteractive;
import com.evmtv.rtc.csInteractive.cpns.entity.QueryIdentifyCodeResult;

import java.util.UUID;

public class BaseSendSMSModelImpl implements SendSMSModel {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void dealSendMsg(String phone, Handler timerHandler, Boolean isRegisterPass) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {

            QueryIdentifyCodeResult result;

            @Override
            public void IO() {
                if (!dealIsRegister(isRegisterPass, phone))
                    return;

                Looper.prepare();
//                sessionId = UUID.randomUUID().toString();
//                String json = CpnsInteractive.getInstance().queryIdentifyCode(phone, sessionId);
                result = CPNSInteractive.getInstance().queryIdentifyCode(phone, "");
                sessionId = result.getUuid();
                if (result.getResult() == BaseResult.SUCCESS) {
                    timerHandler.sendEmptyMessage(0);
                }
                Looper.loop();
            }

            @Override
            public void Main() {
                if (result == null || result.getResult() != BaseResult.SUCCESS) {
                    ToastUtil.setToast("获取验证码失败");
                }
            }
        });
    }

    @Override
    public boolean dealIsRegister(boolean registerPass, String tel) {
        String json = CpnsInteractive.getInstance().isUserRegistered(tel);
        StateIntEntity entity;
        try {
            entity = JSON.parseObject(json, StateIntEntity.class);
        } catch (Exception e) {
            entity = new StateIntEntity(-1);
        }
        if (entity.getResult() == 1) {
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
        } else {
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
        }
    }


}
