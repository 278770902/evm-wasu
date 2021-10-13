package com.evmtv.cloudvideo.common.model.http.csm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AlarmDeviceStatusEntity implements Parcelable {

    /**
     * result : true
     * totalSize : 1
     * deviceList : [{"devcieGuid":"45000000003015","userGUID":"45000000004695","alarm":0,"id":1816,"sn":"34020016213097270850","deviceToken":""}]
     * errorCode : 0
     * errorDetail :
     */

    private boolean result;
    private int totalSize;
    private int errorCode;
    private String errorDetail;
    private List<DeviceListBean> deviceList;

    public AlarmDeviceStatusEntity(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public List<DeviceListBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceListBean> deviceList) {
        this.deviceList = deviceList;
    }

    public static class DeviceListBean implements Parcelable {
        /**
         * devcieGuid : 45000000003015
         * userGUID : 45000000004695
         * alarm : 0  关闭  1   打开
         * id : 1816
         * sn : 34020016213097270850
         * deviceToken :
         */

        private String devcieGuid;
        private String userGUID;
        private int alarm;
        private int id;
        private String sn;
        private String deviceToken;

        public String getDevcieGuid() {
            return devcieGuid;
        }

        public void setDevcieGuid(String devcieGuid) {
            this.devcieGuid = devcieGuid;
        }

        public String getUserGUID() {
            return userGUID;
        }

        public void setUserGUID(String userGUID) {
            this.userGUID = userGUID;
        }

        public int getAlarm() {
            return alarm;
        }

        public void setAlarm(int alarm) {
            this.alarm = alarm;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.devcieGuid);
            dest.writeString(this.userGUID);
            dest.writeInt(this.alarm);
            dest.writeInt(this.id);
            dest.writeString(this.sn);
            dest.writeString(this.deviceToken);
        }

        public DeviceListBean() {
        }

        protected DeviceListBean(Parcel in) {
            this.devcieGuid = in.readString();
            this.userGUID = in.readString();
            this.alarm = in.readInt();
            this.id = in.readInt();
            this.sn = in.readString();
            this.deviceToken = in.readString();
        }

        public static final Parcelable.Creator<DeviceListBean> CREATOR = new Parcelable.Creator<DeviceListBean>() {
            @Override
            public DeviceListBean createFromParcel(Parcel source) {
                return new DeviceListBean(source);
            }

            @Override
            public DeviceListBean[] newArray(int size) {
                return new DeviceListBean[size];
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
        dest.writeInt(this.totalSize);
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorDetail);
        dest.writeTypedList(this.deviceList);
    }

    public AlarmDeviceStatusEntity() {
    }

    protected AlarmDeviceStatusEntity(Parcel in) {
        this.result = in.readByte() != 0;
        this.totalSize = in.readInt();
        this.errorCode = in.readInt();
        this.errorDetail = in.readString();
        this.deviceList = in.createTypedArrayList(DeviceListBean.CREATOR);
    }

    public static final Parcelable.Creator<AlarmDeviceStatusEntity> CREATOR = new Parcelable.Creator<AlarmDeviceStatusEntity>() {
        @Override
        public AlarmDeviceStatusEntity createFromParcel(Parcel source) {
            return new AlarmDeviceStatusEntity(source);
        }

        @Override
        public AlarmDeviceStatusEntity[] newArray(int size) {
            return new AlarmDeviceStatusEntity[size];
        }
    };
}
