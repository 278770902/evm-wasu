package com.evmtv.cloudvideo.common.view.tool.evm.play;

import android.view.View;

import com.evmtv.util.view.EvmPlayerView;

public interface MediaControllerInterface {
    void hide();

    void show();

    boolean isShowing();

    /**
     * @return 播控总布局
     */
    View getRootView();

    /**
     * 播控初始化
     *
     * @param playerView
     * @return
     */
    View makeControllerView(EvmPlayerView playerView);


    void delayedShow();


    void stop();

    void onRestart();

    void onDestroy();

    void startPlay(String url);
}
