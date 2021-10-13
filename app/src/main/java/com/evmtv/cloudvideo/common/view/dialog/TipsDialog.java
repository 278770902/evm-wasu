package com.evmtv.cloudvideo.common.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

public class TipsDialog extends Dialog {
    private Activity activity;
    private Button  TipsDialogOKViewID;
    private String Tips;
    private TextView TipsContextTextViewID;
    private android.view.View.OnClickListener  cancelOkBtnClickListener;

    public TipsDialog(@NonNull Activity context, String Tips) {
        this(context, Tips, null);
    }

    public TipsDialog(@NonNull Activity context, String Tips, android.view.View.OnClickListener cancelOkBtnClickListener) {
        super(context, R.style.logOutDialogStyle);
        this.activity = context;
        this.Tips = Tips;
        this.cancelOkBtnClickListener = cancelOkBtnClickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_tip);
        ViewBindUtil.bindViews(this, getWindow().getDecorView());
        TipsContextTextViewID.setText(Tips);
        TipsDialogOKViewID.setOnClickListener(cancelOkBtnClickListener);
    }
}
