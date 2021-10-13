package com.evmtv.cloudvideo.common.persenter.register;

import android.widget.Button;
import android.widget.TextView;

public interface RegisterModel {
    void InitDisplay();

    void showClassId();

    void setClassTextView(TextView text);

    String getClassId();

    void senMsg(String phone, Button butGetReturnViewID);

    void register(String name, String sex
            , String age, String phone
            , String password, String pwd2
            , String classid, String identifyCode
            , String sessionid);

    String getSessionid();
}
