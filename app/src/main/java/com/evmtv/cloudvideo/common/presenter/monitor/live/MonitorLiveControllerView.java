package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.presenter.video.BaseMediaControllerView;
import com.evmtv.cloudvideo.common.presenter.video.VideoTimeHandler;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.util.view.EvmPlayerView;

/**
 * 内部播控
 */
public class MonitorLiveControllerView extends BaseMediaControllerView {
    private EvmPlayerView playerView;

    public MonitorLiveControllerView(Activity mContext, String title, String session) {
        super(mContext, title, session);
    }

    @Override
    public void UpdatePlayControllerView() {
    }

    @Override
    public View makeControllerView(EvmPlayerView playerView) {
        this.playerView = playerView;
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mContext.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mRootView = inflate.inflate(R.layout.media_controller_kanjiabao_landscape, null);
            MonitorLiveLandscapeViewHolder viewHolder = new MonitorLiveLandscapeViewHolder(mRootView);
            mRootView.setTag(viewHolder);
        } else {
            mRootView = inflate.inflate(R.layout.media_controller_kanjiabao_portrait, null);
            MonitorLivePortraitViewHolder viewHolder = new MonitorLivePortraitViewHolder(mRootView);
            mRootView.setTag(viewHolder);
        }
        return mRootView;
    }

    @Override
    public void stop() {
        MonitorLiveTool.getInstance(null).stopVideoPlay(this);
    }

    @Override
    public void onRestart() {
        MonitorLiveTool.getInstance(mContext).startVideoPlay(this, null);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void startPlay(String url) {
        if (playerView.isPlaying())
            return;
        if (url == null || url.length() <= 0)
            return;
        playerView.reset();
        playerView.enableKeepVideoRatio(true);
        playerView.enableLowLatency(false);
        playerView.enableRecord(true);
        playerView.enableTakeSnapshot();
        playerView.startPlay(url);
        handler = new VideoTimeHandler(this);
        handler.sendEmptyMessage(VideoTimeHandler.LIVE_MONITOR_KEEP_MESSAGE_ID);
//        NetSpeedTimer mNetSpeedTimer = new NetSpeedTimer(mContext, new NetSpeed(), new Handler()).setDelayTime(1000).setPeriodTime(2000);
//        mNetSpeedTimer.startSpeedTimer();
    }
}
