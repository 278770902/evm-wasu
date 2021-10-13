package com.evmtv.cloudvideo.common.presenter.base;
import com.evmtv.cloudvideo.common.presenter.video.BaseMediaControllerView;
public class BaseVideoActivity extends BaseActivity {
    protected BaseMediaControllerView mediaInter;

    @Override
    protected void onStop() {
        super.onStop();
        mediaInter.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaInter.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaInter.onDestroy();
    }
}
