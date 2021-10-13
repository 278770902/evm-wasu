package com.evmtv.cloudvideo.common.service;

import android.content.Context;

import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.rtc.ERTCCommand;

public class ErtcSdkTool {
    private static ErtcSdkTool instance;
    private Context mContext;
    private String GUID;

    private ErtcSdkTool(Context context) {
        this.mContext = context;
        GUID = SharedPreferencesUtil.getInstance().getUserGuid(false);
    }

    public synchronized static ErtcSdkTool getInstance(Context context) {
        synchronized (AppUtils.class) {
            if (instance == null && context != null)
                instance = new ErtcSdkTool(context);
        }
        return instance;
    }

    public void startGetNotification() {
        if (GUID == null || GUID.intern().length() == 0) return;
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                ERTCCommand.getInstance().mobile.common.login(SharedPreferencesUtil.getInstance().getUserLoginName()
                        , SharedPreferencesUtil.getInstance().getUserLoginPassWorld()
                        , null);
            }
        });
    }
}
