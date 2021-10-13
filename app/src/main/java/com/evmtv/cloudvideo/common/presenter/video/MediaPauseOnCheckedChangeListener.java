package com.evmtv.cloudvideo.common.presenter.video;

import android.widget.CompoundButton;

import com.evmtv.util.view.EvmPlayerView;

public class MediaPauseOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

    private EvmPlayerView playerView;

    public MediaPauseOnCheckedChangeListener(EvmPlayerView playerView) {
        this.playerView = playerView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (playerView == null)
            return;
        if (playerView.isPlaying())
            playerView.pause();
        else
            playerView.resume();
    }
}
