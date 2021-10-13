package com.evmtv.cloudvideo.common.presenter.video;

import android.os.Handler;
import android.widget.SeekBar;

import com.evmtv.util.view.EvmPlayerView;


public class MediaOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private int CurrProgress;
    protected EvmPlayerView playerView;
    protected Handler handler;

    public MediaOnSeekBarChangeListener(EvmPlayerView playerView, Handler handler) {
        this.playerView = playerView;
        this.handler = handler;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        CurrProgress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        playerView.seekTo(CurrProgress * playerView.getDuration() / 100);
        handler.sendEmptyMessageDelayed(VideoTimeHandler.UPDATE_PLAY_CURRENT_PROGRESS, 1000);
    }


}
