package com.evmtv.cloudvideo.common.model.http;

import android.os.Parcel;
import android.os.Parcelable;

public class CpnUserInfoBean implements Parcelable {


    /**
     * result : true
     * CSMServer : 123.160.94.231:9554
     * userGUID : 41000000002286
     * name : 滴答
     * PNSServer : 123.160.94.231:9554
     * UMSServer : 123.160.94.231:9554
     */

    private boolean result;
    private String CSMServer;
    private String userGUID;
    private String name;
    private String PNSServer;
    private String UMSServer;
    private String errorMessage;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCSMServer() {
        return CSMServer;
    }

    public void setCSMServer(String CSMServer) {
        this.CSMServer = CSMServer;
    }

    public String getUserGUID() {
        return userGUID;
    }

    public void setUserGUID(String userGUID) {
        this.userGUID = userGUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPNSServer() {
        return PNSServer;
    }

    public void setPNSServer(String PNSServer) {
        this.PNSServer = PNSServer;
    }

    public String getUMSServer() {
        return UMSServer;
    }

    public void setUMSServer(String UMSServer) {
        this.UMSServer = UMSServer;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.result ? (byte) 1 : (byte) 0);
        dest.writeString(this.CSMServer);
        dest.writeString(this.userGUID);
        dest.writeString(this.name);
        dest.writeString(this.PNSServer);
        dest.writeString(this.UMSServer);
        dest.writeString(this.errorMessage);
    }

    public CpnUserInfoBean() {
    }

    protected CpnUserInfoBean(Parcel in) {
        this.result = in.readByte() != 0;
        this.CSMServer = in.readString();
        this.userGUID = in.readString();
        this.name = in.readString();
        this.PNSServer = in.readString();
        this.UMSServer = in.readString();
        this.errorMessage = in.readString();
    }

    public static final Creator<CpnUserInfoBean> CREATOR = new Creator<CpnUserInfoBean>() {
        @Override
        public CpnUserInfoBean createFromParcel(Parcel source) {
            return new CpnUserInfoBean(source);
        }

        @Override
        public CpnUserInfoBean[] newArray(int size) {
            return new CpnUserInfoBean[size];
        }
    };
}
