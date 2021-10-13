package com.evmtv.cloudvideo.common.presenter.monitor.live.multi;

import android.os.Handler;
import android.os.Message;

import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayItemBean;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class VideoKeepHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        if (msg.obj instanceof Integer)
            keepHttp((int) msg.obj);
        if (msg.obj instanceof String)
            XLog.e("VideoKeepHandler", "keep video----" + msg.obj);
    }


    private void keepHttp(int position) {
        if (MorePlayEntity.getInstance().getCopyItemBeans().size()<=position)
            return;
        MorePlayItemBean itemBean = MorePlayEntity.getInstance().getCopyItemBeans().get(position);
        String sessionId = itemBean.getSessionId();
        EvmControllerPlayerViewA play = itemBean.getPlayerA();
        if (sessionId == null || sessionId.isEmpty() || play == null || (!itemBean.getPlaying()))
            return;
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                CsmInteractive.getInstance().keep(sessionId);
            }
        });
        Message message = new Message();
        message.obj = position;
        sendMessageDelayed(message);
    }


    public boolean sendMessageDelayed(Message msg) {
        return super.sendMessageDelayed(msg, 10000);
    }
}
