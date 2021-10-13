package com.evmtv.cloudvideo.common.model.http.cpns;

import android.os.Parcel;
import android.os.Parcelable;

public class StateIntEntity implements Parcelable {
    /**
     * result : 0
     */

    private int result;

    public StateIntEntity(int result) {
        this.result = result;
    }



    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.result);
    }

    public StateIntEntity() {
    }

    protected StateIntEntity(Parcel in) {
        this.result = in.readInt();
    }

    public static final Parcelable.Creator<StateIntEntity> CREATOR = new Parcelable.Creator<StateIntEntity>() {
        @Override
        public StateIntEntity createFromParcel(Parcel source) {
            return new StateIntEntity(source);
        }

        @Override
        public StateIntEntity[] newArray(int size) {
            return new StateIntEntity[size];
        }
    };
}
