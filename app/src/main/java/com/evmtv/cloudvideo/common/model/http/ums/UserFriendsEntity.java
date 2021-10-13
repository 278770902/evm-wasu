package com.evmtv.cloudvideo.common.model.http.ums;

import java.util.List;

public class UserFriendsEntity {

    /**
     * users : [{"districtCode":"'-1'","customerType":0,"GUID":"41010000002898","imageUrl":"","startMeeting":0,"TEL":"","name":"办事处1","fromBindedUser":true,"friendPower":1,"type":1,"isCollected":0,"regionGUID":"410100"}]
     * friendshipLastModifyTime : 1614240037000
     * hasUnconfirmedFriend : false
     * isSuccess : 1
     */

    private long friendshipLastModifyTime;
    private boolean hasUnconfirmedFriend;
    private int isSuccess;
    private List<UsersBean> users;

    public long getFriendshipLastModifyTime() {
        return friendshipLastModifyTime;
    }

    public void setFriendshipLastModifyTime(long friendshipLastModifyTime) {
        this.friendshipLastModifyTime = friendshipLastModifyTime;
    }

    public boolean isHasUnconfirmedFriend() {
        return hasUnconfirmedFriend;
    }

    public void setHasUnconfirmedFriend(boolean hasUnconfirmedFriend) {
        this.hasUnconfirmedFriend = hasUnconfirmedFriend;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * districtCode : '-1'
         * customerType : 0
         * GUID : 41010000002898
         * imageUrl :
         * startMeeting : 0
         * TEL :
         * name : 办事处1
         * fromBindedUser : true
         * friendPower : 1
         * type : 1
         * isCollected : 0
         * regionGUID : 410100
         */

        private String districtCode;
        private int customerType;
        private String GUID;
        private String imageUrl;
        private int startMeeting;
        private String TEL;
        private String name;
        private boolean fromBindedUser;
        private int friendPower;
        private int type;
        private int isCollected;
        private String regionGUID;

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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isFromBindedUser() {
            return fromBindedUser;
        }

        public void setFromBindedUser(boolean fromBindedUser) {
            this.fromBindedUser = fromBindedUser;
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

        public int getIsCollected() {
            return isCollected;
        }

        public void setIsCollected(int isCollected) {
            this.isCollected = isCollected;
        }

        public String getRegionGUID() {
            return regionGUID;
        }

        public void setRegionGUID(String regionGUID) {
            this.regionGUID = regionGUID;
        }
    }
}
