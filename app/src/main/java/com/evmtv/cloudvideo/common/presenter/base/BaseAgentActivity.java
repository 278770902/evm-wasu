package com.evmtv.cloudvideo.common.presenter.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.web.EWebViewPresenter;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

import org.greenrobot.eventbus.EventBus;


public class BaseAgentActivity extends BaseActivity {

    protected EWebViewPresenter presenter;
    public String title = getClass().getName();


    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null)
            presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null)
            presenter.onResume();
    }
}
