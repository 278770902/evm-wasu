package com.evmtv.cloudvideo.common.presenter.login;

import com.evmtv.cloudvideo.common.model.http.LoginUserBean;

public interface LoginModel {
    void login(LoginUserBean user, OnLoginFinishedListener listener);

    void saveUserInfoIO(String loginName, String loginPassWorld);
}
