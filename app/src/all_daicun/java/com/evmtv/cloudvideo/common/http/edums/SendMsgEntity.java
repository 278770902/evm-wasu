package com.evmtv.cloudvideo.common.http.edums;

import android.os.Parcel;
import android.os.Parcelable;

public class SendMsgEntity implements Parcelable {


    /**
     * code : 200
     * msg : 请求已经成功处理
     * total : 0
     * rows : {"sessionid":"4F75032572E0C598336ED78348D1EDF3"}
     */

    private int code;
    private String msg;
    private int total;
    private RowsBean rows;

    public SendMsgEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Parcelable {
        /**
         * sessionid : 4F75032572E0C598336ED78348D1EDF3
         */

        private String sessionid;

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sessionid);
        }

        public RowsBean() {
        }

        protected RowsBean(Parcel in) {
            this.sessionid = in.readString();
        }

        public static final Parcelable.Creator<RowsBean> CREATOR = new Parcelable.Creator<RowsBean>() {
            @Override
            public RowsBean createFromParcel(Parcel source) {
                return new RowsBean(source);
            }

            @Override
            public RowsBean[] newArray(int size) {
                return new RowsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeInt(this.total);
        dest.writeParcelable(this.rows, flags);
    }

    public SendMsgEntity() {
    }

    protected SendMsgEntity(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.total = in.readInt();
        this.rows = in.readParcelable(RowsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SendMsgEntity> CREATOR = new Parcelable.Creator<SendMsgEntity>() {
        @Override
        public SendMsgEntity createFromParcel(Parcel source) {
            return new SendMsgEntity(source);
        }

        @Override
        public SendMsgEntity[] newArray(int size) {
            return new SendMsgEntity[size];
        }
    };
}
