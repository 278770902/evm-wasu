package com.evmtv.cloudvideo.common.model.http.csm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CallLogEntity implements Parcelable {

    /**
     * result : true
     * errorDetail :
     * cnt : 2
     * errorCode : 0
     * list : [{"hostTerminalType":20,"sn":"41010020201126111434000001","guestName":"尹勇","remark":"","tel":"18670809898","hostId":"41000000002286","vssToken":"","vssExtension":"","serviceType":null,"endTime":"2020/11/26 11:15:24","hasRecord":false,"vssAddress":"","userType":0,"guIdTerminalType":20,"startTime":"2020/11/26 11:14:34","imgUrl":"http://123.160.94.231:9555/ums_image/userLogo/2019121815522500000000000084.png","guestFinalStatus":1,"stbId":"","guestId":"41000000002524","hostName":"滴答","guId":"41000000002524","guIdName":"尹勇","guestTerminalType":20},{"hostTerminalType":20,"sn":"41010020201125152533000011","guestName":"办事处1","remark":"","tel":"","hostId":"41000000002286","vssToken":"","vssExtension":"","serviceType":null,"endTime":"2020/11/25 15:25:39","hasRecord":false,"vssAddress":"","userType":0,"guIdTerminalType":0,"startTime":"2020/11/25 15:25:33","imgUrl":"","guestFinalStatus":2,"stbId":"","guestId":"41010000002898","hostName":"滴答","guId":"41010000002898","guIdName":"办事处1","guestTerminalType":0}]
     * lastFreshGroupCallTime : 0
     * lastGroupMissCallTime : 0
     * lastGroupCallTime : 0
     */

    private boolean result;
    private String errorDetail;
    private int cnt;
    private int errorCode;
    private int lastFreshGroupCallTime;
    private int lastGroupMissCallTime;
    private int lastGroupCallTime;
    private List<ListBean> list;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getLastFreshGroupCallTime() {
        return lastFreshGroupCallTime;
    }

    public void setLastFreshGroupCallTime(int lastFreshGroupCallTime) {
        this.lastFreshGroupCallTime = lastFreshGroupCallTime;
    }

    public int getLastGroupMissCallTime() {
        return lastGroupMissCallTime;
    }

    public void setLastGroupMissCallTime(int lastGroupMissCallTime) {
        this.lastGroupMissCallTime = lastGroupMissCallTime;
    }

    public int getLastGroupCallTime() {
        return lastGroupCallTime;
    }

    public void setLastGroupCallTime(int lastGroupCallTime) {
        this.lastGroupCallTime = lastGroupCallTime;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * hostTerminalType : 20
         * sn : 41010020201126111434000001
         * guestName : 尹勇
         * remark :
         * tel : 18670809898
         * hostId : 41000000002286
         * vssToken :
         * vssExtension :
         * serviceType : null
         * endTime : 2020/11/26 11:15:24
         * hasRecord : false
         * vssAddress :
         * userType : 0
         * guIdTerminalType : 20
         * startTime : 2020/11/26 11:14:34
         * imgUrl : http://123.160.94.231:9555/ums_image/userLogo/2019121815522500000000000084.png
         * guestFinalStatus : 1
         * stbId :
         * guestId : 41000000002524
         * hostName : 滴答
         * guId : 41000000002524
         * guIdName : 尹勇
         * guestTerminalType : 20
         */

        private int hostTerminalType;
        private String sn;
        private String guestName;
        private String remark;
        private String tel;
        private String hostId;
        private String vssToken;
        private String vssExtension;
//        private Object serviceType;
        private String endTime;
        private boolean hasRecord;
        private String vssAddress;
        private int userType;
        private int guIdTerminalType;
        private String startTime;
        private String imgUrl;
        private int guestFinalStatus;
        private String stbId;
        private String guestId;
        private String hostName;
        private String guId;
        private String guIdName;
        private int guestTerminalType;

        public int getHostTerminalType() {
            return hostTerminalType;
        }

        public void setHostTerminalType(int hostTerminalType) {
            this.hostTerminalType = hostTerminalType;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getGuestName() {
            return guestName;
        }

        public void setGuestName(String guestName) {
            this.guestName = guestName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getHostId() {
            return hostId;
        }

        public void setHostId(String hostId) {
            this.hostId = hostId;
        }

        public String getVssToken() {
            return vssToken;
        }

        public void setVssToken(String vssToken) {
            this.vssToken = vssToken;
        }

        public String getVssExtension() {
            return vssExtension;
        }

        public void setVssExtension(String vssExtension) {
            this.vssExtension = vssExtension;
        }

//        public Object getServiceType() {
//            return serviceType;
//        }

//        public void setServiceType(Object serviceType) {
//            this.serviceType = serviceType;
//        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public boolean isHasRecord() {
            return hasRecord;
        }

        public void setHasRecord(boolean hasRecord) {
            this.hasRecord = hasRecord;
        }

        public String getVssAddress() {
            return vssAddress;
        }

        public void setVssAddress(String vssAddress) {
            this.vssAddress = vssAddress;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getGuIdTerminalType() {
            return guIdTerminalType;
        }

        public void setGuIdTerminalType(int guIdTerminalType) {
            this.guIdTerminalType = guIdTerminalType;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getGuestFinalStatus() {
            return guestFinalStatus;
        }

        public void setGuestFinalStatus(int guestFinalStatus) {
            this.guestFinalStatus = guestFinalStatus;
        }

        public String getStbId() {
            return stbId;
        }

        public void setStbId(String stbId) {
            this.stbId = stbId;
        }

        public String getGuestId() {
            return guestId;
        }

        public void setGuestId(String guestId) {
            this.guestId = guestId;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getGuId() {
            return guId;
        }

        public void setGuId(String guId) {
            this.guId = guId;
        }

        public String getGuIdName() {
            return guIdName;
        }

        public void setGuIdName(String guIdName) {
            this.guIdName = guIdName;
        }

        public int getGuestTerminalType() {
            return guestTerminalType;
        }

        public void setGuestTerminalType(int guestTerminalType) {
            this.guestTerminalType = guestTerminalType;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.hostTerminalType);
            dest.writeString(this.sn);
            dest.writeString(this.guestName);
            dest.writeString(this.remark);
            dest.writeString(this.tel);
            dest.writeString(this.hostId);
            dest.writeString(this.vssToken);
            dest.writeString(this.vssExtension);
//            dest.writeParcelable(this.serviceType, flags);
            dest.writeString(this.endTime);
            dest.writeByte(this.hasRecord ? (byte) 1 : (byte) 0);
            dest.writeString(this.vssAddress);
            dest.writeInt(this.userType);
            dest.writeInt(this.guIdTerminalType);
            dest.writeString(this.startTime);
            dest.writeString(this.imgUrl);
            dest.writeInt(this.guestFinalStatus);
            dest.writeString(this.stbId);
            dest.writeString(this.guestId);
            dest.writeString(this.hostName);
            dest.writeString(this.guId);
            dest.writeString(this.guIdName);
            dest.writeInt(this.guestTerminalType);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.hostTerminalType = in.readInt();
            this.sn = in.readString();
            this.guestName = in.readString();
            this.remark = in.readString();
            this.tel = in.readString();
            this.hostId = in.readString();
            this.vssToken = in.readString();
            this.vssExtension = in.readString();
//            this.serviceType = in.readParcelable(Object.class.getClassLoader());
            this.endTime = in.readString();
            this.hasRecord = in.readByte() != 0;
            this.vssAddress = in.readString();
            this.userType = in.readInt();
            this.guIdTerminalType = in.readInt();
            this.startTime = in.readString();
            this.imgUrl = in.readString();
            this.guestFinalStatus = in.readInt();
            this.stbId = in.readString();
            this.guestId = in.readString();
            this.hostName = in.readString();
            this.guId = in.readString();
            this.guIdName = in.readString();
            this.guestTerminalType = in.readInt();
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.result ? (byte) 1 : (byte) 0);
        dest.writeString(this.errorDetail);
        dest.writeInt(this.cnt);
        dest.writeInt(this.errorCode);
        dest.writeInt(this.lastFreshGroupCallTime);
        dest.writeInt(this.lastGroupMissCallTime);
        dest.writeInt(this.lastGroupCallTime);
        dest.writeTypedList(this.list);
    }

    public CallLogEntity() {
    }

    protected CallLogEntity(Parcel in) {
        this.result = in.readByte() != 0;
        this.errorDetail = in.readString();
        this.cnt = in.readInt();
        this.errorCode = in.readInt();
        this.lastFreshGroupCallTime = in.readInt();
        this.lastGroupMissCallTime = in.readInt();
        this.lastGroupCallTime = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<CallLogEntity> CREATOR = new Parcelable.Creator<CallLogEntity>() {
        @Override
        public CallLogEntity createFromParcel(Parcel source) {
            return new CallLogEntity(source);
        }

        @Override
        public CallLogEntity[] newArray(int size) {
            return new CallLogEntity[size];
        }
    };
}
