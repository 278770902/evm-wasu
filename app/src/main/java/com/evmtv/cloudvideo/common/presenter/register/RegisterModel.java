package com.evmtv.cloudvideo.common.presenter.register;

import android.widget.Button;
import android.widget.TextView;

public interface RegisterModel {
    void InitDisplay();


    void senMsg(String phone, Button butGetReturnViewID);

    void register(String userName
            , String TEL, String password
            , String uuid, String identifyCode
            , String regionId);

    String getSessionId();
}
