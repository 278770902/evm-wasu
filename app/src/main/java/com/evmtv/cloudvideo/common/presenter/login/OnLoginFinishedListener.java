package com.evmtv.cloudvideo.common.presenter.login;

public interface OnLoginFinishedListener {
    void onLoginError(int type, String errorString);

    void onSuccess();
}
