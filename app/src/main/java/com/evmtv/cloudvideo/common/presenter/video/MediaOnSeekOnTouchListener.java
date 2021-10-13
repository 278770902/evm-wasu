package com.evmtv.cloudvideo.common.presenter.video;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;



public class MediaOnSeekOnTouchListener implements View.OnTouchListener {

    private Handler handler;

    public MediaOnSeekOnTouchListener(Handler handler) {
        this.handler = handler;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeMessages(VideoTimeHandler.UPDATE_PLAY_CURRENT_PROGRESS);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
