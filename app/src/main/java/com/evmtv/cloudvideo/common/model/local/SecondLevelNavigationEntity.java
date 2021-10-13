package com.evmtv.cloudvideo.common.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SecondLevelNavigationEntity implements Parcelable{

    private List<SecondLevelNavigationBean> secondLevelNavigation;

    public List<SecondLevelNavigationBean> getSecondLevelNavigation() {
        return secondLevelNavigation;
    }

    public void setSecondLevelNavigation(List<SecondLevelNavigationBean> secondLevelNavigation) {
        this.secondLevelNavigation = secondLevelNavigation;
    }

    public static class SecondLevelNavigationBean implements Parcelable,Comparable<SecondLevelNavigationBean> {
        /**
         * groupId : 0
         * itemId : 0
         * order : 0
         * type : tab
         * title : 首页
         * icon : main_bottom_home_selector
         * childActivity : com.evmtv.cloudvideo.common.view.LoginActivity
         */

        private int groupId;
        private int itemId;
        private int order;
        private String type;
        private String title;
        private String icon;
        private String childActivity;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getChildActivity() {
            return childActivity;
        }

        public void setChildActivity(String childActivity) {
            this.childActivity = childActivity;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.groupId);
            dest.writeInt(this.itemId);
            dest.writeInt(this.order);
            dest.writeString(this.type);
            dest.writeString(this.title);
            dest.writeString(this.icon);
            dest.writeString(this.childActivity);
        }

        public SecondLevelNavigationBean() {
        }

        protected SecondLevelNavigationBean(Parcel in) {
            this.groupId = in.readInt();
            this.itemId = in.readInt();
            this.order = in.readInt();
            this.type = in.readString();
            this.title = in.readString();
            this.icon = in.readString();
            this.childActivity = in.readString();
        }

        public static final Creator<SecondLevelNavigationBean> CREATOR = new Creator<SecondLevelNavigationBean>() {
            @Override
            public SecondLevelNavigationBean createFromParcel(Parcel source) {
                return new SecondLevelNavigationBean(source);
            }

            @Override
            public SecondLevelNavigationBean[] newArray(int size) {
                return new SecondLevelNavigationBean[size];
            }
        };

        @Override
        public int compareTo(SecondLevelNavigationBean o) {
            return ((Integer) this.order).compareTo(o.order);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.secondLevelNavigation);
    }

    public SecondLevelNavigationEntity() {
    }

    protected SecondLevelNavigationEntity(Parcel in) {
        this.secondLevelNavigation = in.createTypedArrayList(SecondLevelNavigationBean.CREATOR);
    }

    public static final Creator<SecondLevelNavigationEntity> CREATOR = new Creator<SecondLevelNavigationEntity>() {
        @Override
        public SecondLevelNavigationEntity createFromParcel(Parcel source) {
            return new SecondLevelNavigationEntity(source);
        }

        @Override
        public SecondLevelNavigationEntity[] newArray(int size) {
            return new SecondLevelNavigationEntity[size];
        }
    };
}
