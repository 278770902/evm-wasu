package com.evmtv.cloudvideo.common.presenter.monitor.live.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;

public class MorePlayViewHolder extends RecyclerView.ViewHolder {

    public TextView nameText, morePlayItemListTextViewId;
    public EvmControllerPlayerViewA evmPlayerView;
    public CheckBox morePlayCheckVoiceViewId, morePlayItemListCheckBoxViewId;
    public ImageView morePlayHeadItemIconViewId, morePlayIconViewID;
    public View morePlayContextViewId;
//        private ImageButton morePlayCloseViewId;

    public MorePlayViewHolder(View itemView) {
        super(itemView);
        try {
            morePlayHeadItemIconViewId = itemView.findViewById(R.id.morePlayHeadItemIconViewId);
        } catch (Exception e) {
        }

        try {
            morePlayItemListCheckBoxViewId = itemView.findViewById(R.id.morePlayItemListCheckBoxViewId);
            morePlayItemListTextViewId = itemView.findViewById(R.id.morePlayItemListTextViewId);
        } catch (Exception e) {
        }

        try {
            nameText = itemView.findViewById(R.id.STBNameTextViewId);
            evmPlayerView = itemView.findViewById(R.id.morePlayEvmPlayerViewID);
            morePlayCheckVoiceViewId = itemView.findViewById(R.id.morePlayCheckVoiceViewId);
            morePlayContextViewId = itemView.findViewById(R.id.morePlayContextViewId);
            morePlayIconViewID = itemView.findViewById(R.id.morePlayIconViewID);
        } catch (Exception e) {
        }
    }
}
