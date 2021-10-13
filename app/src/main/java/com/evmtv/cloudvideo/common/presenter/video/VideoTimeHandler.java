package com.evmtv.cloudvideo.common.presenter.video;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.utils.DateUtils;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.rtc.csInteractive.csm.CSMInteractive;


public class VideoTimeHandler extends Handler {

    public final static int UPDATE_PLAY_CURRENT_TIME = 0x234323;
    public final static int UPDATE_PLAY_CURRENT_PROGRESS = UPDATE_PLAY_CURRENT_TIME + 1;
    public final static int VIDEO_CALL_KEEP_MESSAGE_ID = UPDATE_PLAY_CURRENT_PROGRESS + 1;
    public final static int LIVE_MONITOR_KEEP_MESSAGE_ID = VIDEO_CALL_KEEP_MESSAGE_ID + 1;

    private BaseMediaControllerView context;

    public VideoTimeHandler(BaseMediaControllerView context) {
        this.context = context;
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATE_PLAY_CURRENT_TIME:
                if (context.playerView == null)
                    return;
                context.timeCurrentPlayTextViewId.setText(DateUtils.IntToStringTime(context.playerView.getCurrentPosition())
                        + "/" + DateUtils.IntToStringTime(context.playerView.getDuration()));
                Log.i("media", context.timeCurrentPlayTextViewId.getText().toString());
                context.handler.sendEmptyMessageDelayed(UPDATE_PLAY_CURRENT_TIME, 500);
                context.UpdatePlayControllerView();
                break;
            case UPDATE_PLAY_CURRENT_PROGRESS:
                if (context.playerView == null)
                    return;
                context.phoneDuration = context.playerView.getCurrentPosition();
                context.videoDuration = context.playerView.getDuration();

                int progress = (context.phoneDuration == 0
                        || context.videoDuration == 0) ? 0 : (context.phoneDuration * 100 / context.videoDuration);
                XLog.i("media", "progress:" + progress
                        , "current position:" + context.phoneDuration
                        , "duration:" + context.videoDuration);
                context.kanJiaBaoSeekBarViewId.setProgress(progress);
                context.handler.sendEmptyMessageDelayed(UPDATE_PLAY_CURRENT_PROGRESS, 500);
                context.playPercent = progress;
                break;
            case VIDEO_CALL_KEEP_MESSAGE_ID:
                if (context.session == null || context.session.length() <= 0)
                    return;
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        CSMInteractive.getInstance().keepVideoCall(SharedPreferencesUtil.getInstance().getUserGuid(true), context.session);
                    }
                });
                context.handler.sendEmptyMessageDelayed(VIDEO_CALL_KEEP_MESSAGE_ID, 10000);
                break;
            case LIVE_MONITOR_KEEP_MESSAGE_ID:
                if (context.session == null || context.session.length() <= 0)
                    return;
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        CsmInteractive.getInstance().keep(context.session);
                    }
                });
                context.handler.sendEmptyMessageDelayed(LIVE_MONITOR_KEEP_MESSAGE_ID, 10000);
                break;

        }
    }
}
