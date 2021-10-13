package com.evmtv.cloudvideo.common.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.qr.QRPresenter;
import com.evmtv.cloudvideo.common.presenter.qr.QRPresenterImpl;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.CodeUtils;
import com.google.zxing.WriterException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MyQRCodeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backViewID, RQCodeViewID;
    private TextView titleViewID;
    private Button shareQRCodeViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrcode);
        initView();
    }

    private void initView() {
        shareQRCodeViewID.setVisibility(View.GONE);
        backViewID.setOnClickListener(this);
        QRPresenter qrPresenter = new QRPresenterImpl(this);
        qrPresenter.InitDisplay(RQCodeViewID);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
        }
    }
}
