package com.evmtv.cloudvideo.common.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StartRegisterActionEntity implements Parcelable {

    /**
     * actionArr : [{"text":"扫描区域码注册","action":"com.evmtv.cloudvideo.common.FORGET_PASS_WORLD_ACTIVITY"},{"text":"选择区域码注册","action":"com.evmtv.cloudvideo.common.FORGET_PASS_WORLD_ACTIVITY"}]
     * directText : 注册
     * DirectStartAction : com.evmtv.cloudvideo.common.LoginRegisterActivity
     */

    private String directText;
    private String DirectStartAction;
    private List<ActionArrBean> actionArr;

    public String getDirectText() {
        return directText;
    }

    public void setDirectText(String directText) {
        this.directText = directText;
    }

    public String getDirectStartAction() {
        return DirectStartAction;
    }

    public void setDirectStartAction(String DirectStartAction) {
        this.DirectStartAction = DirectStartAction;
    }

    public List<ActionArrBean> getActionArr() {
        return actionArr;
    }

    public void setActionArr(List<ActionArrBean> actionArr) {
        this.actionArr = actionArr;
    }

    public static class ActionArrBean implements Parcelable {
        /**
         * text : 扫描区域码注册
         * action : com.evmtv.cloudvideo.common.FORGET_PASS_WORLD_ACTIVITY
         */

        private String text;
        private String action;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.text);
            dest.writeString(this.action);
        }

        public ActionArrBean() {
        }

        protected ActionArrBean(Parcel in) {
            this.text = in.readString();
            this.action = in.readString();
        }

        public static final Creator<ActionArrBean> CREATOR = new Creator<ActionArrBean>() {
            @Override
            public ActionArrBean createFromParcel(Parcel source) {
                return new ActionArrBean(source);
            }

            @Override
            public ActionArrBean[] newArray(int size) {
                return new ActionArrBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.directText);
        dest.writeString(this.DirectStartAction);
        dest.writeTypedList(this.actionArr);
    }

    public StartRegisterActionEntity() {
    }

    protected StartRegisterActionEntity(Parcel in) {
        this.directText = in.readString();
        this.DirectStartAction = in.readString();
        this.actionArr = in.createTypedArrayList(ActionArrBean.CREATOR);
    }

    public static final Creator<StartRegisterActionEntity> CREATOR = new Creator<StartRegisterActionEntity>() {
        @Override
        public StartRegisterActionEntity createFromParcel(Parcel source) {
            return new StartRegisterActionEntity(source);
        }

        @Override
        public StartRegisterActionEntity[] newArray(int size) {
            return new StartRegisterActionEntity[size];
        }
    };
}

