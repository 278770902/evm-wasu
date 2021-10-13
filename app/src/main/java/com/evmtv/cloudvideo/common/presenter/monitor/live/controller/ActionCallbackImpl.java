package com.evmtv.cloudvideo.common.presenter.monitor.live.controller;

import android.util.Log;

import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.presenter.monitor.live.CircleViewByImage;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveControllerView;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveEntity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class ActionCallbackImpl implements CircleViewByImage.ActionCallback {
    private boolean isCircleViewActionUp = true;
    MonitorLiveControllerView controllerView;

    public ActionCallbackImpl(MonitorLiveControllerView controllerView) {
        this.controllerView = controllerView;
    }

    @Override
    public void forwardMove() {
        controllerView.removeCallbacksFadeOut();
        if (isCircleViewActionUp) {
            ipcPtzCtrl("up");
            Log.i("CircleViewByImage", "----up");
            isCircleViewActionUp = false;
        }
    }

    @Override
    public void backMove() {
        controllerView.removeCallbacksFadeOut();
        if (isCircleViewActionUp) {
            ipcPtzCtrl("down");
            Log.i("CircleViewByImage", "----down");
            isCircleViewActionUp = false;
        }
    }

    @Override
    public void leftMove() {
        controllerView.removeCallbacksFadeOut();
        if (isCircleViewActionUp) {
            ipcPtzCtrl("left");
            isCircleViewActionUp = false;
            Log.i("CircleViewByImage", "----left");
        }
    }

    @Override
    public void rightMove() {
        controllerView.removeCallbacksFadeOut();
        if (isCircleViewActionUp) {
            ipcPtzCtrl("right");
            Log.i("CircleViewByImage", "----right");
            isCircleViewActionUp = false;
        }
    }

    @Override
    public void centerMove() {
        controllerView.removeCallbacksFadeOut();
        Log.i("CircleViewByImage", "----centerMove");
    }

    @Override
    public void centerClick() {
        controllerView.removeCallbacksFadeOut();
        Log.i("CircleViewByImage", "----centerClick");
    }

    @Override
    public void actionUp() {
        ipcPtzCtrl("stop");
        isCircleViewActionUp = true;
        controllerView.postDelayedFadeOut();
        Log.i("CircleViewByImage", "----actionUp");
    }


    private void ipcPtzCtrl(String action) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                CsmInteractive.getInstance().ptzCtrl(MonitorLiveEntity.getInstance().getSerialNum(), action);
            }
        });
    }
}
