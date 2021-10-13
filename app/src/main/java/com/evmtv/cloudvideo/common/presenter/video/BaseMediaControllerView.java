package com.evmtv.cloudvideo.common.presenter.video;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.evmtv.cloudvideo.common.view.tool.evm.play.MediaControllerInterface;
import com.evmtv.util.view.EvmPlayerView;

public abstract class BaseMediaControllerView implements MediaControllerInterface {
    protected View mRootView;
    protected Handler handler;
    protected Activity mContext;
    protected String title, session;
    protected EvmPlayerView playerView;
    protected SeekBar kanJiaBaoSeekBarViewId;
    protected TextView timeCurrentPlayTextViewId;
    protected static final int sDefaultTimeout = 4000;
    protected int phoneDuration, videoDuration, playPercent;
    protected CheckBox MediaVoiceImageViewId, MediaPauseImageViewId;
    protected ImageButton MediaControllerTopBackViewID, MediaGoBackImageViewId, MediaFastForwardImageViewId;


    public abstract void UpdatePlayControllerView();

    public int getPhoneDuration() {
        return phoneDuration;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public int getPlayPercent() {
        return playPercent;
    }

    public BaseMediaControllerView(Activity mContext, String title, String session) {
        this.mContext = mContext;
        this.title = title;
        this.session = session;
        handler = new Handler();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    public void show(int timeout) {
        if (timeout == 0)
            return;
        mRootView.removeCallbacks(mFadeOut);
        mRootView.postDelayed(mFadeOut, timeout);
    }

    public void removeMessages(int what) {
        if (handler == null)
            return;
        handler.removeMessages(what);
    }

    @Override
    public void show() {
        mRootView.bringToFront();
        mRootView.setVisibility(View.VISIBLE);
        show(sDefaultTimeout);
        UpdatePlayControllerView();
    }

    @Override
    public boolean isShowing() {
        if (mRootView != null)
            return mRootView.getVisibility() == View.VISIBLE;
        else
            return false;
    }

    @Override
    public void delayedShow() {
        show(sDefaultTimeout);
    }

    @Override
    public void hide() {
        mRootView.setVisibility(View.INVISIBLE);
        mRootView.removeCallbacks(mFadeOut);
    }

    protected void initDisplayVoice(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new VoiceOnCheckedChangeListener(mContext));
    }

    protected void initDisplayMediaPause(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new MediaPauseOnCheckedChangeListener(playerView));
    }

    protected void initDisplaySeekBar(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(new MediaOnSeekBarChangeListener(playerView, handler));
        seekBar.setOnTouchListener(new MediaOnSeekOnTouchListener(handler));
    }

    protected final Runnable mFadeOut = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public void removeCallbacksFadeOut() {
        if (mRootView == null)
            return;
        mRootView.removeCallbacks(mFadeOut);
    }

    public void postDelayedFadeOut() {
        if (mRootView == null)
            return;
        mRootView.postDelayed(mFadeOut, sDefaultTimeout);
    }
}
