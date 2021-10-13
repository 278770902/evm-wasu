package com.evmtv.cloudvideo.common.model.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

public class WeChatBean implements Parcelable {
    /**
     * weChatNavigation : {"navigationContent":[{"groupId":0,"itemId":0,"order":0,"type":"fragment","title":"通话记录","icon":"main_bottom_home_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"main/navigationMain.json"}},{"groupId":0,"itemId":1,"order":1,"title":"我的好友","type":"fragment","icon":"main_bottom_intelligent_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MainTabFragment","content":{"startType":"LocalPage","value":"main/navigationHome.json"}},{"groupId":0,"itemId":3,"order":3,"type":"fragment","title":"好友申请","icon":"icon_watch","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MyFragment","content":{"startType":"StartSystemActivity","value":"main/navigationWatchTV.json"}},{"groupId":0,"itemId":2,"order":2,"title":"我的收藏","type":"fragment","icon":"main_bottom_share_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"main/navigationShare.json"}}],"firstChoiceItemId":0}
     * title : 亲情通
     * versionName : zhongyuan
     * versionCode : 1.0
     */
    private WeChatNavigationBean weChatNavigation;
    private String title;
    private String versionName;
    private String versionCode;

    public WeChatNavigationBean getWeChatNavigation() {
        return weChatNavigation;
    }

    public void setWeChatNavigation(WeChatNavigationBean weChatNavigation) {
        this.weChatNavigation = weChatNavigation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public static class WeChatNavigationBean implements Parcelable {
        /**
         * navigationContent : [{"groupId":0,"itemId":0,"order":0,"type":"fragment","title":"通话记录","icon":"main_bottom_home_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"main/navigationMain.json"}},{"groupId":0,"itemId":1,"order":1,"title":"我的好友","type":"fragment","icon":"main_bottom_intelligent_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MainTabFragment","content":{"startType":"LocalPage","value":"main/navigationHome.json"}},{"groupId":0,"itemId":3,"order":3,"type":"fragment","title":"好友申请","icon":"icon_watch","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MyFragment","content":{"startType":"StartSystemActivity","value":"main/navigationWatchTV.json"}},{"groupId":0,"itemId":2,"order":2,"title":"我的收藏","type":"fragment","icon":"main_bottom_share_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"main/navigationShare.json"}}]
         * firstChoiceItemId : 0
         */

        private int firstChoiceItemId;
        private List<NavigationContentBean> navigationContent;

        public int getFirstChoiceItemId() {
            return firstChoiceItemId;
        }

        public void setFirstChoiceItemId(int firstChoiceItemId) {
            this.firstChoiceItemId = firstChoiceItemId;
        }

        public List<NavigationContentBean> getNavigationContent() {
            return navigationContent;
        }

        public void setNavigationContent(List<NavigationContentBean> navigationContent) {
            this.navigationContent = navigationContent;
        }

        public static class NavigationContentBean implements Parcelable ,Comparable<NavigationContentBean>{
            /**
             * groupId : 0
             * itemId : 0
             * order : 0
             * type : fragment
             * title : 通话记录
             * icon : main_bottom_home_selector
             * childFragment : com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment
             * content : {"startType":"EWebView","value":"main/navigationMain.json"}
             */

            private int groupId;
            private int itemId;
            private int order;
            private String type;
            private String title;
            private String icon;
            private String childFragment;
            private ContentBean content;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getChildFragment() {
                return childFragment;
            }

            public void setChildFragment(String childFragment) {
                this.childFragment = childFragment;
            }

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            @Override
            public int compareTo(@NonNull NavigationContentBean o) {
                return ((Integer) this.order).compareTo(o.order);
            }

            public static class ContentBean implements Parcelable {
                /**
                 * startType : EWebView
                 * value : main/navigationMain.json
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

                public static final Parcelable.Creator<ContentBean> CREATOR = new Parcelable.Creator<ContentBean>() {
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
                dest.writeInt(this.groupId);
                dest.writeInt(this.itemId);
                dest.writeInt(this.order);
                dest.writeString(this.type);
                dest.writeString(this.title);
                dest.writeString(this.icon);
                dest.writeString(this.childFragment);
                dest.writeParcelable(this.content, flags);
            }

            public NavigationContentBean() {
            }

            protected NavigationContentBean(Parcel in) {
                this.groupId = in.readInt();
                this.itemId = in.readInt();
                this.order = in.readInt();
                this.type = in.readString();
                this.title = in.readString();
                this.icon = in.readString();
                this.childFragment = in.readString();
                this.content = in.readParcelable(ContentBean.class.getClassLoader());
            }

            public static final Parcelable.Creator<NavigationContentBean> CREATOR = new Parcelable.Creator<NavigationContentBean>() {
                @Override
                public NavigationContentBean createFromParcel(Parcel source) {
                    return new NavigationContentBean(source);
                }

                @Override
                public NavigationContentBean[] newArray(int size) {
                    return new NavigationContentBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.firstChoiceItemId);
            dest.writeTypedList(this.navigationContent);
        }

        public WeChatNavigationBean() {
        }

        protected WeChatNavigationBean(Parcel in) {
            this.firstChoiceItemId = in.readInt();
            this.navigationContent = in.createTypedArrayList(NavigationContentBean.CREATOR);
        }

        public static final Parcelable.Creator<WeChatNavigationBean> CREATOR = new Parcelable.Creator<WeChatNavigationBean>() {
            @Override
            public WeChatNavigationBean createFromParcel(Parcel source) {
                return new WeChatNavigationBean(source);
            }

            @Override
            public WeChatNavigationBean[] newArray(int size) {
                return new WeChatNavigationBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.weChatNavigation, flags);
        dest.writeString(this.title);
        dest.writeString(this.versionName);
        dest.writeString(this.versionCode);
    }

    public WeChatBean() {
    }

    protected WeChatBean(Parcel in) {
        this.weChatNavigation = in.readParcelable(WeChatNavigationBean.class.getClassLoader());
        this.title = in.readString();
        this.versionName = in.readString();
        this.versionCode = in.readString();
    }

    public static final Parcelable.Creator<WeChatBean> CREATOR = new Parcelable.Creator<WeChatBean>() {
        @Override
        public WeChatBean createFromParcel(Parcel source) {
            return new WeChatBean(source);
        }

        @Override
        public WeChatBean[] newArray(int size) {
            return new WeChatBean[size];
        }
    };
}
