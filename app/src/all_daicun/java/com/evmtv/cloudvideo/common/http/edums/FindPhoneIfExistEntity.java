package com.evmtv.cloudvideo.common.http.edums;

import android.os.Parcel;
import android.os.Parcelable;

public class FindPhoneIfExistEntity implements Parcelable {

    /**
     * code : 200
     * msg : 请求已经成功处理
     * total : null
     * rows : {"id":26,"name":"李 123","sex":"1","age":30,"phone":"13072125607","classid":74034524534603780,"stbno":null,"createtime":1620698551000,"stbduration":0,"phoneduration":0,"ckstate":0,"password":"123456","identifyCode":null,"sessionid":null,"repwd":null,"className":"中国我的小学1六年级2班","areaid":"74034524534603780","parentId":null,"areaIdMin":null,"areaIdMax":null}
     */

    private int code;
    private String msg;
    private int total;
    private RowsBean rows;

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
         * id : 26
         * name : 李 123
         * sex : 1
         * age : 30
         * phone : 13072125607
         * classid : 74034524534603780
         * stbno : null
         * createtime : 1620698551000
         * stbduration : 0
         * phoneduration : 0
         * ckstate : 0
         * password : 123456
         * identifyCode : null
         * sessionid : null
         * repwd : null
         * className : 中国我的小学1六年级2班
         * areaid : 74034524534603780
         * parentId : null
         * areaIdMin : null
         * areaIdMax : null
         */

        private int id;
        private String name;
        private String sex;
        private int age;
        private String phone;
        private long classid;
        private String stbno;
        private long createtime;
        private int stbduration;
        private int phoneduration;
        private int ckstate;
        private String password;
        private String identifyCode;
        private String sessionid;
        private String repwd;
        private String className;
        private String areaid;
        private String parentId;
        private String areaIdMin;
        private String areaIdMax;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getClassid() {
            return classid;
        }

        public void setClassid(long classid) {
            this.classid = classid;
        }

        public Object getStbno() {
            return stbno;
        }

        public void setStbno(String stbno) {
            this.stbno = stbno;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getStbduration() {
            return stbduration;
        }

        public void setStbduration(int stbduration) {
            this.stbduration = stbduration;
        }

        public int getPhoneduration() {
            return phoneduration;
        }

        public void setPhoneduration(int phoneduration) {
            this.phoneduration = phoneduration;
        }

        public int getCkstate() {
            return ckstate;
        }

        public void setCkstate(int ckstate) {
            this.ckstate = ckstate;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getIdentifyCode() {
            return identifyCode;
        }

        public void setIdentifyCode(String identifyCode) {
            this.identifyCode = identifyCode;
        }

        public Object getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        public Object getRepwd() {
            return repwd;
        }

        public void setRepwd(String repwd) {
            this.repwd = repwd;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getAreaIdMin() {
            return areaIdMin;
        }

        public void setAreaIdMin(String areaIdMin) {
            this.areaIdMin = areaIdMin;
        }

        public String getAreaIdMax() {
            return areaIdMax;
        }

        public void setAreaIdMax(String areaIdMax) {
            this.areaIdMax = areaIdMax;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.sex);
            dest.writeInt(this.age);
            dest.writeString(this.phone);
            dest.writeLong(this.classid);
            dest.writeString(this.stbno);
            dest.writeLong(this.createtime);
            dest.writeInt(this.stbduration);
            dest.writeInt(this.phoneduration);
            dest.writeInt(this.ckstate);
            dest.writeString(this.password);
            dest.writeString(this.identifyCode);
            dest.writeString(this.sessionid);
            dest.writeString(this.repwd);
            dest.writeString(this.className);
            dest.writeString(this.areaid);
            dest.writeString(this.parentId);
            dest.writeString(this.areaIdMin);
            dest.writeString(this.areaIdMax);
        }

        public RowsBean() {
        }

        protected RowsBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.sex = in.readString();
            this.age = in.readInt();
            this.phone = in.readString();
            this.classid = in.readLong();
            this.stbno = in.readParcelable(Object.class.getClassLoader());
            this.createtime = in.readLong();
            this.stbduration = in.readInt();
            this.phoneduration = in.readInt();
            this.ckstate = in.readInt();
            this.password = in.readString();
            this.identifyCode = in.readParcelable(Object.class.getClassLoader());
            this.sessionid = in.readParcelable(Object.class.getClassLoader());
            this.repwd = in.readParcelable(Object.class.getClassLoader());
            this.className = in.readString();
            this.areaid = in.readString();
            this.parentId = in.readParcelable(Object.class.getClassLoader());
            this.areaIdMin = in.readParcelable(Object.class.getClassLoader());
            this.areaIdMax = in.readParcelable(Object.class.getClassLoader());
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

    public FindPhoneIfExistEntity() {
    }

    protected FindPhoneIfExistEntity(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.total = in.readInt();
        this.rows = in.readParcelable(RowsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FindPhoneIfExistEntity> CREATOR = new Parcelable.Creator<FindPhoneIfExistEntity>() {
        @Override
        public FindPhoneIfExistEntity createFromParcel(Parcel source) {
            return new FindPhoneIfExistEntity(source);
        }

        @Override
        public FindPhoneIfExistEntity[] newArray(int size) {
            return new FindPhoneIfExistEntity[size];
        }
    };
}
