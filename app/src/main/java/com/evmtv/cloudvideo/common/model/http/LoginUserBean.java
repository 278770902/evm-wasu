package com.evmtv.cloudvideo.common.model.http;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginUserBean implements Parcelable {

    private PassWorldLogin passWorldLogin;
    private TelLogin telLogin;

    public PassWorldLogin getPassWorldLogin() {
        return passWorldLogin;
    }

    public void setPassWorldLogin(PassWorldLogin passWorldLogin) {
        this.passWorldLogin = passWorldLogin;
    }

    public TelLogin getTelLogin() {
        return telLogin;
    }

    public void setTelLogin(TelLogin telLogin) {
        this.telLogin = telLogin;
    }

    //帐号密码登录
    public class PassWorldLogin implements Parcelable {
        private String UserName;
        private String UserPassWorld;
        private String DeviceToken;


        public String getDeviceToken() {
            return DeviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            DeviceToken = deviceToken;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getUserPassWorld() {
            return UserPassWorld;
        }

        public void setUserPassWorld(String userPassWorld) {
            UserPassWorld = userPassWorld;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.UserName);
            dest.writeString(this.UserPassWorld);
            dest.writeString(this.DeviceToken);
        }

        public PassWorldLogin() {
        }

        public PassWorldLogin(String userName, String userPassWorld) {
            this.UserName = userName;
            this.UserPassWorld = userPassWorld;
        }

        protected PassWorldLogin(Parcel in) {
            this.UserName = in.readString();
            this.UserPassWorld = in.readString();
            this.DeviceToken = in.readString();
        }

        public final Creator<PassWorldLogin> CREATOR = new Creator<PassWorldLogin>() {
            @Override
            public PassWorldLogin createFromParcel(Parcel source) {
                return new PassWorldLogin(source);
            }

            @Override
            public PassWorldLogin[] newArray(int size) {
                return new PassWorldLogin[size];
            }
        };
    }

    //手机验证码登录
    public class TelLogin implements Parcelable {
        private String Tel;
        private int code;

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.Tel);
            dest.writeInt(this.code);
        }

        public TelLogin(String tel, int code) {
            Tel = tel;
            this.code = code;
        }

        public TelLogin() {
        }

        protected TelLogin(Parcel in) {
            this.Tel = in.readString();
            this.code = in.readInt();
        }

        public final Creator<TelLogin> CREATOR = new Creator<TelLogin>() {
            @Override
            public TelLogin createFromParcel(Parcel source) {
                return new TelLogin(source);
            }

            @Override
            public TelLogin[] newArray(int size) {
                return new TelLogin[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.passWorldLogin, flags);
        dest.writeParcelable((Parcelable) this.telLogin, flags);
    }

    public LoginUserBean(String userName, String PassWorld) {
        if (passWorldLogin == null)
            passWorldLogin = new PassWorldLogin();
        passWorldLogin.setUserName(userName);
        passWorldLogin.setUserPassWorld(PassWorld);
    }


    public LoginUserBean(String tel, int code) {
        if (telLogin == null)
            telLogin = new TelLogin();
        telLogin.setTel(tel);
        telLogin.setCode(code);
    }


    protected LoginUserBean(Parcel in) {
        this.passWorldLogin = in.readParcelable(PassWorldLogin.class.getClassLoader());
        this.telLogin = in.readParcelable(TelLogin.class.getClassLoader());
    }

    public static final Parcelable.Creator<LoginUserBean> CREATOR = new Parcelable.Creator<LoginUserBean>() {
        @Override
        public LoginUserBean createFromParcel(Parcel source) {
            return new LoginUserBean(source);
        }

        @Override
        public LoginUserBean[] newArray(int size) {
            return new LoginUserBean[size];
        }
    };
}
