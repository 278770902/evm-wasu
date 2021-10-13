package com.evmtv.cloudvideo.common.model.http.ums;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CameraShareUserEntity implements Parcelable {
    private int isSuccess;
    private String errorMessage;
    private int total;
    private List<UsersBean> users;

    public CameraShareUserEntity(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Parcelable {
        private int callPower;
        private String districtCode;
        private int customerType;
        private String GUID;
        private String shortGUID;
        private int startMeeting;
        private String TEL;
        private int meetingPower;
        private String name;
        private int friendPower;
        private int type;
        private int monitorPower;
        private String regionGUID;

        public int getCallPower() {
            return callPower;
        }

        public void setCallPower(int callPower) {
            this.callPower = callPower;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getShortGUID() {
            return shortGUID;
        }

        public void setShortGUID(String shortGUID) {
            this.shortGUID = shortGUID;
        }

        public int getStartMeeting() {
            return startMeeting;
        }

        public void setStartMeeting(int startMeeting) {
            this.startMeeting = startMeeting;
        }

        public String getTEL() {
            return TEL;
        }

        public void setTEL(String TEL) {
            this.TEL = TEL;
        }

        public int getMeetingPower() {
            return meetingPower;
        }

        public void setMeetingPower(int meetingPower) {
            this.meetingPower = meetingPower;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFriendPower() {
            return friendPower;
        }

        public void setFriendPower(int friendPower) {
            this.friendPower = friendPower;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getMonitorPower() {
            return monitorPower;
        }

        public void setMonitorPower(int monitorPower) {
            this.monitorPower = monitorPower;
        }

        public String getRegionGUID() {
            return regionGUID;
        }

        public void setRegionGUID(String regionGUID) {
            this.regionGUID = regionGUID;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.callPower);
            dest.writeString(this.districtCode);
            dest.writeInt(this.customerType);
            dest.writeString(this.GUID);
            dest.writeString(this.shortGUID);
            dest.writeInt(this.startMeeting);
            dest.writeString(this.TEL);
            dest.writeInt(this.meetingPower);
            dest.writeString(this.name);
            dest.writeInt(this.friendPower);
            dest.writeInt(this.type);
            dest.writeInt(this.monitorPower);
            dest.writeString(this.regionGUID);
        }

        public UsersBean() {
        }

        protected UsersBean(Parcel in) {
            this.callPower = in.readInt();
            this.districtCode = in.readString();
            this.customerType = in.readInt();
            this.GUID = in.readString();
            this.shortGUID = in.readString();
            this.startMeeting = in.readInt();
            this.TEL = in.readString();
            this.meetingPower = in.readInt();
            this.name = in.readString();
            this.friendPower = in.readInt();
            this.type = in.readInt();
            this.monitorPower = in.readInt();
            this.regionGUID = in.readString();
        }

        public static final Parcelable.Creator<UsersBean> CREATOR = new Parcelable.Creator<UsersBean>() {
            @Override
            public UsersBean createFromParcel(Parcel source) {
                return new UsersBean(source);
            }

            @Override
            public UsersBean[] newArray(int size) {
                return new UsersBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.isSuccess);
        dest.writeString(this.errorMessage);
        dest.writeInt(this.total);
        dest.writeTypedList(this.users);
    }

    public CameraShareUserEntity() {
    }

    protected CameraShareUserEntity(Parcel in) {
        this.isSuccess = in.readInt();
        this.errorMessage = in.readString();
        this.total = in.readInt();
        this.users = in.createTypedArrayList(UsersBean.CREATOR);
    }

    public static final Parcelable.Creator<CameraShareUserEntity> CREATOR = new Parcelable.Creator<CameraShareUserEntity>() {
        @Override
        public CameraShareUserEntity createFromParcel(Parcel source) {
            return new CameraShareUserEntity(source);
        }

        @Override
        public CameraShareUserEntity[] newArray(int size) {
            return new CameraShareUserEntity[size];
        }
    };
}
