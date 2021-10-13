package com.evmtv.cloudvideo.common.presenter.video;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.view.tool.CheckBoxTool;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.util.view.EvmPlayerView;

public class MediaControllerView extends BaseMediaControllerView implements View.OnClickListener {
    private DealVideoStreamPresenter presenter;

    public MediaControllerView(Activity mContext, String title, String session) {
        super(mContext, title, session);
    }

    @Override
    public View makeControllerView(EvmPlayerView playerView) {
        this.playerView = playerView;
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflate.inflate(R.layout.item_media_controller, null);
        initView();
        return mRootView;
    }

    private void initView() {
        MediaPauseImageViewId = mRootView.findViewById(R.id.MediaPauseImageViewId);
        MediaVoiceImageViewId = mRootView.findViewById(R.id.MediaVoiceImageViewId);
        kanJiaBaoSeekBarViewId = mRootView.findViewById(R.id.kanJiaBaoSeekBarViewId);
        timeCurrentPlayTextViewId = mRootView.findViewById(R.id.timeCurrentPlayTextViewId);
        MediaFastForwardImageViewId = mRootView.findViewById(R.id.MediaFastForwardImageViewId);
        MediaGoBackImageViewId = mRootView.findViewById(R.id.MediaGoBackImageViewId);
        MediaControllerTopBackViewID = mRootView.findViewById(R.id.MediaControllerTopBackViewID);
        MediaControllerTopBackViewID.setImageResource(R.drawable.svg_close);
        MediaFastForwardImageViewId.setImageResource(R.drawable.svg_video_fast_forward);
        MediaGoBackImageViewId.setImageResource(R.drawable.svg_video_fast_back);

        MediaGoBackImageViewId.setOnClickListener(this);
        MediaFastForwardImageViewId.setOnClickListener(this);
        MediaControllerTopBackViewID.setOnClickListener(this);
        presenter = new DealVideoStreamPresenterImpl(this);

        handler = new VideoTimeHandler(this);
        initDisplayVoice(MediaVoiceImageViewId);
        initDisplayMediaPause(MediaPauseImageViewId);
        initDisplaySeekBar(kanJiaBaoSeekBarViewId);

        UpdatePlayControllerView();
    }


    @Override
    public void stop() {
        handler.removeMessages(VideoTimeHandler.UPDATE_PLAY_CURRENT_TIME);
        handler.removeMessages(VideoTimeHandler.UPDATE_PLAY_CURRENT_PROGRESS);
        handler.removeMessages(VideoTimeHandler.VIDEO_CALL_KEEP_MESSAGE_ID);
    }

    @Override
    public void onRestart() {
        handler.sendEmptyMessage(VideoTimeHandler.UPDATE_PLAY_CURRENT_TIME);
        handler.sendEmptyMessage(VideoTimeHandler.UPDATE_PLAY_CURRENT_PROGRESS);
        handler.sendEmptyMessage(VideoTimeHandler.VIDEO_CALL_KEEP_MESSAGE_ID);
    }

    @Override
    public void onDestroy() {
        presenter.ipcStopPlay();
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
        playerView.startPlay(url);

        handler = new VideoTimeHandler(this);
        handler.sendEmptyMessage(VideoTimeHandler.UPDATE_PLAY_CURRENT_TIME);
        handler.sendEmptyMessage(VideoTimeHandler.UPDATE_PLAY_CURRENT_PROGRESS);
        XLog.i("media", "video play url:" + url);
    }


    public void UpdatePlayControllerView() {
        CheckBoxTool.resumePlayCompoundButton(MediaVoiceImageViewId, SystemUtil.getStreamVolume(mContext) == 0, new VoiceOnCheckedChangeListener(mContext));
        CheckBoxTool.resumePlayCompoundButton(MediaPauseImageViewId, playerView == null ? false : playerView.isPlaying(), new MediaPauseOnCheckedChangeListener(playerView));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MediaControllerTopBackViewID:
                stop();
                mContext.finish();
                break;
            case R.id.MediaGoBackImageViewId:
                presenter.GoBack(5000);
                break;
            case R.id.MediaFastForwardImageViewId:
                presenter.FastForward(5000);
                break;
        }
    }
}
