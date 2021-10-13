package com.evmtv.cloudvideo.common.presenter.monitor.live.controller;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.evmtv.cloudvideo.common.presenter.monitor.live.CircleViewByImage;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveControllerView;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveTool;

public class CircleViewTool implements View.OnTouchListener {
    CircleViewByImage circleViewByImage;


    private static CircleViewTool instance;

    public static CircleViewTool getInstance() {
        synchronized (CircleViewTool.class) {
            if (instance == null)
                instance = new CircleViewTool();
        }
        return instance;
    }

    public void initView(MonitorLiveControllerView controllerView,CircleViewByImage circleViewByImage){
        circleViewByImage.setCallback(new ActionCallbackImpl(controllerView));
        circleViewByImage.setLongClickable(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.i("litao", "ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("litao", "ACTION_MOVE");
                break;
        }
        return false;
    }
}
