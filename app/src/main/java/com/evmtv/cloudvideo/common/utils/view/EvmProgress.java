package com.evmtv.cloudvideo.common.utils.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

public class EvmProgress extends android.support.v7.widget.AppCompatImageView {
    AnimationDrawable animationDrawable;

    public EvmProgress(Context context) {
        super(context);
    }

    public EvmProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EvmProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setVisibility(int visibility) {
        try {
            if (animationDrawable == null)
                animationDrawable = (AnimationDrawable) this.getDrawable();
            if (visibility == VISIBLE) {
                animationDrawable.start();
            } else {
                animationDrawable.stop();
            }
        } catch (Exception e) {
            Log.e("EvmProgress", e.getMessage());
        }
        super.setVisibility(visibility);
    }


}
