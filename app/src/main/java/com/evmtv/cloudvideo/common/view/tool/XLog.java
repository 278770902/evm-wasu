package com.evmtv.cloudvideo.common.view.tool;

import android.util.Log;

public class XLog {
    public static void i(String TAG, String... value) {
        Log.i(TAG, "----------------------------------------------");
        StringBuffer sb = new StringBuffer("@@");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
        }
        Log.i(TAG, sb.toString());
        sb.append("@@");
        Log.i("TAG", "----------------------------------------------");
    }

    public static void d(String TAG, String... value) {
        Log.d(TAG, "----------------------------------------------");
        StringBuffer sb = new StringBuffer("@@");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
        }
        Log.d(TAG, sb.toString());
        sb.append("@@");
        Log.d("TAG", "----------------------------------------------");
    }

    public static void e(String TAG, String... value) {
        Log.e(TAG, "----------------------------------------------");
        StringBuffer sb = new StringBuffer("@@");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
        }
        Log.e(TAG, sb.toString());
        sb.append("@@");
        Log.e("TAG", "----------------------------------------------");
    }


}
