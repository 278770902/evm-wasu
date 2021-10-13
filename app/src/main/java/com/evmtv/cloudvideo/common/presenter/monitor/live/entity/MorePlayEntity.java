package com.evmtv.cloudvideo.common.presenter.monitor.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MorePlayEntity implements Parcelable {
    private List<MorePlayItemBean> itemBeans;
    private List<MorePlayItemBean> copyItemBeans;
    private boolean isMorePlayCameraList = true;
    private static MorePlayEntity instance;

    public static MorePlayEntity getInstance() {
        synchronized (MorePlayEntity.class) {
            if (instance == null)
                instance = new MorePlayEntity();
        }
        return instance;
    }

    public List<MorePlayItemBean> getItemBeans() {
        return itemBeans;
    }

    public List<MorePlayItemBean> getCopyItemBeans() {
        if (itemBeans == null)
            return copyItemBeans = new ArrayList<>();
        if (copyItemBeans == null) {
            copyItemBeans = new ArrayList<>();
            copyItemBeans.addAll(itemBeans);
        }
        return copyItemBeans;
    }

    public void cleanCopyItemBeans() {
        this.copyItemBeans = null;
    }

    public boolean isMorePlayCameraList() {
        return isMorePlayCameraList;
    }

    public void setMorePlayCameraList(boolean morePlayCameraList) {
        isMorePlayCameraList = morePlayCameraList;
    }

    public void setItemBeans(List<MorePlayItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.itemBeans);
    }

    public MorePlayEntity() {
    }

    protected MorePlayEntity(Parcel in) {
        this.itemBeans = in.createTypedArrayList(MorePlayItemBean.CREATOR);
    }

    public static final Parcelable.Creator<MorePlayEntity> CREATOR = new Parcelable.Creator<MorePlayEntity>() {
        @Override
        public MorePlayEntity createFromParcel(Parcel source) {
            return new MorePlayEntity(source);
        }

        @Override
        public MorePlayEntity[] newArray(int size) {
            return new MorePlayEntity[size];
        }
    };
}

