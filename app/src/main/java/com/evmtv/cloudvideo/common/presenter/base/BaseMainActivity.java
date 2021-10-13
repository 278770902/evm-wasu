package com.evmtv.cloudvideo.common.presenter.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.evmtv.cloudvideo.common.utils.fir.UpdateFunGO;

public class BaseMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateFunGO.init(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }
}
