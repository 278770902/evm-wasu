package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceEntity;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceStatusEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveEntity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;

public class MonitorLiveOnClickListenerImpl implements View.OnClickListener {
    private int position;
    private Context context;

    public MonitorLiveOnClickListenerImpl(int position, Context context) {
        this.position = position;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, MonitorLiveActivity.class);
        MonitorLiveEntity.getInstance().setAllEntity(
                KanJiaBaoCameraEntity.getInstance().getCameraInfoEntity().getData().get(position).getSerialNum()
                , KanJiaBaoCameraEntity.getInstance().getSTBGuid(), true
                , KanJiaBaoCameraEntity.getInstance().getCameraInfoEntity().getData().get(position).getDeviceName()
                , KanJiaBaoCameraEntity.getInstance().getCameraInfoEntity().getData().get(position).getDeviceGUID());
        context.startActivity(intent);
    }
}
