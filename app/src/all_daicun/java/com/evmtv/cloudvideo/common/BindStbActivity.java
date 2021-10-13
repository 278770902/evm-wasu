package com.evmtv.cloudvideo.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.evmtv.cloudvideo.common.persenter.bind.BindStbModel;
import com.evmtv.cloudvideo.common.persenter.bind.BindStbModelImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.view.ScanQRActivity;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SPUtilInter;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class BindStbActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backViewID, BindScanViewID;
    private Button bindStbViewBtView;
    private ListView bindStbListViewId;
    private LinearLayout BindStbContextViewID;
    private BindStbModel stbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_stb);
        initView();
    }

    private void initView() {
        stbModel = new BindStbModelImpl(BindStbContextViewID, this);
        stbModel.getBindInfo(SharedPreferencesUtil.getInstance().getUserLoginName());
        backViewID.setOnClickListener(this::onClick);
        BindScanViewID.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.BindScanViewID:
            case R.id.nullViewId:
                startActivityForResult(new Intent(BindStbActivity.this, ScanQRActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0) {
            stbModel.getBindInfo(SharedPreferencesUtil.getInstance().getUserLoginName());
            return;
        }
    }
}
