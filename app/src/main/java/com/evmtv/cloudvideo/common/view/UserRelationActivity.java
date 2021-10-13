package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.bind.BindPresenter;
import com.evmtv.cloudvideo.common.presenter.bind.BindPresenterImpl;

public class UserRelationActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView userRelationRecViewID;
    private ImageView backViewID, shareQRCodeViewID;
    private String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_relation);
        initView();
    }

    private void initView() {
        backViewID.setOnClickListener(this);
        shareQRCodeViewID.setOnClickListener(this);
        BindPresenter bindPresenter = new BindPresenterImpl();
        bindPresenter.InitDisplay(userRelationRecViewID, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.shareQRCodeViewID:
                break;
        }
    }
}
