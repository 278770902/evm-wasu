package com.evmtv.cloudvideo.common.presenter.login;

public interface LoginControllerView {
    //login是个耗时操作，我们需要给用户一个友好的提示，一般就是操作ProgressBar
    void showProgress();

    void hideProgress();

    //login当然存在登录成功与失败的处理，失败给出提示
    void setLoginError(int type, String errorString);

    //login成功，也给个提示
    void showSuccess();
}
