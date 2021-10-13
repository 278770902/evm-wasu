package com.evmtv.cloudvideo.common.model.http.ums;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BindUserEntity implements Parcelable {

    /**
     * STBUserJson : {"name":"陶继排","GUID":"45000000004695","imageUrl":""}
     * GUID : 45000000004696
     * ClientUserArray : [{"name":"tjp123","GUID":"45000000004696","TEL":"18278169565","imageUrl":"","receiveMessageWhenOffline":1,"isMainClient":1},{"name":"小荣","GUID":"45000000001461","TEL":"18177701112","imageUrl":"","receiveMessageWhenOffline":1,"isMainClient":0},{"name":"dxgdwl","GUID":"45000000001464","TEL":"18607808446","imageUrl":"","receiveMessageWhenOffline":1,"isMainClient":0},{"name":"文33","GUID":"45000000004397","TEL":"18321549590","imageUrl":"","receiveMessageWhenOffline":1,"isMainClient":0}]
     * isSuccess : 1
     */

    private STBUserJsonBean STBUserJson;
    private String GUID;
    private int isSuccess;
    private List<ClientUserArrayBean> ClientUserArray;

    public STBUserJsonBean getSTBUserJson() {
        return STBUserJson;
    }

    public void setSTBUserJson(STBUserJsonBean STBUserJson) {
        this.STBUserJson = STBUserJson;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<ClientUserArrayBean> getClientUserArray() {
        return ClientUserArray;
    }

    public void setClientUserArray(List<ClientUserArrayBean> ClientUserArray) {
        this.ClientUserArray = ClientUserArray;
    }

    public static class STBUserJsonBean implements Parcelable {
        /**
         * name : 陶继排
         * GUID : 45000000004695
         * imageUrl :
         */

        private String name;
        private String GUID;
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
            dest.writeString(this.imageUrl);
        }

        public STBUserJsonBean() {
        }

        protected STBUserJsonBean(Parcel in) {
            this.name = in.readString();
            this.GUID = in.readString();
            this.imageUrl = in.readString();
        }

        public static final Parcelable.Creator<STBUserJsonBean> CREATOR = new Parcelable.Creator<STBUserJsonBean>() {
            @Override
            public STBUserJsonBean createFromParcel(Parcel source) {
                return new STBUserJsonBean(source);
            }

            @Override
            public STBUserJsonBean[] newArray(int size) {
                return new STBUserJsonBean[size];
            }
        };
    }

    public static class ClientUserArrayBean implements Parcelable {
        /**
         * name : tjp123
         * GUID : 45000000004696
         * TEL : 18278169565
         * imageUrl :
         * receiveMessageWhenOffline : 1
         * isMainClient : 1
         */

        private String name;
        private String GUID;
        private String TEL;
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
            dest.writeString(this.imageUrl);
            dest.writeInt(this.receiveMessageWhenOffline);
            dest.writeInt(this.isMainClient);
        }

        public ClientUserArrayBean() {
        }

        protected ClientUserArrayBean(Parcel in) {
            this.name = in.readString();
            this.GUID = in.readString();
            this.TEL = in.readString();
            this.imageUrl = in.readString();
            this.receiveMessageWhenOffline = in.readInt();
            this.isMainClient = in.readInt();
        }

        public static final Parcelable.Creator<ClientUserArrayBean> CREATOR = new Parcelable.Creator<ClientUserArrayBean>() {
            @Override
            public ClientUserArrayBean createFromParcel(Parcel source) {
                return new ClientUserArrayBean(source);
            }

            @Override
            public ClientUserArrayBean[] newArray(int size) {
                return new ClientUserArrayBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.STBUserJson, flags);
        dest.writeString(this.GUID);
        dest.writeInt(this.isSuccess);
        dest.writeTypedList(this.ClientUserArray);
    }

    public BindUserEntity() {
    }

    public BindUserEntity(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    protected BindUserEntity(Parcel in) {
        this.STBUserJson = in.readParcelable(STBUserJsonBean.class.getClassLoader());
        this.GUID = in.readString();
        this.isSuccess = in.readInt();
        this.ClientUserArray = in.createTypedArrayList(ClientUserArrayBean.CREATOR);
    }

    public static final Parcelable.Creator<BindUserEntity> CREATOR = new Parcelable.Creator<BindUserEntity>() {
        @Override
        public BindUserEntity createFromParcel(Parcel source) {
            return new BindUserEntity(source);
        }

        @Override
        public BindUserEntity[] newArray(int size) {
            return new BindUserEntity[size];
        }
    };
}
