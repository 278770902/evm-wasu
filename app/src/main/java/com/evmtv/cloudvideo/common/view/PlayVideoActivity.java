package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;
import android.view.KeyEvent;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.persenter.video.VideoPlayPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseVideoActivity;
import com.evmtv.cloudvideo.common.presenter.video.MediaControllerView;
import com.evmtv.cloudvideo.common.presenter.video.BaseVideoPlayPresenterImpl;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;
import com.evmtv.cloudvideo.common.view.video.VideoPlayPresenter;

public class PlayVideoActivity extends BaseVideoActivity {
    private EvmControllerPlayerViewA videoViewID;
    private String playUrl, title, session;
    private Bundle bundle;
    private VideoPlayPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        initData();
        initVideoPlay();
    }

    private void initVideoPlay() {
        presenter = new VideoPlayPresenterImpl(bundle);
        mediaInter = new MediaControllerView(this, title, session);
        videoViewID.setMediaController(mediaInter);
        mediaInter.startPlay(playUrl);
    }

    /**
     * playUrl:http://www.bndtn.com:28088/vod/EVMTV_EVMTV_MOVIE-844FE306BD-20201022104642.m3u8?uid=123456&stype=app&sig=
     */
    private void initData() {
        playUrl = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        session = getIntent().getStringExtra("session");
        bundle = getIntent().getExtras();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.end(mediaInter.getPhoneDuration() / (1000 * 60), mediaInter.getVideoDuration() / 1000, mediaInter.getPlayPercent());
        videoViewID.release();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (SystemUtil.onKeyDown(this, keyCode, event))
            return true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mediaInter.stop();
            PlayVideoActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
