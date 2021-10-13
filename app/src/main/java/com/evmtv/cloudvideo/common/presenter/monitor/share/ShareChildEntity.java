package com.evmtv.cloudvideo.common.presenter.monitor.share;

import android.os.Parcel;
import android.os.Parcelable;

public class ShareChildEntity implements Parcelable {

    private String Name;
    private String GUID;
    private Boolean isShare;
    private String tel;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public Boolean getShare() {
        return isShare;
    }

    public void setShare(Boolean share) {
        isShare = share;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.GUID);
        dest.writeValue(this.isShare);
        dest.writeString(this.tel);
    }

    public ShareChildEntity() {
    }

    protected ShareChildEntity(Parcel in) {
        this.Name = in.readString();
        this.GUID = in.readString();
        this.isShare = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.tel = in.readString();
    }

    public static final Parcelable.Creator<ShareChildEntity> CREATOR = new Parcelable.Creator<ShareChildEntity>() {
        @Override
        public ShareChildEntity createFromParcel(Parcel source) {
            return new ShareChildEntity(source);
        }

        @Override
        public ShareChildEntity[] newArray(int size) {
            return new ShareChildEntity[size];
        }
    };
}
