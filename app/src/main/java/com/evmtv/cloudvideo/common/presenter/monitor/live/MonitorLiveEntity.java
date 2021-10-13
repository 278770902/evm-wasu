package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.content.Context;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.presenter.monitor.UserSTBTabTool;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;

public class MonitorLiveEntity {
    private String deviceName;
    private String serialNum;
    private String deviceGUID;
    private String STBGuid;
    private Boolean mode; //true 辅码流 标清
    private ImageView ScreenShotView;
    private boolean isPassPermissionRecord = false;

    private EvmControllerPlayerViewA videoViewID;

    private static MonitorLiveEntity instance;

    public static MonitorLiveEntity getInstance() {
        synchronized (UserSTBTabTool.class) {
            if (instance == null)
                instance = new MonitorLiveEntity();
        }
        return instance;
    }

    public String getDeviceGUID() {
        return deviceGUID;
    }

    public void setDeviceGUID(String deviceGUID) {
        this.deviceGUID = deviceGUID;
    }

    public boolean isPassPermissionRecord() {
        return isPassPermissionRecord;
    }

    public void setPassPermissionRecord(boolean passPermissionRecord) {
        isPassPermissionRecord = passPermissionRecord;
    }

    public ImageView getScreenShotView() {
        return ScreenShotView;
    }

    public void setScreenShotView(ImageView screenShotView) {
        ScreenShotView = screenShotView;
    }

    public EvmControllerPlayerViewA getVideoViewID() {
        return videoViewID;
    }

    public void setVideoViewID(EvmControllerPlayerViewA videoViewID) {
        this.videoViewID = videoViewID;
    }

    public void setAllEntity(String serialNum, String STBGuid, Boolean mode, String deviceName, String deviceGUID) {
        this.mode = mode;
        this.STBGuid = STBGuid;
        this.serialNum = serialNum;
        this.deviceName = deviceName;
        this.deviceGUID = deviceGUID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getSTBGuid() {
        return STBGuid;
    }

    public void setSTBGuid(String STBGuid) {
        this.STBGuid = STBGuid;
    }

    public Boolean getMode() {
        return mode;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }
}
