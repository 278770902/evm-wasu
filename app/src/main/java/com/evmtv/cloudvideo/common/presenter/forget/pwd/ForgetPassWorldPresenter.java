package com.evmtv.cloudvideo.common.presenter.forget.pwd;

import android.widget.Button;

public interface ForgetPassWorldPresenter {
    void senMsgCode(String phone, Button butGetReturnViewID);

    void sendForgetPassWorldHttp(String tel, String code, String pwd1, String pwd2);
}
