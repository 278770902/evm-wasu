package com.evmtv.cloudvideo.common.model.http.ums;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;

import java.util.List;

public class MonitorCameraInfoEntity implements Parcelable {

    /**
     * data : [{"userName":"admin","password":"admin","deviceGUID":"45000000003015","deviceName":"个人摄像头","serialNum":"34020016213097270850","controlPower":1,"sdPower":1,"reviewPower":1,"shoutPower":0,"type":3,"url":"","networkType":"0","sipAddress":"10.0.6.3:10200","recordPower":0,"startRecord":0,"sharePower":0,"detailedAddr":"","lng":"","lat":""}]
     * errCode : 1
     * errMessage : SUCCESS
     * dataSize : 1
     */

    private int errCode;
    private String errMessage;
    private int dataSize;
    private List<DataBean> data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public MonitorCameraInfoEntity(int errCode) {
        this.errCode = errCode;
    }

    public static class DataBean implements Parcelable,Comparable<DataBean> {
        /**
         * userName : admin
         * password : admin
         * deviceGUID : 45000000003015
         * deviceName : 个人摄像头
         * serialNum : 34020016213097270850
         * controlPower : 1
         * sdPower : 1
         * reviewPower : 1
         * shoutPower : 0
         * type : 3
         * url :
         * networkType : 0
         * sipAddress : 10.0.6.3:10200
         * recordPower : 0
         * startRecord : 0
         * sharePower : 0
         * detailedAddr :
         * lng :
         * lat :
         */

        private String userName;
        private String password;
        private String deviceGUID;
        private String deviceName;
        private String serialNum;
        private int controlPower;
        private int sdPower;
        private int reviewPower;
        private int shoutPower;
        private int type;
        private String url;
        private String networkType;
        private String sipAddress;
        private int recordPower;
        private int startRecord;
        private int sharePower;
        private String detailedAddr;
        private String lng;
        private String lat;

        //未选择 -1  选择排序 0 1 2 3 4 ·····；
        private int  isSelectIndex=-1;

        public int getIsSelectIndex() {
            return isSelectIndex;
        }

        public void setIsSelectIndex(int isSelectIndex) {
            this.isSelectIndex = isSelectIndex;
        }


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDeviceGUID() {
            return deviceGUID;
        }

        public void setDeviceGUID(String deviceGUID) {
            this.deviceGUID = deviceGUID;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getSerialNum() {
            return serialNum;
        }

        public void setSerialNum(String serialNum) {
            this.serialNum = serialNum;
        }

        public int getControlPower() {
            return controlPower;
        }

        public void setControlPower(int controlPower) {
            this.controlPower = controlPower;
        }

        public int getSdPower() {
            return sdPower;
        }

        public void setSdPower(int sdPower) {
            this.sdPower = sdPower;
        }

        public int getReviewPower() {
            return reviewPower;
        }

        public void setReviewPower(int reviewPower) {
            this.reviewPower = reviewPower;
        }

        public int getShoutPower() {
            return shoutPower;
        }

        public void setShoutPower(int shoutPower) {
            this.shoutPower = shoutPower;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNetworkType() {
            return networkType;
        }

        public void setNetworkType(String networkType) {
            this.networkType = networkType;
        }

        public String getSipAddress() {
            return sipAddress;
        }

        public void setSipAddress(String sipAddress) {
            this.sipAddress = sipAddress;
        }

        public int getRecordPower() {
            return recordPower;
        }

        public void setRecordPower(int recordPower) {
            this.recordPower = recordPower;
        }

        public int getStartRecord() {
            return startRecord;
        }

        public void setStartRecord(int startRecord) {
            this.startRecord = startRecord;
        }

        public int getSharePower() {
            return sharePower;
        }

        public void setSharePower(int sharePower) {
            this.sharePower = sharePower;
        }

        public String getDetailedAddr() {
            return detailedAddr;
        }

        public void setDetailedAddr(String detailedAddr) {
            this.detailedAddr = detailedAddr;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userName);
            dest.writeString(this.password);
            dest.writeString(this.deviceGUID);
            dest.writeString(this.deviceName);
            dest.writeString(this.serialNum);
            dest.writeInt(this.controlPower);
            dest.writeInt(this.sdPower);
            dest.writeInt(this.reviewPower);
            dest.writeInt(this.shoutPower);
            dest.writeInt(this.type);
            dest.writeString(this.url);
            dest.writeString(this.networkType);
            dest.writeString(this.sipAddress);
            dest.writeInt(this.recordPower);
            dest.writeInt(this.startRecord);
            dest.writeInt(this.sharePower);
            dest.writeString(this.detailedAddr);
            dest.writeString(this.lng);
            dest.writeString(this.lat);
            dest.writeInt(this.isSelectIndex);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.userName = in.readString();
            this.password = in.readString();
            this.deviceGUID = in.readString();
            this.deviceName = in.readString();
            this.serialNum = in.readString();
            this.controlPower = in.readInt();
            this.sdPower = in.readInt();
            this.reviewPower = in.readInt();
            this.shoutPower = in.readInt();
            this.type = in.readInt();
            this.url = in.readString();
            this.networkType = in.readString();
            this.sipAddress = in.readString();
            this.recordPower = in.readInt();
            this.startRecord = in.readInt();
            this.sharePower = in.readInt();
            this.detailedAddr = in.readString();
            this.lng = in.readString();
            this.lat = in.readString();
            this.isSelectIndex=in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        @Override
        public int compareTo(@NonNull DataBean o) {
            return ((Integer) this.isSelectIndex).compareTo(o.isSelectIndex);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.errCode);
        dest.writeString(this.errMessage);
        dest.writeInt(this.dataSize);
        dest.writeTypedList(this.data);
    }

    public MonitorCameraInfoEntity() {
    }

    protected MonitorCameraInfoEntity(Parcel in) {
        this.errCode = in.readInt();
        this.errMessage = in.readString();
        this.dataSize = in.readInt();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<MonitorCameraInfoEntity> CREATOR = new Parcelable.Creator<MonitorCameraInfoEntity>() {
        @Override
        public MonitorCameraInfoEntity createFromParcel(Parcel source) {
            return new MonitorCameraInfoEntity(source);
        }

        @Override
        public MonitorCameraInfoEntity[] newArray(int size) {
            return new MonitorCameraInfoEntity[size];
        }
    };
}
