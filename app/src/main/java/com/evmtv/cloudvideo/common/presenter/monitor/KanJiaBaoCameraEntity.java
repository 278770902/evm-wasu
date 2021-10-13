package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;

import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceStatusEntity;
import com.evmtv.cloudvideo.common.model.http.ums.BindUserEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;

import java.util.HashMap;
import java.util.Map;

public class KanJiaBaoCameraEntity {
    private static KanJiaBaoCameraEntity instance;
    private String STBGuid;
    private MonitorCameraInfoEntity cameraInfoEntity;
    private Map<String, Object> cameraState = new HashMap<>();
    private BindUserEntity userEntity;

    public static KanJiaBaoCameraEntity getInstance() {
        synchronized (TerminalCameraTool.class) {
            if (instance == null)
                instance = new KanJiaBaoCameraEntity();
        }
        return instance;
    }

    public String getSTBGuid() {
        return STBGuid;
    }

    public void setSTBGuid(String STBGuid) {
        this.STBGuid = STBGuid;
    }

    public MonitorCameraInfoEntity getCameraInfoEntity() {
        return cameraInfoEntity;
    }

    public void setCameraInfoEntity(MonitorCameraInfoEntity cameraInfoEntity) {
        this.cameraInfoEntity = cameraInfoEntity;
    }

    public Map<String, Object> getCameraState() {
        return cameraState;
    }

    public BindUserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(BindUserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setCameraState(AlarmDeviceStatusEntity alarmDeviceStatusEntity) {
        if (!alarmDeviceStatusEntity.isResult()
                || alarmDeviceStatusEntity.getDeviceList() == null
                || alarmDeviceStatusEntity.getDeviceList().size() == 0)
            return;
        for (AlarmDeviceStatusEntity.DeviceListBean bean : alarmDeviceStatusEntity.getDeviceList()) {
            cameraState.put(bean.getSn(), bean);
        }
    }
}
