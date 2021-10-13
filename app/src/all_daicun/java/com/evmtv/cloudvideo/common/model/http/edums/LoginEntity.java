package com.evmtv.cloudvideo.common.model.http.edums;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LoginEntity implements Parcelable {

    /**
     * code : 200
     * msg : 请求已经成功处理
     * total : 1
     * rows : [{"id":23,"name":"龚先生","sex":"0","age":20,"phone":"18758011562","classid":73748642921447427,"stbno":null,"createtime":1620356133000,"stbduration":0,"phoneduration":0,"ckstate":0,"password":"1234567","identifyCode":null,"sessionid":null,"repwd":null,"className":null,"areaid":null,"parentId":null,"areaIdMin":null,"areaIdMax":null}]
     */

    private int code;
    private String msg;
    private int total;
    private List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Parcelable {
        /**
         * id : 23
         * name : 龚先生
         * sex : 0
         * age : 20
         * phone : 18758011562
         * classid : 73748642921447427
         * stbno : null
         * createtime : 1620356133000
         * stbduration : 0
         * phoneduration : 0
         * ckstate : 0
         * password : 1234567
         * identifyCode : null
         * sessionid : null
         * repwd : null
         * className : null
         * areaid : null
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

        public String getStbno() {
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

        public Object getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Object getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public Object getAreaIdMin() {
            return areaIdMin;
        }

        public void setAreaIdMin(String areaIdMin) {
            this.areaIdMin = areaIdMin;
        }

        public Object getAreaIdMax() {
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
            this.stbno = in.readString();
            this.createtime = in.readLong();
            this.stbduration = in.readInt();
            this.phoneduration = in.readInt();
            this.ckstate = in.readInt();
            this.password = in.readString();
            this.identifyCode = in.readString();
            this.sessionid = in.readString();
            this.repwd = in.readString();
            this.className = in.readString();
            this.areaid = in.readString();
            this.parentId = in.readString();
            this.areaIdMin = in.readString();
            this.areaIdMax = in.readString();
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
        dest.writeTypedList(this.rows);
    }

    public LoginEntity() {
    }

    public LoginEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    protected LoginEntity(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.total = in.readInt();
        this.rows = in.createTypedArrayList(RowsBean.CREATOR);
    }

    public static final Parcelable.Creator<LoginEntity> CREATOR = new Parcelable.Creator<LoginEntity>() {
        @Override
        public LoginEntity createFromParcel(Parcel source) {
            return new LoginEntity(source);
        }

        @Override
        public LoginEntity[] newArray(int size) {
            return new LoginEntity[size];
        }
    };
}
