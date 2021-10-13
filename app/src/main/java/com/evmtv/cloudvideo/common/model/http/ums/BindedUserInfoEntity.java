package com.evmtv.cloudvideo.common.model.http.ums;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BindedUserInfoEntity implements Parcelable {


    /**
     * GUID : 51000000004160
     * bindUserArray : [{"name":"sichuan2","GUID":"51000000004185","TEL":"13524078959","regionGUID":"510000","type":1,"customerType":0,"startMeeting":1,"friendPower":1,"districtCode":"'-1'","callPower":1,"meetingPower":1,"monitorPower":0,"shortGUID":"4185","userType":0,"imageUrl":"","receiveMessageWhenOffline":1,"isMainClient":0},{"name":"evm","GUID":"51000000004128","TEL":"17761262115","regionGUID":"510000","type":1,"customerType":0,"startMeeting":1,"friendPower":1,"districtCode":"'-1'","callPower":1,"meetingPower":1,"monitorPower":0,"shortGUID":"4128","userType":0,"imageUrl":"http://www.evmtv.com:1201/ums_image/userLogo/2020081722483100000000000274.png","receiveMessageWhenOffline":1,"isMainClient":0}]
     * userJson : {"name":"test","GUID":"51000000004160","TEL":"13072125607","regionGUID":"510000","type":1,"customerType":1,"startMeeting":1,"friendPower":1,"districtCode":"-1","callPower":1,"meetingPower":1,"monitorPower":0,"shortGUID":"4160","userType":0,"imageUrl":""}
     * isSuccess : 1
     */

    private String GUID;
    private UserJsonBean userJson;
    private int isSuccess;
    private List<BindUserArrayBean> bindUserArray;

    public BindedUserInfoEntity(int isSuccess) {
        this.isSuccess = isSuccess;
    }


    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public UserJsonBean getUserJson() {
        return userJson;
    }

    public void setUserJson(UserJsonBean userJson) {
        this.userJson = userJson;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<BindUserArrayBean> getBindUserArray() {
        return bindUserArray;
    }

    public void setBindUserArray(List<BindUserArrayBean> bindUserArray) {
        this.bindUserArray = bindUserArray;
    }

    public static class UserJsonBean implements Parcelable {
        /**
         * name : test
         * GUID : 51000000004160
         * TEL : 13072125607
         * regionGUID : 510000
         * type : 1
         * customerType : 1
         * startMeeting : 1
         * friendPower : 1
         * districtCode : -1
         * callPower : 1
         * meetingPower : 1
         * monitorPower : 0
         * shortGUID : 4160
         * userType : 0
         * imageUrl :
         */

        private String name;
        private String GUID;
        private String TEL;
        private String regionGUID;
        private int type;
        private int customerType;
        private int startMeeting;
        private int friendPower;
        private String districtCode;
        private int callPower;
        private int meetingPower;
        private int monitorPower;
        private String shortGUID;
        private int userType;
        private String imageUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getTEL() {
            return TEL;
        }

        public void setTEL(String TEL) {
            this.TEL = TEL;
        }

        public String getRegionGUID() {
            return regionGUID;
        }

        public void setRegionGUID(String regionGUID) {
            this.regionGUID = regionGUID;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public int getStartMeeting() {
            return startMeeting;
        }

        public void setStartMeeting(int startMeeting) {
            this.startMeeting = startMeeting;
        }

        public int getFriendPower() {
            return friendPower;
        }

        public void setFriendPower(int friendPower) {
            this.friendPower = friendPower;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public int getCallPower() {
            return callPower;
        }

        public void setCallPower(int callPower) {
            this.callPower = callPower;
        }

        public int getMeetingPower() {
            return meetingPower;
        }

        public void setMeetingPower(int meetingPower) {
            this.meetingPower = meetingPower;
        }

        public int getMonitorPower() {
            return monitorPower;
        }

        public void setMonitorPower(int monitorPower) {
            this.monitorPower = monitorPower;
        }

        public String getShortGUID() {
            return shortGUID;
        }

        public void setShortGUID(String shortGUID) {
            this.shortGUID = shortGUID;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.GUID);
            dest.writeString(this.TEL);
            dest.writeString(this.regionGUID);
            dest.writeInt(this.type);
            dest.writeInt(this.customerType);
            dest.writeInt(this.startMeeting);
            dest.writeInt(this.friendPower);
            dest.writeString(this.districtCode);
            dest.writeInt(this.callPower);
            dest.writeInt(this.meetingPower);
            dest.writeInt(this.monitorPower);
            dest.writeString(this.shortGUID);
            dest.writeInt(this.userType);
            dest.writeString(this.imageUrl);
        }

        public UserJsonBean() {
        }

        protected UserJsonBean(Parcel in) {
            this.name = in.readString();
            this.GUID = in.readString();
            this.TEL = in.readString();
            this.regionGUID = in.readString();
            this.type = in.readInt();
            this.customerType = in.readInt();
            this.startMeeting = in.readInt();
            this.friendPower = in.readInt();
            this.districtCode = in.readString();
            this.callPower = in.readInt();
            this.meetingPower = in.readInt();
            this.monitorPower = in.readInt();
            this.shortGUID = in.readString();
            this.userType = in.readInt();
            this.imageUrl = in.readString();
        }

        public static final Parcelable.Creator<UserJsonBean> CREATOR = new Parcelable.Creator<UserJsonBean>() {
            @Override
            public UserJsonBean createFromParcel(Parcel source) {
                return new UserJsonBean(source);
            }

            @Override
            public UserJsonBean[] newArray(int size) {
                return new UserJsonBean[size];
            }
        };
    }

    public static class BindUserArrayBean implements Parcelable {
        /**
         * name : sichuan2
         * GUID : 51000000004185
         * TEL : 13524078959
         * regionGUID : 510000
         * type : 1
         * customerType : 0
         * startMeeting : 1
         * friendPower : 1
         * districtCode : '-1'
         * callPower : 1
         * meetingPower : 1
         * monitorPower : 0
         * shortGUID : 4185
         * userType : 0
         * imageUrl :
         * receiveMessageWhenOffline : 1
         * isMainClient : 0
         */

        private String name;
        private String GUID;
        private String TEL;
        private String regionGUID;
        private int type;
        private int customerType;
        private int startMeeting;
        private int friendPower;
        private String districtCode;
        private int callPower;
        private int meetingPower;
        private int monitorPower;
        private String shortGUID;
        private int userType;
        private String imageUrl;
        private int receiveMessageWhenOffline;
        private int isMainClient;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getTEL() {
            return TEL;
        }

        public void setTEL(String TEL) {
            this.TEL = TEL;
        }

        public String getRegionGUID() {
            return regionGUID;
        }

        public void setRegionGUID(String regionGUID) {
            this.regionGUID = regionGUID;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public int getStartMeeting() {
            return startMeeting;
        }

        public void setStartMeeting(int startMeeting) {
            this.startMeeting = startMeeting;
        }

        public int getFriendPower() {
            return friendPower;
        }

        public void setFriendPower(int friendPower) {
            this.friendPower = friendPower;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public int getCallPower() {
            return callPower;
        }

        public void setCallPower(int callPower) {
            this.callPower = callPower;
        }

        public int getMeetingPower() {
            return meetingPower;
        }

        public void setMeetingPower(int meetingPower) {
            this.meetingPower = meetingPower;
        }

        public int getMonitorPower() {
            return monitorPower;
        }

        public void setMonitorPower(int monitorPower) {
            this.monitorPower = monitorPower;
        }

        public String getShortGUID() {
            return shortGUID;
        }

        public void setShortGUID(String shortGUID) {
            this.shortGUID = shortGUID;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getReceiveMessageWhenOffline() {
            return receiveMessageWhenOffline;
        }

        public void setReceiveMessageWhenOffline(int receiveMessageWhenOffline) {
            this.receiveMessageWhenOffline = receiveMessageWhenOffline;
        }

        public int getIsMainClient() {
            return isMainClient;
        }

        public void setIsMainClient(int isMainClient) {
            this.isMainClient = isMainClient;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.GUID);
            dest.writeString(this.TEL);
            dest.writeString(this.regionGUID);
            dest.writeInt(this.type);
            dest.writeInt(this.customerType);
            dest.writeInt(this.startMeeting);
            dest.writeInt(this.friendPower);
            dest.writeString(this.districtCode);
            dest.writeInt(this.callPower);
            dest.writeInt(this.meetingPower);
            dest.writeInt(this.monitorPower);
            dest.writeString(this.shortGUID);
            dest.writeInt(this.userType);
            dest.writeString(this.imageUrl);
            dest.writeInt(this.receiveMessageWhenOffline);
            dest.writeInt(this.isMainClient);
        }

        public BindUserArrayBean() {
        }

        protected BindUserArrayBean(Parcel in) {
            this.name = in.readString();
            this.GUID = in.readString();
            this.TEL = in.readString();
            this.regionGUID = in.readString();
            this.type = in.readInt();
            this.customerType = in.readInt();
            this.startMeeting = in.readInt();
            this.friendPower = in.readInt();
            this.districtCode = in.readString();
            this.callPower = in.readInt();
            this.meetingPower = in.readInt();
            this.monitorPower = in.readInt();
            this.shortGUID = in.readString();
            this.userType = in.readInt();
            this.imageUrl = in.readString();
            this.receiveMessageWhenOffline = in.readInt();
            this.isMainClient = in.readInt();
        }

        public static final Parcelable.Creator<BindUserArrayBean> CREATOR = new Parcelable.Creator<BindUserArrayBean>() {
            @Override
            public BindUserArrayBean createFromParcel(Parcel source) {
                return new BindUserArrayBean(source);
            }

            @Override
            public BindUserArrayBean[] newArray(int size) {
                return new BindUserArrayBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.GUID);
        dest.writeParcelable(this.userJson, flags);
        dest.writeInt(this.isSuccess);
        dest.writeTypedList(this.bindUserArray);
    }

    public BindedUserInfoEntity() {
    }

    protected BindedUserInfoEntity(Parcel in) {
        this.GUID = in.readString();
        this.userJson = in.readParcelable(UserJsonBean.class.getClassLoader());
        this.isSuccess = in.readInt();
        this.bindUserArray = in.createTypedArrayList(BindUserArrayBean.CREATOR);
    }

    public static final Parcelable.Creator<BindedUserInfoEntity> CREATOR = new Parcelable.Creator<BindedUserInfoEntity>() {
        @Override
        public BindedUserInfoEntity createFromParcel(Parcel source) {
            return new BindedUserInfoEntity(source);
        }

        @Override
        public BindedUserInfoEntity[] newArray(int size) {
            return new BindedUserInfoEntity[size];
        }
    };
}
