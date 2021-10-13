package com.evmtv.cloudvideo.common.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

public class IntentLocalBean implements Parcelable {

    /**
     * url : file:///android_asset/www/index.html#/home
     * title : 页面名称
     */


    private String url;
    private String title;
    private String action;
    private Boolean startRefresh;

    public IntentLocalBean(String url, String title, String action, boolean startRefresh) {
        this.url = url;
        this.title = title;
        this.action = action;
        this.startRefresh = startRefresh;
    }

    public IntentLocalBean(String url, String title, String action) {
        this(url, title, action, true);
    }

    public Boolean isStartRefresh() {
        return startRefresh;
    }

    public void setStartRefresh(Boolean startRefresh) {
        this.startRefresh = startRefresh;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IntentLocalBean() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.action);
        dest.writeValue(this.startRefresh);
    }

    protected IntentLocalBean(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
        this.action = in.readString();
        this.startRefresh = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<IntentLocalBean> CREATOR = new Creator<IntentLocalBean>() {
        @Override
        public IntentLocalBean createFromParcel(Parcel source) {
            return new IntentLocalBean(source);
        }

        @Override
        public IntentLocalBean[] newArray(int size) {
            return new IntentLocalBean[size];
        }
    };
}
