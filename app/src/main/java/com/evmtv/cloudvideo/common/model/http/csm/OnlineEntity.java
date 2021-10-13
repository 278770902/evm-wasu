package com.evmtv.cloudvideo.common.model.http.csm;

import android.os.Parcel;
import android.os.Parcelable;

public class OnlineEntity implements Parcelable {

    /**
     * result : true
     * errorCode : 0
     * errorDetail :
     * online : true
     */

    private boolean result;
    private int errorCode;
    private String errorDetail;
    private boolean online;

    public OnlineEntity(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.result ? (byte) 1 : (byte) 0);
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorDetail);
        dest.writeByte(this.online ? (byte) 1 : (byte) 0);
    }

    public OnlineEntity() {
    }

    protected OnlineEntity(Parcel in) {
        this.result = in.readByte() != 0;
        this.errorCode = in.readInt();
        this.errorDetail = in.readString();
        this.online = in.readByte() != 0;
    }

    public static final Parcelable.Creator<OnlineEntity> CREATOR = new Parcelable.Creator<OnlineEntity>() {
        @Override
        public OnlineEntity createFromParcel(Parcel source) {
            return new OnlineEntity(source);
        }

        @Override
        public OnlineEntity[] newArray(int size) {
            return new OnlineEntity[size];
        }
    };
}
