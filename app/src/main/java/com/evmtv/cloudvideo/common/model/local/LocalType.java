package com.evmtv.cloudvideo.common.model.local;

import android.view.View;

public interface LocalType {
    String LOGIN_LAYOUT_TYPE = "logout";
    String START_SYSTEM_ACTIVITY = "StartSystemActivity";
    String STRING_ACTIVITY_LOCAL = "StartLocalActivity";
    String STRING_ACTIVITY_REGISTER_LOCAL = "StartLocalRegisterActivity";
    String STRING_ACTIVITY_LOCAL_LOGIN = "StartLocalLogin";
    String STRING_UNKNOWN = "unknown";

    void use(View v,String type, String json);

    void use(View v,String type, String json, View.OnClickListener loginMode);
}
