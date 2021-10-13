package com.evmtv.cloudvideo.common.presenter.monitor.live.controller;

import android.os.Parcel;
import android.os.Parcelable;

public class KanJiaBaoRawRecordEntity implements Parcelable {
    private String rawRecordPath;
    private String state;

    public String getRawRecordPath() {
        return rawRecordPath;
    }

    public void setRawRecordPath(String rawRecordPath) {
        this.rawRecordPath = rawRecordPath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rawRecordPath);
        dest.writeString(this.state);
    }

    public KanJiaBaoRawRecordEntity() {
    }

    protected KanJiaBaoRawRecordEntity(Parcel in) {
        this.rawRecordPath = in.readString();
        this.state = in.readString();
    }

    public static final Creator<KanJiaBaoRawRecordEntity> CREATOR = new Creator<KanJiaBaoRawRecordEntity>() {
        @Override
        public KanJiaBaoRawRecordEntity createFromParcel(Parcel source) {
            return new KanJiaBaoRawRecordEntity(source);
        }

        @Override
        public KanJiaBaoRawRecordEntity[] newArray(int size) {
            return new KanJiaBaoRawRecordEntity[size];
        }
    };
}
