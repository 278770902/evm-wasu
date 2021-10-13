package com.evmtv.cloudvideo.common.presenter.login;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evmtv.cloudvideo.common.model.http.LoginUserBean;

public interface LoginPresenter {
    void validateCredentials(LoginUserBean user);

    void initTextOnClick(ViewGroup view, View.OnClickListener clickListener);

    void onDestroy();

    void LoginSuccess();

    void startForgetPassword();

    void SendSMS(String phone, Button btn);
}
