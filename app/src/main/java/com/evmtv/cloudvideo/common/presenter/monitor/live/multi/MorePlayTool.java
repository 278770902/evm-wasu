package com.evmtv.cloudvideo.common.presenter.monitor.live.multi;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayItemBean;
import com.evmtv.cloudvideo.common.presenter.monitor.live.view.MorePlayViewHolder;
import com.evmtv.cloudvideo.common.utils.TextureVideoViewOutlineProvider;
import com.evmtv.cloudvideo.common.utils.UnitConversion;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.util.view.EvmPlayerEventListener;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MorePlayTool implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Context mContext;
    private static MorePlayTool instance;
    private VideoKeepHandler handler;
    private OnHeaderItemClickListener headerListener;

    public static MorePlayTool getInstance(Context mContext) {
        synchronized (MorePlayTool.class) {
            if (instance == null)
                instance = new MorePlayTool(mContext);
        }
        return instance;
    }


    public void setHandler(VideoKeepHandler handler) {
        this.handler = handler;
    }

    public MorePlayTool(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnHeaderItemClickListener(OnHeaderItemClickListener headerListener) {
        this.headerListener = headerListener;
    }

    public void fillHeadIcon(ImageView view) {
        if (MorePlayEntity.getInstance().isMorePlayCameraList()) {
            view.setImageResource(R.drawable.svg_kanjiabao_more_camera_play);
        } else {
            view.setImageResource(R.drawable.svg_kanjiabao_more_list);
        }
    }


    public int getItemCount() {
        if (MorePlayEntity.getInstance().isMorePlayCameraList()) {
            return MorePlayEntity.getInstance().getItemBeans() == null ? 1 : MorePlayEntity.getInstance().getItemBeans().size() + 1;
        } else {
            return morePlayEntitySelectNum();
        }
    }

    private int morePlayEntitySelectNum() {
        if (MorePlayEntity.getInstance().getCopyItemBeans() == null)
            return 1;
        Iterator<MorePlayItemBean> iter = MorePlayEntity.getInstance().getCopyItemBeans().iterator();
        while (iter.hasNext()) {
            MorePlayItemBean item = iter.next();
            if (item.getIsSelectIndex() == -1) {
                iter.remove();
            }
        }
        Collections.sort(MorePlayEntity.getInstance().getCopyItemBeans());
        return MorePlayEntity.getInstance().getCopyItemBeans().size() + 1;
    }

    public View onCreateView(Context mContext, ViewGroup parent) {
        if (MorePlayEntity.getInstance().isMorePlayCameraList()) {
            return LayoutInflater.from(mContext).inflate(R.layout.item_more_play_list_layout, parent, false);
        } else {
            return LayoutInflater.from(mContext).inflate(R.layout.item_more_play_layout, parent, false);
        }
    }

    public void initCameraOnBindViewHolder(MorePlayViewHolder holder, int position) {
        if (MorePlayEntity.getInstance().isMorePlayCameraList()) {
            selectCameraOnBindViewHolder(holder, position);
        } else {
            CameraPlayOnBindViewHolder(holder, position);
        }
    }

    @Override
    public void onClick(View v) {
        if (headerListener == null)
            return;
        if (MorePlayEntity.getInstance().isMorePlayCameraList())
            headerListener.OnHeaderItemOnClick(morePlayEntitySelectNum(), MorePlayEntity.getInstance().isMorePlayCameraList());
        else
            headerListener.OnHeaderItemOnClick(getItemCount(), MorePlayEntity.getInstance().isMorePlayCameraList());
    }

    private void CameraPlayOnBindViewHolder(MorePlayViewHolder holder, int ValuePosition) {
        MorePlayItemBean bean = MorePlayEntity.getInstance().getCopyItemBeans().get(ValuePosition);
        holder.nameText.setText(bean.getName());
        if (!bean.isErrorDeviceOffLine()) {
            holder.evmPlayerView.setBeforePlayVisibleBg();
            holder.evmPlayerView.setTag("on");
            holder.morePlayIconViewID.setImageResource(R.drawable.item_kanjiabao_camera_online);
        } else {
            holder.morePlayIconViewID.setImageResource(R.drawable.item_kanjiabao_camera);
            holder.evmPlayerView.setOutlineVisibleBg();
            holder.evmPlayerView.setTag("off");
        }
        MorePlayEntity.getInstance().getCopyItemBeans().get(ValuePosition).setPlayerA(holder.evmPlayerView);
        MorePlayEntity.getInstance().getCopyItemBeans().get(ValuePosition).setCheckVoiceViewId(holder.morePlayCheckVoiceViewId);
        holder.morePlayCheckVoiceViewId.setTag(ValuePosition);
        holder.morePlayCheckVoiceViewId.setOnCheckedChangeListener(this::onCheckedChanged);
        startPlay(ValuePosition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.morePlayContextViewId.setOutlineProvider(new TextureVideoViewOutlineProvider(UnitConversion.px2dip(mContext, 60)));
            holder.morePlayContextViewId.setClipToOutline(true);
            holder.morePlayContextViewId.setElevation(UnitConversion.px2dip(mContext, 20));
        }
    }

    private void selectCameraOnBindViewHolder(MorePlayViewHolder holder, int position) {
        MorePlayItemBean item = MorePlayEntity.getInstance().getItemBeans().get(position);
        holder.morePlayItemListTextViewId.setText(item.getName());
        XLog.i("adapter", "MorePlay select index" + item.getIsSelectIndex());
        holder.morePlayItemListCheckBoxViewId.setChecked(item.getIsSelectIndex() != -1);
        holder.morePlayItemListCheckBoxViewId.setTag(position);
        holder.itemView.setOnClickListener(new SelectCameraBindOnListener(holder.morePlayItemListCheckBoxViewId));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        EvmControllerPlayerViewA evmPlayerView = MorePlayEntity.getInstance().getCopyItemBeans().get((int) buttonView.getTag()).getPlayerA();
        if (evmPlayerView != null)
            if (isChecked) {
                evmPlayerView.CloseVolume();
            } else {
                evmPlayerView.OpenVolume();
            }
    }


    public void startPlay(int position) {
        MorePlayItemBean morePlayItemBean = MorePlayEntity.getInstance().getCopyItemBeans().get(position);
        EvmControllerPlayerViewA player = morePlayItemBean.getPlayerA();
        String url = morePlayItemBean.getPlayUrl();
        if (player.getTag().equals("off") || player.isPlaying())
            return;
        player.reset();
        player.enableKeepVideoRatio(true);
        player.enableLowLatency(false);
        Log.i("litao", "live Video playUrl=" + url);
        player.enableRecord(true);
        player.startPlay(url);
        String sessionId = morePlayItemBean.getSessionId();
        Log.i("ipcStopPlay", "play sessionId=" + sessionId);
        player.setEvmEventListener(new EvmEventOnListener(position, handler));
    }


    public void stopAllVideoPlay() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                List<MorePlayItemBean> itemBeans = MorePlayEntity.getInstance().getCopyItemBeans();
                if (itemBeans == null)
                    return;
                for (int i = 0; i < itemBeans.size(); i++) {
                    MorePlayItemBean bean = itemBeans.get(i);
                    CsmInteractive.getInstance().stopPlay(bean.getSessionId());
                    MorePlayEntity.getInstance().getCopyItemBeans().get(i).setPlaying(false);
                    if (bean.getPlayerA() != null && bean.getPlayerA().isPlaying())
                        bean.getPlayerA().stopPlay();
                }
            }
        });
    }
}
