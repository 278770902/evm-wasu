package com.evmtv.cloudvideo.common.model.http.csm;

import android.os.Parcel;
import android.os.Parcelable;

public class StartPlayEntity implements Parcelable {
    private Boolean result;
    private Boolean errorDeviceBusy;
    private Boolean errorDeviceOffLine;
    private String errorDetail;
    private String sessionId;
    private String playUrl;

    public StartPlayEntity(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean getErrorDeviceBusy() {
        return errorDeviceBusy;
    }

    public void setErrorDeviceBusy(Boolean errorDeviceBusy) {
        this.errorDeviceBusy = errorDeviceBusy;
    }

    public Boolean getErrorDeviceOffLine() {
        return errorDeviceOffLine;
    }

    public void setErrorDeviceOffLine(Boolean errorDeviceOffLine) {
        this.errorDeviceOffLine = errorDeviceOffLine;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
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

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.result);
        dest.writeValue(this.errorDeviceBusy);
        dest.writeValue(this.errorDeviceOffLine);
        dest.writeString(this.errorDetail);
        dest.writeString(this.sessionId);
        dest.writeString(this.playUrl);
    }

    public StartPlayEntity() {
    }

    protected StartPlayEntity(Parcel in) {
        this.result = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.errorDeviceBusy = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.errorDeviceOffLine = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.errorDetail = in.readString();
        this.sessionId = in.readString();
        this.playUrl = in.readString();
    }

    public static final Parcelable.Creator<StartPlayEntity> CREATOR = new Parcelable.Creator<StartPlayEntity>() {
        @Override
        public StartPlayEntity createFromParcel(Parcel source) {
            return new StartPlayEntity(source);
        }

        @Override
        public StartPlayEntity[] newArray(int size) {
            return new StartPlayEntity[size];
        }
    };
}
