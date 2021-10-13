package com.evmtv.cloudvideo.common.presenter.video;

import android.content.Context;
import android.widget.CompoundButton;

import com.evmtv.cloudvideo.common.utils.SystemUtil;

public class VoiceOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

    private Context mContext;

    public VoiceOnCheckedChangeListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            SystemUtil.silentSwitchOff(mContext);
        } else {
            SystemUtil.silentSwitchOn(mContext);
        }
    }
}
