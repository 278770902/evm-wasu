package com.evmtv.cloudvideo.common.presenter.video;

import android.os.Bundle;

import com.evmtv.cloudvideo.common.view.video.VideoPlayPresenter;

public class BaseVideoPlayPresenterImpl implements VideoPlayPresenter {
    private Bundle bundle;

    public BaseVideoPlayPresenterImpl(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void end(int phoneduration, int videoDuration, int playPercent) {

    }
}
