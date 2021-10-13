package com.evmtv.cloudvideo.common.model.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

public class MySettingInfoEntity implements Parcelable {

    private String userAgreement;
    private String privacyPolicy;
    private String editPersonalAction;
    private String backgroundIcon;
    private List<LevelOneBean> levelOne;
    private List<LevelTwoBean> levelTwo;

    public String getEditPersonalAction() {
        return editPersonalAction;
    }

    public void setEditPersonalAction(String editPersonalAction) {
        this.editPersonalAction = editPersonalAction;
    }

    public String getBackgroundIcon() {
        return backgroundIcon;
    }

    public void setBackgroundIcon(String backgroundIcon) {
        this.backgroundIcon = backgroundIcon;
    }

    public String getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(String userAgreement) {
        this.userAgreement = userAgreement;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public List<LevelOneBean> getLevelOne() {
        return levelOne;
    }

    public void setLevelOne(List<LevelOneBean> levelOne) {
        this.levelOne = levelOne;
    }

    public List<LevelTwoBean> getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(List<LevelTwoBean> levelTwo) {
        this.levelTwo = levelTwo;
    }

    public static class LevelOneBean implements Parcelable, Comparable<LevelOneBean> {
        /**
         * name : 我的收藏
         * icon : icon_message_on
         * groupId : 0
         * itemId : 0
         * order : 0
         * content : {"startType":"StartLocalActivity","value":""}
         */

        private String name;
        private String icon;
        private int groupId;
        private int itemId;
        private int order;
        private ContentBean content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        @Override
        public int compareTo(@NonNull LevelOneBean o) {
            return ((Integer) this.order).compareTo(o.order);
        }

        public static class ContentBean implements Parcelable {
            /**
             * startType : StartLocalActivity
             * value :
             */

            private String startType;
            private String value;

            public String getStartType() {
                return startType;
            }

            public void setStartType(String startType) {
                this.startType = startType;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.startType);
                dest.writeString(this.value);
            }

            public ContentBean() {
            }

            protected ContentBean(Parcel in) {
                this.startType = in.readString();
                this.value = in.readString();
            }

            public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
                @Override
                public ContentBean createFromParcel(Parcel source) {
                    return new ContentBean(source);
                }

                @Override
                public ContentBean[] newArray(int size) {
                    return new ContentBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.icon);
            dest.writeInt(this.groupId);
            dest.writeInt(this.itemId);
            dest.writeInt(this.order);
            dest.writeParcelable(this.content, flags);
        }

        public LevelOneBean() {
        }

        protected LevelOneBean(Parcel in) {
            this.name = in.readString();
            this.icon = in.readString();
            this.groupId = in.readInt();
            this.itemId = in.readInt();
            this.order = in.readInt();
            this.content = in.readParcelable(ContentBean.class.getClassLoader());
        }

        public static final Creator<LevelOneBean> CREATOR = new Creator<LevelOneBean>() {
            @Override
            public LevelOneBean createFromParcel(Parcel source) {
                return new LevelOneBean(source);
            }

            @Override
            public LevelOneBean[] newArray(int size) {
                return new LevelOneBean[size];
            }
        };
    }

    public static class LevelTwoBean implements Parcelable, Comparable<LevelTwoBean> {
        /**
         * name : 版本升级
         * icon : icon_message_on
         * groupId : 0
         * itemId : 0
         * order : 0
         * content : {"startType":"StartLocalActivity","value":""}
         */

        private String name;
        private String icon;
        private int groupId;
        private int itemId;
        private int order;
        private ContentBeanX content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public ContentBeanX getContent() {
            return content;
        }

        public void setContent(ContentBeanX content) {
            this.content = content;
        }

        @Override
        public int compareTo(@NonNull LevelTwoBean o) {
            return ((Integer) this.order).compareTo(o.order);
        }

        public static class ContentBeanX implements Parcelable {
            /**
             * startType : StartLocalActivity
             * value :
             */

            private String startType;
            private String value;

            public String getStartType() {
                return startType;
            }

            public void setStartType(String startType) {
                this.startType = startType;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.startType);
                dest.writeString(this.value);
            }

            public ContentBeanX() {
            }

            protected ContentBeanX(Parcel in) {
                this.startType = in.readString();
                this.value = in.readString();
            }

            public static final Creator<ContentBeanX> CREATOR = new Creator<ContentBeanX>() {
                @Override
                public ContentBeanX createFromParcel(Parcel source) {
                    return new ContentBeanX(source);
                }

                @Override
                public ContentBeanX[] newArray(int size) {
                    return new ContentBeanX[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.icon);
            dest.writeInt(this.groupId);
            dest.writeInt(this.itemId);
            dest.writeInt(this.order);
            dest.writeParcelable(this.content, flags);
        }

        public LevelTwoBean() {
        }

        protected LevelTwoBean(Parcel in) {
            this.name = in.readString();
            this.icon = in.readString();
            this.groupId = in.readInt();
            this.itemId = in.readInt();
            this.order = in.readInt();
            this.content = in.readParcelable(ContentBeanX.class.getClassLoader());
        }

        public static final Creator<LevelTwoBean> CREATOR = new Creator<LevelTwoBean>() {
            @Override
            public LevelTwoBean createFromParcel(Parcel source) {
                return new LevelTwoBean(source);
            }

            @Override
            public LevelTwoBean[] newArray(int size) {
                return new LevelTwoBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userAgreement);
        dest.writeString(this.privacyPolicy);
        dest.writeTypedList(this.levelOne);
        dest.writeTypedList(this.levelTwo);
        dest.writeString(this.editPersonalAction);
        dest.writeString(this.backgroundIcon);
    }

    public MySettingInfoEntity() {
    }

    protected MySettingInfoEntity(Parcel in) {
        this.userAgreement = in.readString();
        this.privacyPolicy = in.readString();
        this.levelOne = in.createTypedArrayList(LevelOneBean.CREATOR);
        this.levelTwo = in.createTypedArrayList(LevelTwoBean.CREATOR);
        this.editPersonalAction = in.readString();
        this.backgroundIcon = in.readString();
    }

    public static final Creator<MySettingInfoEntity> CREATOR = new Creator<MySettingInfoEntity>() {
        @Override
        public MySettingInfoEntity createFromParcel(Parcel source) {
            return new MySettingInfoEntity(source);
        }

        @Override
        public MySettingInfoEntity[] newArray(int size) {
            return new MySettingInfoEntity[size];
        }
    };
}
