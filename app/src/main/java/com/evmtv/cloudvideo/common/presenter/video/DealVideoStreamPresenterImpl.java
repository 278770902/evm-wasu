package com.evmtv.cloudvideo.common.presenter.video;

import android.content.Context;

import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class DealVideoStreamPresenterImpl implements DealVideoStreamPresenter {

    private BaseMediaControllerView context;

    public DealVideoStreamPresenterImpl(BaseMediaControllerView context) {
        this.context = context;
    }

    @Override
    public void ipcStopPlay() {
        if (context.session == null || context.session.isEmpty())
            return;
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                CsmInteractive.getInstance().evmMediaStopPlay(context.session);
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        context.playerView.stopPlay();
                    }
                });
                context.session = null;
            }
        });
    }

    public void FastForward(int valueTime) {
        if (context.playerView.isPlaying()) {
            int time = context.playerView.getCurrentPosition() + valueTime;
            if (time >= context.playerView.getDuration()) {
                context.playerView.seekTo(context.playerView.getDuration());
            } else {
                context.playerView.seekTo(time);
            }
        }
    }

    public void GoBack(int valueTime) {
        if (context.playerView.isPlaying()) {
            int time = context.playerView.getCurrentPosition() - valueTime;
            if (time >= 0) {
                context.playerView.seekTo(time);
            } else {
                context.playerView.seekTo(0);
            }
        }
    }


}
