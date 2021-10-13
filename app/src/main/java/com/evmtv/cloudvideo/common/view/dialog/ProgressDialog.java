package com.evmtv.cloudvideo.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.view.EvmProgress;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

public class ProgressDialog extends Dialog {
    EvmProgress ProgressDialogViewId;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.progressDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);
        ViewBindUtil.bindViews(this, getWindow().getDecorView());
    }

    @Override
    public void show() {
        super.show();
        if (ProgressDialogViewId != null)
            ProgressDialogViewId.setVisibility(View.VISIBLE);
    }
}
