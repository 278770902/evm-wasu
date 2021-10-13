package com.evmtv.cloudvideo.common.view.tool;

import android.os.Handler;

public interface SendSMSModel {

    void dealSendMsg(String phone, Handler timerHandler,Boolean isRegisterPass);

    boolean dealIsRegister(boolean registerPass,String tel);

    String getSessionId();
}
