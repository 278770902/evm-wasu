package com.evmtv.cloudvideo.common.model.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Override public int compareTo(NavigationContentBean o) {
 * return ((Integer) this.order).compareTo(o.order);
 * }
 * zhongyuan:        MainBottomNavigationBean  order :  顺序
 */
public class NavigationBean implements Parcelable {
    /**
     * mainNavigation : {"navigationContent":[{"groupId":0,"itemId":0,"order":0,"type":"fragment","title":"首页","icon":"main_bottom_home_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"{\n  \"url\":\"file:///android_asset/www/index.html#/home\",\n  \"title\":\"首页\"\n}"}},{"groupId":0,"itemId":1,"order":1,"title":"智能家","type":"fragment","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MainTabFragment","content":{"startType":"LocalPage","value":"{\n  \"secondLevelNavigation\": [\n    {\n      \"groupId\": 0,\n      \"itemId\": 0,\n      \"order\": 0,\n      \"type\": \"tab\",\n      \"title\": \"首页\",\n      \"icon\": \"intelligent_home_kjb3x\",\n      \"childActivity\": \"com.evmtv.cloudvideo.common.view.LoginActivity\"\n    },\n    {\n      \"groupId\": 0,\n      \"itemId\": 1,\n      \"order\": 1,\n      \"title\": \"智能家\",\n      \"type\": \"tab\",\n      \"icon\": \"intelligent_home_qqt3x\",\n      \"childActivity\": \"com.evmtv.cloudvideo.common.view.LoginActivity\"\n    }\n  ]\n}\n"}},{"groupId":0,"itemId":3,"order":3,"type":"intent","title":"看电视","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MyFragment","content":{"startType":"StartSystemActivity","value":"{\n  \"url\":\"https://fir.im/1nse\",\n  \"action\":\"android.intent.action.VIEW\"\n}"}},{"groupId":0,"itemId":2,"order":2,"title":"大关","type":"fragment","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"{\n  \"url\":\"file:///android_asset/www/index.html#/share\",\n  \"title\":\"乡音\"\n}"}},{"groupId":0,"itemId":4,"order":4,"title":"我的","type":"fragment","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MyFragment","content":{"startType":"LocalPage"}}],"firstChoiceItemId":0}
     * addressPNS : shx.monitor.evm.cloud.henancatv.com:10554
     * addressDMS : shx.monitor.evm.cloud.henancatv.com:10554
     * addressUMS : shx.monitor.evm.cloud.henancatv.com:10554
     * AddressAUMS : shx.monitor.evm.cloud.henancatv.com:10554
     * AddressAUMSI : shx.monitor.evm.cloud.henancatv.com:10554
     * AddressVOICE : shx.monitor.evm.cloud.henancatv.com:10554
     * downloadAppUrl : http://evmapps.evmtv.com/az3j
     * xgAccessId : 1500002060
     * firAppId : 5ddcef74f94548235e06c39e
     * providerFileAuthorities : com.evmtv.cloudvideo.zhongyuan.fileprovider
     * h5port : 19005
     * versionName : zhongyuan
     * versionCode : 1.0
     */

    private MainNavigationBean mainNavigation;
    private String addressPNS;
    private String addressDMS;
    private String addressUMS;
    private String addressCSM;
    private String AddressAUMS;
    private String AddressAUMSI;
    private String AddressVOICE;
    private String downloadAppUrl;
    private String xgAccessId;
    private String firAppId;
    private String providerFileAuthorities;
    private String h5port;
    private String versionName;
    private String versionCode;
    private String logOutTitle;
    private String downloadAppQR;
    private String publicAccountGuid;
    private String startForgetPassWorldAction;
    private LoginTextOnClick loginTextOnClick;
    private List<GuideSplashBean> guideSplash;


    public String getStartForgetPassWorldAction() {
        return startForgetPassWorldAction;
    }

    public void setStartForgetPassWorldAction(String startForgetPassWorldAction) {
        this.startForgetPassWorldAction = startForgetPassWorldAction;
    }

    public String getPublicAccountGuid() {
        return publicAccountGuid;
    }

    public void setPublicAccountGuid(String publicAccountGuid) {
        this.publicAccountGuid = publicAccountGuid;
    }


    public LoginTextOnClick getLoginTextOnClick() {
        return loginTextOnClick;
    }

    public void setLoginTextOnClick(LoginTextOnClick loginTextOnClick) {
        this.loginTextOnClick = loginTextOnClick;
    }

    public List<GuideSplashBean> getGuideSplash() {
        return guideSplash;
    }

    public void setGuideSplash(List<GuideSplashBean> guideSplash) {
        this.guideSplash = guideSplash;
    }

    public static Creator<NavigationBean> getCREATOR() {
        return CREATOR;
    }

    public String getDownloadAppQR() {
        return downloadAppQR;
    }

    public String getAddressCSM() {
        return addressCSM;
    }

    public void setAddressCSM(String addressCSM) {
        this.addressCSM = addressCSM;
    }

    public void setDownloadAppQR(String downloadAppQR) {
        this.downloadAppQR = downloadAppQR;
    }

    public String getLogOutTitle() {
        return logOutTitle;
    }

    public void setLogOutTitle(String logOutTitle) {
        this.logOutTitle = logOutTitle;
    }

    public MainNavigationBean getMainNavigation() {
        return mainNavigation;
    }

    public void setMainNavigation(MainNavigationBean mainNavigation) {
        this.mainNavigation = mainNavigation;
    }

    public String getAddressPNS() {
        return addressPNS;
    }

    public void setAddressPNS(String addressPNS) {
        this.addressPNS = addressPNS;
    }

    public String getAddressDMS() {
        return addressDMS;
    }

    public void setAddressDMS(String addressDMS) {
        this.addressDMS = addressDMS;
    }

    public String getAddressUMS() {
        return addressUMS;
    }

    public void setAddressUMS(String addressUMS) {
        this.addressUMS = addressUMS;
    }

    public String getAddressAUMS() {
        return AddressAUMS;
    }

    public void setAddressAUMS(String AddressAUMS) {
        this.AddressAUMS = AddressAUMS;
    }

    public String getAddressAUMSI() {
        return AddressAUMSI;
    }

    public void setAddressAUMSI(String AddressAUMSI) {
        this.AddressAUMSI = AddressAUMSI;
    }

    public String getAddressVOICE() {
        return AddressVOICE;
    }

    public void setAddressVOICE(String AddressVOICE) {
        this.AddressVOICE = AddressVOICE;
    }

    public String getDownloadAppUrl() {
        return downloadAppUrl;
    }

    public void setDownloadAppUrl(String downloadAppUrl) {
        this.downloadAppUrl = downloadAppUrl;
    }

    public String getXgAccessId() {
        return xgAccessId;
    }

    public void setXgAccessId(String xgAccessId) {
        this.xgAccessId = xgAccessId;
    }

    public String getFirAppId() {
        return firAppId;
    }

    public void setFirAppId(String firAppId) {
        this.firAppId = firAppId;
    }

    public String getProviderFileAuthorities() {
        return providerFileAuthorities;
    }

    public void setProviderFileAuthorities(String providerFileAuthorities) {
        this.providerFileAuthorities = providerFileAuthorities;
    }

    public String getH5port() {
        return h5port;
    }

    public void setH5port(String h5port) {
        this.h5port = h5port;
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

    public static class MainNavigationBean implements Parcelable {
        /**
         * navigationContent : [{"groupId":0,"itemId":0,"order":0,"type":"fragment","title":"首页","icon":"main_bottom_home_selector","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"{\n  \"url\":\"file:///android_asset/www/index.html#/home\",\n  \"title\":\"首页\"\n}"}},{"groupId":0,"itemId":1,"order":1,"title":"智能家","type":"fragment","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MainTabFragment","content":{"startType":"LocalPage","value":"{\n  \"secondLevelNavigation\": [\n    {\n      \"groupId\": 0,\n      \"itemId\": 0,\n      \"order\": 0,\n      \"type\": \"tab\",\n      \"title\": \"首页\",\n      \"icon\": \"intelligent_home_kjb3x\",\n      \"childActivity\": \"com.evmtv.cloudvideo.common.view.LoginActivity\"\n    },\n    {\n      \"groupId\": 0,\n      \"itemId\": 1,\n      \"order\": 1,\n      \"title\": \"智能家\",\n      \"type\": \"tab\",\n      \"icon\": \"intelligent_home_qqt3x\",\n      \"childActivity\": \"com.evmtv.cloudvideo.common.view.LoginActivity\"\n    }\n  ]\n}\n"}},{"groupId":0,"itemId":3,"order":3,"type":"intent","title":"看电视","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MyFragment","content":{"startType":"StartSystemActivity","value":"{\n  \"url\":\"https://fir.im/1nse\",\n  \"action\":\"android.intent.action.VIEW\"\n}"}},{"groupId":0,"itemId":2,"order":2,"title":"大关","type":"fragment","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment","content":{"startType":"EWebView","value":"{\n  \"url\":\"file:///android_asset/www/index.html#/share\",\n  \"title\":\"乡音\"\n}"}},{"groupId":0,"itemId":4,"order":4,"title":"我的","type":"fragment","icon":"icon_message_on","childFragment":"com.evmtv.cloudvideo.common.view.fragment.MyFragment","content":{"startType":"LocalPage"}}]
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

        public static class NavigationContentBean implements Parcelable, Comparable<NavigationContentBean> {
            /**
             * groupId : 0
             * itemId : 0
             * order : 0
             * type : fragment
             * title : 首页
             * icon : main_bottom_home_selector
             * childFragment : com.evmtv.cloudvideo.common.view.fragment.EWebViewFragment
             * content : {"startType":"EWebView","value":"{\n  \"url\":\"file:///android_asset/www/index.html#/home\",\n  \"title\":\"首页\"\n}"}
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
            public int compareTo(NavigationContentBean o) {
                return ((Integer) this.order).compareTo(o.order);
            }

            public static class ContentBean implements Parcelable {
                /**
                 * startType : EWebView
                 * value : {
                 * "url":"file:///android_asset/www/index.html#/home",
                 * "title":"首页"
                 * }
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

            public static final Creator<NavigationContentBean> CREATOR = new Creator<NavigationContentBean>() {
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

        public MainNavigationBean() {
        }

        protected MainNavigationBean(Parcel in) {
            this.firstChoiceItemId = in.readInt();
            this.navigationContent = in.createTypedArrayList(NavigationContentBean.CREATOR);
        }

        public static final Creator<MainNavigationBean> CREATOR = new Creator<MainNavigationBean>() {
            @Override
            public MainNavigationBean createFromParcel(Parcel source) {
                return new MainNavigationBean(source);
            }

            @Override
            public MainNavigationBean[] newArray(int size) {
                return new MainNavigationBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mainNavigation, flags);
        dest.writeString(this.addressPNS);
        dest.writeString(this.addressDMS);
        dest.writeString(this.addressUMS);
        dest.writeString(this.AddressAUMS);
        dest.writeString(this.AddressAUMSI);
        dest.writeString(this.AddressVOICE);
        dest.writeString(this.downloadAppUrl);
        dest.writeString(this.xgAccessId);
        dest.writeString(this.firAppId);
        dest.writeString(this.providerFileAuthorities);
        dest.writeString(this.h5port);
        dest.writeString(this.versionName);
        dest.writeString(this.versionCode);
        dest.writeString(this.logOutTitle);
        dest.writeString(this.downloadAppQR);
        dest.writeString(this.addressCSM);
        dest.writeString(this.publicAccountGuid);
        dest.writeString(this.startForgetPassWorldAction);
        dest.writeParcelable(this.loginTextOnClick, flags);
        dest.writeList(this.guideSplash);
    }

    public NavigationBean() {
    }

    protected NavigationBean(Parcel in) {
        this.mainNavigation = in.readParcelable(MainNavigationBean.class.getClassLoader());
        this.addressPNS = in.readString();
        this.addressDMS = in.readString();
        this.addressUMS = in.readString();
        this.AddressAUMS = in.readString();
        this.AddressAUMSI = in.readString();
        this.AddressVOICE = in.readString();
        this.downloadAppUrl = in.readString();
        this.xgAccessId = in.readString();
        this.firAppId = in.readString();
        this.providerFileAuthorities = in.readString();
        this.h5port = in.readString();
        this.versionName = in.readString();
        this.versionCode = in.readString();
        this.logOutTitle = in.readString();
        this.downloadAppQR = in.readString();
        this.addressCSM = in.readString();
        this.publicAccountGuid = in.readString();
        this.startForgetPassWorldAction = in.readString();
        this.loginTextOnClick = in.readParcelable(LoginTextOnClick.class.getClassLoader());
        this.guideSplash = in.createTypedArrayList(GuideSplashBean.CREATOR);
    }

    public static final Creator<NavigationBean> CREATOR = new Creator<NavigationBean>() {
        @Override
        public NavigationBean createFromParcel(Parcel source) {
            return new NavigationBean(source);
        }

        @Override
        public NavigationBean[] newArray(int size) {
            return new NavigationBean[size];
        }
    };

    public static class LoginTextOnClick implements Parcelable {

        private List<ContextBean> loginContext;

        public List<ContextBean> getLoginContext() {
            return loginContext;
        }

        public void setLoginContext(List<ContextBean> loginContext) {
            this.loginContext = loginContext;
        }

        public static class ContextBean implements Parcelable {
            /**
             * text : 忘记密码
             * action : com.evmtv.cloudvideo.common.FORGET_PASS_WORLD_ACTIVITY
             */

            private String text;
            private String startType;
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


            public String getStartType() {
                return startType;
            }

            public void setStartType(String startType) {
                this.startType = startType;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.text);
                dest.writeString(this.action);
                dest.writeString(this.startType);
            }

            public ContextBean() {
            }

            protected ContextBean(Parcel in) {
                this.text = in.readString();
                this.action = in.readString();
                this.startType = in.readString();
            }

            public static final Creator<ContextBean> CREATOR = new Creator<ContextBean>() {
                @Override
                public ContextBean createFromParcel(Parcel source) {
                    return new ContextBean(source);
                }

                @Override
                public ContextBean[] newArray(int size) {
                    return new ContextBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.loginContext);
        }

        public LoginTextOnClick() {
        }

        protected LoginTextOnClick(Parcel in) {
            this.loginContext = new ArrayList<ContextBean>();
            in.readList(this.loginContext, ContextBean.class.getClassLoader());
        }

        public static final Creator<LoginTextOnClick> CREATOR = new Creator<LoginTextOnClick>() {
            @Override
            public LoginTextOnClick createFromParcel(Parcel source) {
                return new LoginTextOnClick(source);
            }

            @Override
            public LoginTextOnClick[] newArray(int size) {
                return new LoginTextOnClick[size];
            }
        };
    }

    public static class GuideSplashBean implements Parcelable ,Comparable<GuideSplashBean>{

        /**
         * order : 1
         * icon : wuhai_splash_a
         */

        private int order;
        private String icon;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.order);
            dest.writeString(this.icon);
        }

        public GuideSplashBean() {
        }

        protected GuideSplashBean(Parcel in) {
            this.order = in.readInt();
            this.icon = in.readString();
        }

        public static final Creator<GuideSplashBean> CREATOR = new Creator<GuideSplashBean>() {
            @Override
            public GuideSplashBean createFromParcel(Parcel source) {
                return new GuideSplashBean(source);
            }

            @Override
            public GuideSplashBean[] newArray(int size) {
                return new GuideSplashBean[size];
            }
        };

        @Override
        public int compareTo(@NonNull GuideSplashBean o) {
            return ((Integer) this.order).compareTo(o.order);
        }
    }
}