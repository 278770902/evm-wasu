package com.evmtv.cloudvideo.common.presenter.monitor;

import android.view.View;

import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceEntity;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceStatusEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;

public class SetUpAlarmDeviceOnClickListenerImpl implements View.OnClickListener {
    private int position;
    private KanJiaBaoCameraViewHolder holder;

    public SetUpAlarmDeviceOnClickListenerImpl(int position, KanJiaBaoCameraViewHolder holder) {
        this.position = position;
        this.holder = holder;
    }

    @Override
    public void onClick(View v) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            AlarmDeviceEntity entity;
            private String SerialNum;
            private int alarm = 0;//1 打开
            private AlarmDeviceStatusEntity.DeviceListBean deviceStateEntity;

            @Override
            public void IO() {
                MonitorCameraInfoEntity.DataBean bean = KanJiaBaoCameraEntity.getInstance().getCameraInfoEntity().getData().get(position);
                SerialNum = bean.getSerialNum();
                deviceStateEntity = (AlarmDeviceStatusEntity.DeviceListBean) KanJiaBaoCameraEntity.getInstance().getCameraState().get(SerialNum);
                alarm = deviceStateEntity.getAlarm() == 1 ? 0 : 1;
                String json = CsmInteractive.getInstance().setAlarmDevice(bean.getSerialNum(), alarm);
                entity = TerminalCameraTool.getInstance(null).parseAlarmDeviceEntity(json);
                deviceStateEntity.setAlarm(alarm);
            }

            @Override
            public void Main() {
                if (entity.isResult() && (!entity.isErrorDeviceOffLine())) {
                    KanJiaBaoCameraEntity.getInstance().getCameraState().put(SerialNum, deviceStateEntity);
                    TerminalCameraTool.getInstance(null).kanJiaModelSwitchBg(alarm == 1, holder);
                } else {
                    ToastUtil.setToast("切换失败:" + entity.getErrorDetail());
                }
            }
        });


    }
}
