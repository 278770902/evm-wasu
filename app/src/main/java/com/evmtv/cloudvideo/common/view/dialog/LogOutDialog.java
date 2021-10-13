package com.evmtv.cloudvideo.common.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class LogOutDialog extends Dialog implements View.OnClickListener {

    private String title;
    private Activity activity;
    private TextView loginOutContextTextViewID;
    private Button loginOutDialogCancelViewID, loginOutDialogOKViewID;


    public LogOutDialog(Activity context) {
        super(context, R.style.logOutDialogStyle);
        this.activity = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login_out);
        ViewBindUtil.bindViews(this, getWindow().getDecorView());
        String GUID = SharedPreferencesUtil.getInstance().getUserGuid(false);
        String title = ReadLocalJsonFile.logOutTitle();
        String[] split = title.split("&&");
        if (GUID.isEmpty())
            loginOutContextTextViewID.setText(split[0]);
        else
            loginOutContextTextViewID.setText(split[1]);
        loginOutDialogCancelViewID.setOnClickListener(this);
        loginOutDialogOKViewID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.loginOutDialogCancelViewID:
                break;
            case R.id.loginOutDialogOKViewID:
                AppUtils.startLogin(activity);
                break;
        }
    }


}
