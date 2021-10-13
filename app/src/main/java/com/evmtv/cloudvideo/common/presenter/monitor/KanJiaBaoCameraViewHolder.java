package com.evmtv.cloudvideo.common.presenter.monitor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;

public class KanJiaBaoCameraViewHolder extends RecyclerView.ViewHolder {
    public TextView kanJiaBaoCameraNameTextViewId, startModeKanJiaBaoItemTextViewID;
    public View ib_monitor_set, kanJiaBaoMainLookBackViewID, startModeKanJiaBaoItemViewID, kanJiaBaoMainModelViewID;
    public Button KanJiaBaoMainShareViewID;
    public ImageView kanJiaBaoMainInfoImgViewId;
    public ImageView kanJiaBaoMainInfoCameraImageViewId;

    public KanJiaBaoCameraViewHolder(View itemView) {
        super(itemView);
        kanJiaBaoCameraNameTextViewId = itemView.findViewById(R.id.kanJiaBaoCameraNameTextViewId);
        ib_monitor_set = itemView.findViewById(R.id.ib_monitor_set);
        startModeKanJiaBaoItemViewID = itemView.findViewById(R.id.startModeKanJiaBaoItemViewID);
        startModeKanJiaBaoItemTextViewID = itemView.findViewById(R.id.startModeKanJiaBaoItemTextViewID);
        kanJiaBaoMainModelViewID = itemView.findViewById(R.id.kanJiaBaoMainModelViewID);
        kanJiaBaoMainLookBackViewID = itemView.findViewById(R.id.kanJiaBaoMainLookBackViewID);
        kanJiaBaoMainInfoImgViewId = itemView.findViewById(R.id.kanJiaBaoMainInfoImgViewId);
        kanJiaBaoMainInfoCameraImageViewId = itemView.findViewById(R.id.kanJiaBaoMainInfoCameraImageViewId);
        KanJiaBaoMainShareViewID = itemView.findViewById(R.id.KanJiaBaoMainShareViewID);
    }
}