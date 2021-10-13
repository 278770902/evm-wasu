package com.evmtv.cloudvideo.common.presenter.monitor.live.multi;

import android.os.Message;

import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayItemBean;
import com.evmtv.util.view.EvmPlayerEventListener;

public class EvmEventOnListener implements EvmPlayerEventListener {

    private int position;
    private VideoKeepHandler handler;

    public EvmEventOnListener(int position, VideoKeepHandler handler) {
        this.position = position;
        this.handler = handler;
    }

    @Override
    public void eventListener(int i, String s) {
        MorePlayItemBean itemBean = MorePlayEntity.getInstance().getCopyItemBeans().get(position);
        itemBean.getPlayerA().setProgressBarGone();
        if (i == START_PLAY_SUCCESS) {
            itemBean.getCheckVoiceViewId().setClickable(true);
            Message message = new Message();
            message.obj = position;
            MorePlayEntity.getInstance().getCopyItemBeans().get(position).setPlaying(true);
            handler.sendMessageDelayed(message);
        } else {
            MorePlayEntity.getInstance().getCopyItemBeans().get(position).setPlaying(false);
        }
    }
}
