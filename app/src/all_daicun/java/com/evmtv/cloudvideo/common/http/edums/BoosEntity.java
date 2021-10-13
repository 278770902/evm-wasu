package com.evmtv.cloudvideo.common.http.edums;

import android.os.Parcel;
import android.os.Parcelable;

public class BoosEntity implements Parcelable {

    /**
     * code : 200
     * msg : 请求已经成功处理
     * total : null
     * rows : {"code":"04","message":"手机号:13072125607,智能卡号:123456,解绑成功"}
     */

    private int code;
    private String msg;
    private Integer total;
    private String rows;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeValue(this.total);
        dest.writeString(this.rows);
    }

    public BoosEntity() {
    }

    public BoosEntity(int code) {
        this.code = code;
    }

    protected BoosEntity(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rows = in.readString();
    }

    public static final Creator<BoosEntity> CREATOR = new Creator<BoosEntity>() {
        @Override
        public BoosEntity createFromParcel(Parcel source) {
            return new BoosEntity(source);
        }

        @Override
        public BoosEntity[] newArray(int size) {
            return new BoosEntity[size];
        }
    };
}
