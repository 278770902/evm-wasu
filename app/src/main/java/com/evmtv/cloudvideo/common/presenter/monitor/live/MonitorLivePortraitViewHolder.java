package com.evmtv.cloudvideo.common.presenter.monitor.live;


import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

public class MonitorLivePortraitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageButton fullscreenIBViewId, kanJiaBaoPauseImageViewId;
    private CheckBox kanJiaBaoVoiceImageViewId;

    public MonitorLivePortraitViewHolder(View itemView) {
        super(itemView);
        ViewBindUtil.bindViews(this, itemView);
        fullscreenIBViewId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fullscreenIBViewId:
                MonitorLiveTool.getInstance(null).WindowSwitch(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }
}
