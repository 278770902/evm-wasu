package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

public class MonitorLiveControlViewHolder extends RecyclerView.ViewHolder {
    public ImageView ControlViewID;

    public MonitorLiveControlViewHolder(View itemView) {
        super(itemView);
        ViewBindUtil.bindViews(this, itemView);
    }
}
