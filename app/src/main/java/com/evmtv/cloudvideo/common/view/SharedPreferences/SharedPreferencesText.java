package com.evmtv.cloudvideo.common.view.SharedPreferences;

public interface SharedPreferencesText {
    //第一次安装app
    String IS_FIRST_START = "is first start";
    //登录用户GUID
    String LOGIN_CPN_USER_GUID = "userGUID";

    String LOGIN_CPN_DEVICE_TOKEN = "device token";
    //用户别名
    String LOGIN_CPN_USER_NAME = "userName";
    //性别 0男 1女
    String LOGIN_CPN_USER_SEX = "sex";
    //年龄
    String LOGIN_CPN_USER_AGE = "age";
    //班级
    String LOGIN_CPN_USER_CLASS = "class";
    //cpn登录帐号
    String LOGIN_CPN_USER_LOGIN_NAME = "userLoginName";
    //cpn登录密码
    String LOGIN_CPN_USER_LOGIN_PASS_WORLD = "userLoginPassword";
    //aumsAddress
    String AUMS_ADDRESS = "AUMS Address";
    //pnsAddress
    String PNS_ADDRESS = "PNS Address";
    //umsAddress
    String UMS_ADDRESS = "UMS Address";
    //csmAddress
    String CSM_ADDRESS = "CSM Address";
    //AUMSI address
    String AUMSI_ADDRESS = "AUMS address";
    //DMS address
    String DMS_ADDRESS = "DMS address";
    //H5 Port
    String H5_Port = "H5 Port";
    //ums user Icon
    String LOGIN_UMS_USER_ICON = "user icon";
    //ums 用户电话
    String LOGIN_UMS_USER_TEL = "user tel";
    //session id
    String LOGIN_SESSION_ID = "session id";
    //ckstate 审核状态 0：未审核 1：审核通过 2：审核未通过
    String LOGIN_CK_STATE = "ck state";

    String APP_FIRST_OPEN = "first open";
}
