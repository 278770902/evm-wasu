package com.evmtv.cloudvideo.common.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.guide.GuidePresenter;
import com.evmtv.cloudvideo.common.presenter.guide.GuidePresenterImpl;

public class SCGuideActivity extends BaseActivity {

    private ViewPager vpGuide;
    private GuidePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scguide);
        initView();
    }

    private void initView() {
        presenter = new GuidePresenterImpl(this);
        presenter.InitDisplay(vpGuide);
    }
}
