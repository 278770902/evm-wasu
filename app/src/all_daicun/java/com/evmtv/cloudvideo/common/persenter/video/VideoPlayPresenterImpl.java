package com.evmtv.cloudvideo.common.persenter.video;

import android.os.Bundle;

import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.video.VideoPlayPresenter;

public class VideoPlayPresenterImpl implements VideoPlayPresenter {
    private Bundle bundle;

    public VideoPlayPresenterImpl(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void end(int phoneduration, int videoDuration, int playPercent) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                if (bundle == null)
                    return;
                String playName = bundle.getString("playName");
                String playNameID = bundle.getString("playNameID");
                String playColumn = bundle.getString("playColumn");
                String playColumnID = bundle.getString("playColumnID");
                String stbNumber = bundle.getString("stbNumber");
                EdumsInteractive.getInstance().addPlayHistory(phoneduration + "", SharedPreferencesUtil.getInstance().getUserGuid(false));
                EdumsInteractive.getInstance().addPlayRecord(playName, playNameID, playColumn, playColumnID, playPercent, 1, videoDuration,
                        SharedPreferencesUtil.getInstance().getUserLoginName()
                        , stbNumber, SharedPreferencesUtil.getInstance().getUserGuid(false));
            }
        });
    }
}
