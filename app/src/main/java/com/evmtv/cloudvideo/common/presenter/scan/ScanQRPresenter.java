package com.evmtv.cloudvideo.common.presenter.scan;

public interface ScanQRPresenter {
    String SCAN_QR_BIND = "account";
    String SCAN_QR_LOGIN = "login";
    String SCAN_QR_ADD_FRIEND = "add";
    String SCAN_QR_ADD_TO_HOME = "home";
    String SCAN_QR_ADD_TO_NOTE = "note";

    int DealResult(String result);

    void onStop();

    void onDestroy();

    void onStart();

    void startSpot();
}
