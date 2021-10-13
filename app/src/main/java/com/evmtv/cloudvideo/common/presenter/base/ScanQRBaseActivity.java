package com.evmtv.cloudvideo.common.presenter.base;

import android.util.Log;
import android.widget.Toast;

import com.evmtv.cloudvideo.common.presenter.scan.ScanQRPresenter;
import com.evmtv.cloudvideo.common.presenter.scan.ScanQRPresenterImpl;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;


import cn.bingoogolapple.qrcode.cores.QRCodeView;
import cn.bingoogolapple.qrcode.zxings.ZXingView;

public  class ScanQRBaseActivity extends BaseActivity implements QRCodeView.Delegate {

    private String TAG = getClass().getName();
    public ScanQRPresenter presenter;

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result=" + result);
        presenter.startSpot();
        SystemUtil.getInstance(ScanQRBaseActivity.this).vibrate(200);
    }



    @Override
    public void onScanQRCodeOpenCameraError() {
        ToastUtil.setToast("打开相机出错");
    }
}
