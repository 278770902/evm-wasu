package com.evmtv.cloudvideo.common.view.tool.evm.play;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

public interface EvmPlayModel {
    static int FINISH_TYPE = 0x1111111;

    View inflate(Context context);

    void findFullViewClose(View.OnClickListener l);

    void findErrorBgViewId();

    void setErrorBg(Drawable drawable);

    void errorBgGone();

    void setErrorVisibleBg(Drawable drawable);
}
