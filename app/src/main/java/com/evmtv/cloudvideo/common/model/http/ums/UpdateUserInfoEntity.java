package com.evmtv.cloudvideo.common.model.http.ums;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdateUserInfoEntity implements Parcelable {

    /**
     * imageUrl :
     * isSuccess : 1
     */

    private String imageUrl;
    private int isSuccess;

    public UpdateUserInfoEntity(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeInt(this.isSuccess);
    }

    public UpdateUserInfoEntity() {
    }

    protected UpdateUserInfoEntity(Parcel in) {
        this.imageUrl = in.readString();
        this.isSuccess = in.readInt();
    }

    public static final Creator<UpdateUserInfoEntity> CREATOR = new Creator<UpdateUserInfoEntity>() {
        @Override
        public UpdateUserInfoEntity createFromParcel(Parcel source) {
            return new UpdateUserInfoEntity(source);
        }

        @Override
        public UpdateUserInfoEntity[] newArray(int size) {
            return new UpdateUserInfoEntity[size];
        }
    };
}
