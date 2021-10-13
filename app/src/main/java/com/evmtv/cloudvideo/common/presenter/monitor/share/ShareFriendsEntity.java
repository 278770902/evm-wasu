package com.evmtv.cloudvideo.common.presenter.monitor.share;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class ShareFriendsEntity implements Parcelable {
    private String title;
    private ArrayList<ShareChildEntity> children;
    private boolean isExpand;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ShareChildEntity> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ShareChildEntity> children) {
        this.children = children;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeTypedList(this.children);
        dest.writeByte(this.isExpand ? (byte) 1 : (byte) 0);
    }

    public ShareFriendsEntity() {
    }

    protected ShareFriendsEntity(Parcel in) {
        this.title = in.readString();
        this.children = in.createTypedArrayList(ShareChildEntity.CREATOR);
        this.isExpand = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ShareFriendsEntity> CREATOR = new Parcelable.Creator<ShareFriendsEntity>() {
        @Override
        public ShareFriendsEntity createFromParcel(Parcel source) {
            return new ShareFriendsEntity(source);
        }

        @Override
        public ShareFriendsEntity[] newArray(int size) {
            return new ShareFriendsEntity[size];
        }
    };
}
