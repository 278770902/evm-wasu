package com.evmtv.cloudvideo.common.presenter.wechat.call.log;


import android.os.Handler;
import android.os.Message;


public class CallLogHandler extends Handler {

    public static final int FIRST_NO = 0, LOAD_MORE_NO = 1, REFRESH_NO = 2;
    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_FAIL = 1;

    private CallLogHandlerPresenter callLogPresenter;

    public CallLogHandler(CallLogHandlerPresenter callLogPresenter) {
        this.callLogPresenter = callLogPresenter;
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case FIRST_NO:
            case LOAD_MORE_NO:
            case REFRESH_NO:
                callLogPresenter.ExtractDataAdapter(msg.what, msg.arg1 == RESULT_SUCCESS, msg.obj);
                break;
        }
    }

}
