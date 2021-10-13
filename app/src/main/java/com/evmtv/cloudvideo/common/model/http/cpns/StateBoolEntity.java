package com.evmtv.cloudvideo.common.model.http.cpns;

import android.os.Parcel;
import android.os.Parcelable;

public class StateBoolEntity implements Parcelable {
    /**
     * result : 0
     */

    private Boolean result;


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


    public StateBoolEntity(Boolean result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.result);
    }

    public StateBoolEntity() {
    }

    protected StateBoolEntity(Parcel in) {
        this.result = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<StateBoolEntity> CREATOR = new Creator<StateBoolEntity>() {
        @Override
        public StateBoolEntity createFromParcel(Parcel source) {
            return new StateBoolEntity(source);
        }

        @Override
        public StateBoolEntity[] newArray(int size) {
            return new StateBoolEntity[size];
        }
    };
}
