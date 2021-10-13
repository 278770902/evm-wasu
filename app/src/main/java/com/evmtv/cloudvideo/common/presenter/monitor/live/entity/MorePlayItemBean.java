package com.evmtv.cloudvideo.common.presenter.monitor.live.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.CheckBox;

import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;

public class MorePlayItemBean implements Parcelable, Comparable<MorePlayItemBean> {
    private String sessionId;
    private String playUrl;
    private boolean errorDeviceOffLine;
    private boolean errorDeviceBusy;
    private String name;
    //未选择 -1  选择排序 0 1 2 3 4 ·····；
    private int isSelectIndex = -1;
    private String serialNum;

    private EvmControllerPlayerViewA playerA;
    private CheckBox checkVoiceViewId;
    private Boolean isPlaying = false;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public CheckBox getCheckVoiceViewId() {
        return checkVoiceViewId;
    }

    public void setCheckVoiceViewId(CheckBox checkVoiceViewId) {
        this.checkVoiceViewId = checkVoiceViewId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public boolean isErrorDeviceOffLine() {
        return errorDeviceOffLine;
    }

    public void setErrorDeviceOffLine(boolean errorDeviceOffLine) {
        this.errorDeviceOffLine = errorDeviceOffLine;
    }

    public boolean isErrorDeviceBusy() {
        return errorDeviceBusy;
    }

    public void setErrorDeviceBusy(boolean errorDeviceBusy) {
        this.errorDeviceBusy = errorDeviceBusy;
    }

    public int getIsSelectIndex() {
        return isSelectIndex;
    }

    public void setIsSelectIndex(int isSelectIndex) {
        this.isSelectIndex = isSelectIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public EvmControllerPlayerViewA getPlayerA() {
        return playerA;
    }

    public void setPlayerA(EvmControllerPlayerViewA playerA) {
        this.playerA = playerA;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sessionId);
        dest.writeString(this.playUrl);
        dest.writeString(this.name);
        dest.writeByte(this.errorDeviceOffLine ? (byte) 1 : (byte) 0);
        dest.writeByte(this.errorDeviceBusy ? (byte) 1 : (byte) 0);
        dest.writeInt(this.isSelectIndex);
        dest.writeString(this.serialNum);
    }

    public MorePlayItemBean() {
    }

    protected MorePlayItemBean(Parcel in) {
        this.sessionId = in.readString();
        this.playUrl = in.readString();
        this.name = in.readString();
        this.errorDeviceOffLine = in.readByte() != 0;
        this.errorDeviceBusy = in.readByte() != 0;
        this.isSelectIndex = in.readInt();
        this.serialNum=in.readString();
    }

    public static final Parcelable.Creator<MorePlayItemBean> CREATOR = new Parcelable.Creator<MorePlayItemBean>() {
        @Override
        public MorePlayItemBean createFromParcel(Parcel source) {
            return new MorePlayItemBean(source);
        }

        @Override
        public MorePlayItemBean[] newArray(int size) {
            return new MorePlayItemBean[size];
        }
    };

    @Override
    public int compareTo(@NonNull MorePlayItemBean o) {
        return ((Integer) this.getIsSelectIndex()).compareTo(o.isSelectIndex);
    }
}