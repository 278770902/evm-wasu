package com.evmtv.cloudvideo.common.utils.toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.evmtv.cloudvideo.common.MainApplication;

public class ToastUtil {
    private static Toast toast = null;

    public static void setToast(String hint) {
        if (toast == null) {
            toast = Toast.makeText(MainApplication.initContext.getMainApplicationContext(), hint, Toast.LENGTH_SHORT);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
