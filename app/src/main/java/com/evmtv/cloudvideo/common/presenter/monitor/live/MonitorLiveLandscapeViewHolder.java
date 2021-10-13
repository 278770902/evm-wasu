package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

public class MonitorLiveLandscapeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageButton kanJiaBaoLandscapeBackViewId;

    public MonitorLiveLandscapeViewHolder(View itemView) {
        super(itemView);
        ViewBindUtil.bindViews(this, itemView);
        kanJiaBaoLandscapeBackViewId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kanJiaBaoLandscapeBackViewId:
                MonitorLiveTool.getInstance(null).WindowSwitch(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
        }
    }
}
