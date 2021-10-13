package com.evmtv.cloudvideo.common.view.tool.evm.play;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.umeng.debug.log.D;

public class EvmPlayModelImpl implements EvmPlayModel {
    private View view;
    private ImageView errorBgView;
    private Handler handler;

    public EvmPlayModelImpl(View view, Handler handler) {
        this.view = view;
        this.handler = handler;
    }

    @Override
    public View inflate(Context context) {
        return ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.evm_video_play_layout, null);
    }

    public void findFullViewClose(View.OnClickListener l) {
        view.findViewById(R.id.playIBCloseViewId).setOnClickListener(l);
    }

    public void findErrorBgViewId() {
        errorBgView = view.findViewById(R.id.liveVideoErrorBgViewID);
    }

    public void errorBgGone(){
        errorBgView.setVisibility(View.GONE);
    }

    public void setErrorBg(Drawable drawable) {
        if (drawable == null)
            return;
        errorBgView.setImageDrawable(drawable);
        errorBgView.setVisibility(View.VISIBLE);
    }

    public void setErrorVisibleBg(Drawable drawable){
        setErrorBg(drawable);
    }
}
