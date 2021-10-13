package com.evmtv.cloudvideo.common.presenter.web;

import android.view.View;


public interface EWebViewPresenter {

    void attributeSetting(View loadView);

    void loadPage(String url);

    void onPause();

    void onResume();

    void onDestroy();

    boolean isNUll();

    void onBack();
}
