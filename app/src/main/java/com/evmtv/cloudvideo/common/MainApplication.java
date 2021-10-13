package com.evmtv.cloudvideo.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.evmtv.cloudvideo.common.presenter.application.InitPresenter;
import com.evmtv.cloudvideo.common.presenter.application.InitPresenterImpl;


public class MainApplication extends Application {

    public static InitPresenter initContext;
    public static MainApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initContext = new InitPresenterImpl(this);
        initContext.init();
    }
}
