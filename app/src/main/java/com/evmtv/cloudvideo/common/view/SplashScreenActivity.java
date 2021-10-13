package com.evmtv.cloudvideo.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.splash.SplashScreenPresenter;
import com.evmtv.cloudvideo.common.presenter.splash.SplashScreenPresenterImpl;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class SplashScreenActivity extends BaseActivity {

    private ImageView imgSplashViewID;
    private SplashScreenPresenter screenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    private void initValue() {
        screenPresenter = new SplashScreenPresenterImpl();
        if (SharedPreferencesUtil.getInstance().getFirstOpen() && ReadLocalJsonFile.getGuideSplashBg() != null) {
            startActivity(new Intent(this, SCGuideActivity.class));
            return;
        }
        screenPresenter.StartNext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initValue();
    }
}
