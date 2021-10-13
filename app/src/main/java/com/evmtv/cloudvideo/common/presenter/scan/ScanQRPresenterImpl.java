package com.evmtv.cloudvideo.common.presenter.scan;

import android.app.Activity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.persenter.scan.ScanEventPresenterImpl;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.Validator;
import com.evmtv.cloudvideo.common.view.action.ActionName;

import cn.bingoogolapple.qrcode.cores.QRCodeView;

public class ScanQRPresenterImpl implements ScanQRPresenter {
    private QRCodeView QRViewID;
    private String TAG = getClass().getName();
    private ScanEventPresenter presenter;

    public ScanQRPresenterImpl(Activity activity, QRCodeView QRViewID) {
        presenter = new ScanEventPresenterImpl(activity);
        this.QRViewID = QRViewID;

    }

    @Override
    public int DealResult(String result) {
        if (result == null || result.length() == 0)
            return -1;

        Log.i(TAG, result);
        if (Validator.isUrl(result)) {
            presenter.startIntent(ActionName.ACTIVITY_ACTION_AGENT_WEB, new IntentLocalBean(result, null, null));
            return 0;
        }
        if (!JSONLocalObject.isJSON(result))
            return -1;
        JSONObject object = JSON.parseObject(result);
        if (object.containsKey("type")) {
            ContainsType(object);
            return 0;
        }

        if (object.containsKey("cardno")) {
            ContainsCardNo(object);
            return 0;
        }


        return -1;
    }

    public void onStop() {
        QRViewID.stopCamera();
    }

    public void onDestroy() {
        QRViewID.onDestroy();
    }

    public void onStart() {
        QRViewID.startCamera();
        QRViewID.showScanRect();
        QRViewID.startSpot();
    }

    @Override
    public void startSpot() {
        QRViewID.startSpot();
    }


    private void ContainsCardNo(JSONObject object) {
        String cardno = object.containsKey("cardno") ? object.getString("cardno") : null;
        presenter.BindStb(cardno);
    }


    private void ContainsType(JSONObject object) {
        String type = object.containsKey("type") ? object.getString("type") : null;
        String GUID = object.containsKey("GUID") ? object.getString("GUID") : null;
        String token = object.containsKey("token") ? object.getString("token") : null;
        String name = object.containsKey("name") ? object.getString("name") : null;
        switch (type) {
            case SCAN_QR_BIND:
                presenter.bindUser(GUID);
                break;
            case SCAN_QR_LOGIN:
                presenter.login(token);
                break;
            case SCAN_QR_ADD_FRIEND:
                presenter.addFriend(GUID, name);
                break;
            case SCAN_QR_ADD_TO_HOME:
                break;
            case SCAN_QR_ADD_TO_NOTE:
                break;
        }
        return;
    }
}
