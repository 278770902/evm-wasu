package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.KanJiaBaoCameraEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayItemBean;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.MorePlayTool;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.OnHeaderItemClickListener;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.VideoKeepHandler;
import com.evmtv.cloudvideo.common.presenter.monitor.live.view.MorePlayViewHolder;
import com.evmtv.cloudvideo.common.utils.TextureVideoViewOutlineProvider;
import com.evmtv.cloudvideo.common.utils.UnitConversion;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MorePlayAdapter extends RecyclerView.Adapter<MorePlayViewHolder> {
    private Context mContext;
    private final static int HEADER_RECYCLER_VIEW_ITEM = 10001;
    private final static int NORMAL_RECYCLER_VIEW_ITEM = HEADER_RECYCLER_VIEW_ITEM + 1;


    public MorePlayAdapter(Context mContext) {
        this.mContext = mContext;
        MorePlayTool.getInstance(mContext).setHandler(new VideoKeepHandler());
    }


    @Override
    public MorePlayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        if (viewType == HEADER_RECYCLER_VIEW_ITEM) {
            root = LayoutInflater.from(mContext).inflate(R.layout.item_more_play_head_layout, null);
        } else {
            root = MorePlayTool.getInstance(mContext).onCreateView(mContext, parent);
        }
        return new MorePlayViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MorePlayViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == HEADER_RECYCLER_VIEW_ITEM) {
            MorePlayTool.getInstance(mContext).fillHeadIcon(holder.morePlayHeadItemIconViewId);
            holder.itemView.setOnClickListener(MorePlayTool.getInstance(null));
            return;
        }
        MorePlayTool.getInstance(mContext).initCameraOnBindViewHolder(holder, position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_RECYCLER_VIEW_ITEM;
        } else {
            return NORMAL_RECYCLER_VIEW_ITEM;
        }
    }


    @Override
    public int getItemCount() {
        return MorePlayTool.getInstance(null).getItemCount();
    }

}
