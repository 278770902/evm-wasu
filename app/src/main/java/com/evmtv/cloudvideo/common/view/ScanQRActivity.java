package com.evmtv.cloudvideo.common.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.base.ScanQRBaseActivity;
import com.evmtv.cloudvideo.common.presenter.scan.ScanQRPresenter;
import com.evmtv.cloudvideo.common.presenter.scan.ScanQRPresenterImpl;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.utils.permissions.PermissionsUtils;
import com.evmtv.cloudvideo.common.utils.picker.PickerPictureTool;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

import cn.bingoogolapple.qrcode.cores.QRCodeView;
import cn.bingoogolapple.qrcode.zxings.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxings.ZXingView;

public class ScanQRActivity extends ScanQRBaseActivity implements View.OnClickListener, PermissionsUtils.IPermissionsResult {
    public ZXingView ZXViewID;
    private ImageView backViewID, shareQRCodeViewID;
    private String TAG = getClass().getName();
    private String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_layout);
        PermissionsUtils.getInstance().checkPermissions(this, permissions, this);
        initView();
    }

    public void initView() {
        ZXViewID.setDelegate(this);
        presenter = new ScanQRPresenterImpl(this, ZXViewID);
        backViewID.setOnClickListener(this);
        shareQRCodeViewID.setOnClickListener(this);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        int code = presenter.DealResult(result);
        super.onScanQRCodeSuccess(result);
        setResult(code);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.shareQRCodeViewID:
                PickerPictureTool.getInstance(this).initMultiMode(1).start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<ImageItem> items = PickerPictureTool.getInstance(null).onActivityResult(requestCode, resultCode, data);
        XLog.i("ScanQRActivity", "----");
        if (items.size() == 0) {
            ToastUtil.setToast("图片路径无效");
            return;
        }
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            String result = null;

            @Override
            public void IO() {
                result = QRCodeDecoder.syncDecodeQRCode(items.get(0).path);
            }

            @Override
            public void Main() {
                if (null != result) {
                    ZXViewID.stopSpot();
                    onScanQRCodeSuccess(result);
                } else {
                    onScanQRCodeSuccess("无法识别");
                }
            }
        });
    }

    @Override
    public void passPermissions() {
    }

    @Override
    public void forbidPermissions() {
    }
}
