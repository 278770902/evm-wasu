package com.evmtv.cloudvideo.common.model.http.csm;

import android.os.Parcel;
import android.os.Parcelable;

public class AlarmDeviceEntity implements Parcelable {

    public AlarmDeviceEntity(boolean result) {
        this.result = result;
    }

    /**
     * result : true
     * errorDeviceOffLine : false
     * errorCode : 0
     * errorDetail :
     */



    private boolean result;
    private boolean errorDeviceOffLine;
    private int errorCode;
    private String errorDetail;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isErrorDeviceOffLine() {
        return errorDeviceOffLine;
    }

    public void setErrorDeviceOffLine(boolean errorDeviceOffLine) {
        this.errorDeviceOffLine = errorDeviceOffLine;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.result ? (byte) 1 : (byte) 0);
        dest.writeByte(this.errorDeviceOffLine ? (byte) 1 : (byte) 0);
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorDetail);
    }

    public AlarmDeviceEntity() {
    }

    protected AlarmDeviceEntity(Parcel in) {
        this.result = in.readByte() != 0;
        this.errorDeviceOffLine = in.readByte() != 0;
        this.errorCode = in.readInt();
        this.errorDetail = in.readString();
    }

    public static final Parcelable.Creator<AlarmDeviceEntity> CREATOR = new Parcelable.Creator<AlarmDeviceEntity>() {
        @Override
        public AlarmDeviceEntity createFromParcel(Parcel source) {
            return new AlarmDeviceEntity(source);
        }

        @Override
        public AlarmDeviceEntity[] newArray(int size) {
            return new AlarmDeviceEntity[size];
        }
    };
}
