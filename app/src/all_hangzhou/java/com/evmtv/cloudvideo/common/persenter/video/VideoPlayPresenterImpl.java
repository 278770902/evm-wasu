package com.evmtv.cloudvideo.common.persenter.video;

import android.os.Bundle;

import com.evmtv.cloudvideo.common.view.video.VideoPlayPresenter;

public class VideoPlayPresenterImpl implements VideoPlayPresenter {
    private Bundle bundle;

    public VideoPlayPresenterImpl(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void end(int phoneduration, int videoDuration, int playPercent) {
    }
}
