package com.evmtv.cloudvideo.common.persenter.scan;

import android.content.Context;

import com.evmtv.cloudvideo.common.presenter.scan.BaseScanEventPresenterImpl;

public class ScanEventPresenterImpl extends BaseScanEventPresenterImpl {
    private Context context;

    public ScanEventPresenterImpl(Context context) {
        super(context);
        this.context = context;
    }
}

