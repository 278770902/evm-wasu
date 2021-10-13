package com.evmtv.cloudvideo.common.view.tool;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.view.tool.evm.play.EvmPlayModel;
import com.evmtv.cloudvideo.common.view.tool.evm.play.EvmPlayModelImpl;
import com.evmtv.cloudvideo.common.view.tool.evm.play.MediaControllerInterface;
import com.evmtv.util.view.EvmPlayerView;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class EvmControllerPlayerViewA extends EvmPlayerView implements View.OnTouchListener {
    private Activity context;
    private int CurrWidth = 0;
    private EvmPlayModel evmPlayModel;
    private Drawable beforePlaySrcId, outLineSrcId;
    private MediaControllerInterface mMediaController;
    private String TAG = getClass().getName();
    private ImageButton playIBCloseViewId;
    private boolean isDoesItExistCloseView;

    public EvmControllerPlayerViewA(Context context) {
        this(context, null);
    }

    public EvmControllerPlayerViewA(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EvmControllerPlayerViewA(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = (Activity) context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EvmControllerPlayerViewA, defStyle, 0);
        beforePlaySrcId = ta.getDrawable(R.styleable.EvmControllerPlayerViewA_beforePlaySrc);
        outLineSrcId = ta.getDrawable(R.styleable.EvmControllerPlayerViewA_outLineSrc);
        isDoesItExistCloseView = ta.getBoolean(R.styleable.EvmControllerPlayerViewA_DoesItExistCloseView, true);
    }

    @Override
    public void initView() {
        super.initView();
        evmPlayModel = new EvmPlayModelImpl(this, handler);
        addView(evmPlayModel.inflate(getContext()));
        evmPlayModel.findErrorBgViewId();
        this.rootLayout = findViewById(R.id.rootLayout);
        this.playIBCloseViewId = findViewById(R.id.playIBCloseViewId);
        this.playIBCloseViewId.setImageResource(R.drawable.svg_close);
        this.surfaceView = findViewById(R.id.surfaceView);
        this.progressBar = findViewById(R.id.PlayViewProgressBar);
        if (isDoesItExistCloseView)
            playIBCloseViewId.setVisibility(VISIBLE);
        else
            playIBCloseViewId.setVisibility(GONE);

        surfaceView.getHolder().addCallback(this);
    }

    public void findFullViewClose(View.OnClickListener l) {
        evmPlayModel.findFullViewClose(l);
    }


    public Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EvmPlayModel.FINISH_TYPE:
                    break;
            }
        }
    };

    @Override
    public void onPrepared(IMediaPlayer mp) {
        super.onPrepared(mp);
        evmPlayModel.errorBgGone();
    }

    public void setOutlineVisibleBg() {
        evmPlayModel.setErrorVisibleBg(outLineSrcId);
    }


    public void setBeforePlayVisibleBg() {
        evmPlayModel.setErrorVisibleBg(beforePlaySrcId);
    }

    public void setProgressBarGone() {
        if (progressBar != null)
            progressBar.setVisibility(GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();// 获取触屏动作。比如：按下、移动和抬起等手势动作
        if (action == MotionEvent.ACTION_UP)
            if (isInPlaybackState() && mMediaController != null) {
                toggleMediaControlsVisible();
            }
        return true;
    }

    private boolean isInPlaybackState() {
        return true;
    }

    public void updateControllerView() {
        if (mMediaController == null
                || mMediaController.getRootView().getHeight() == 0
                || CurrWidth == getWidth())
            return;
        CurrWidth = getWidth();
        removeView(mMediaController.getRootView());
        addView(mMediaController.makeControllerView(this), new ViewGroup.LayoutParams(getWidth(), getHeight()));
        mMediaController.hide();
        XLog.i(TAG, "update controller view ,w:" + getWidth(), "h:" + getHeight());
    }

    //播控延时隐藏控制
    private void setDelayedShowMotionEvent() {
        if (mMediaController == null || mMediaController.getRootView() == null)
            return;
        if (mMediaController.getRootView() instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) mMediaController.getRootView();
            DelayedShowDealA(vg);
        }
    }

    /**
     * @param vg 一级布局
     */
    private void DelayedShowDealA(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof ViewGroup) {
                ViewGroup child = (ViewGroup) vg.getChildAt(i);
                DelayedShowDealB(child);
            } else {
                //view 控件 处理
            }
        }
    }

    /**
     * @param child 二级布局
     */
    private void DelayedShowDealB(ViewGroup child) {
        if (child.getContentDescription() != null) {
            String cd = child.getContentDescription().toString();
            XLog.i(TAG, "cd:" + cd);
            if (!cd.contains("NotOnTouchListener")) {
                delayedShowMotionEvent(child);
                XLog.i(TAG, "cd contains :" + cd);
            }
        } else {
            delayedShowMotionEvent(child);
        }
    }


    private void delayedShowMotionEvent(View child) {
        child.setOnTouchListener(this::onTouch);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        setDelayedShowMotionEvent();
    }

    public void setMediaController(MediaControllerInterface controller) {
        XLog.i(TAG, "w:" + getWidth(), "h:" + getHeight(), "s w:" + surfaceView.getWidth(), "s h:" + surfaceView.getHeight());
        if (controller.getRootView() == null)
            addView(controller.makeControllerView(this), surfaceView.getLayoutParams());
        mMediaController = controller;
    }

    private void toggleMediaControlsVisible() {
        if (mMediaController.isShowing()) {
            XLog.i(TAG, "doing hide");
            mMediaController.hide();
        } else {
            XLog.i(TAG, "doing show");
            mMediaController.show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "media controller  event: " + event.toString());
        mMediaController.delayedShow();
        return true;
    }
}
