package com.evmtv.cloudvideo.common.http.edums;

import com.evmtv.cloudvideo.common.persenter.LoginOutTool;
import com.evmtv.cloudvideo.common.persenter.login.RetrofitUtilExclusive;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class EdumsInteractive {
    private static EdumsInteractive instance = null;
    private String TAG = getClass().getName();

    static {
        instance = new EdumsInteractive();
    }

    public static EdumsInteractive getInstance() {
        return instance;
    }

    public String login(String userId, String passworld) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .login(userId, passworld));
    }

    public String login(String tel, @Query("identifyCode") String identifyCode, @Query("sessionid") String sessionId) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .login(tel, identifyCode, sessionId));
    }


    public String logout(String userId) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .logout(userId));
    }


    public String getAreaTree() {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .getAreaTree());
    }

    public String sendMsg(String phone) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .sendMsg(phone));
    }

    public String register(String name, String sex
            , String age, String phone
            , String password, String pwd2
            , String classid, String identifyCode
            , String sessionid) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .register(name, sex, age, phone, password, pwd2, classid, identifyCode, sessionid));
    }

    public String updateInfo(String password, String name
            , String sex, String age
            , String classid, String id, String tel, String session) {
        String json = execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .updateInfo(password, name, sex, age, classid, id, tel, session));
        return LoginOutTool.SendLoginOut(json) ? "loginOut" : json;
    }

    public String findPhoneIfExist(String telePhone, String telePhone1
            , String ssoToken) {
        String json = execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .findPhoneIfExist(telePhone, telePhone1, ssoToken));
        LoginOutTool.SendLoginOut(json);
        return LoginOutTool.SendLoginOut(json) ? "loginOut" : json;
    }

    public String findPhoneIfExist(String telePhone) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .findPhoneIfExist(telePhone));
    }


    public String updatePwdByPhone(String phone, String pwd1, String pwd2, String identifyCode, String sessionid
            , String telePhone1
            , String ssoToken) {
        String json = execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .updatePwdByPhone(phone, pwd1, pwd2, identifyCode, sessionid, telePhone1, ssoToken));
        return LoginOutTool.SendLoginOut(json) ? "loginOut" : json;
    }

    public String boosBinCard(String phone, String cardno, String requestURL, String telePhone1
            , String ssoToken) {
        String json = execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .boosBinCard(phone, cardno, requestURL, telePhone1, ssoToken));
        return LoginOutTool.SendLoginOut(json) ? "loginOut" : json;
    }

    public String boosCheckCard(String phone, String requestURL) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .boosCheckCard(phone, requestURL));
    }

    public String boosReBinCard(String phone, String cardno, String requestURL, String telePhone1
            , String ssoToken) {
        String json = execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .boosReBinCard(phone, cardno, requestURL, telePhone1, ssoToken));
        return LoginOutTool.SendLoginOut(json) ? "loginOut" : json;
    }

    public String boosCheckphone(String phone, String telePhone1
            , String ssoToken) {
        String json = execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .boosCheckphone(phone, telePhone1, ssoToken));
        return LoginOutTool.SendLoginOut(json) ? "loginOut" : json;
    }

    public String addPlayHistory(String phoneduration, String id) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .addPlayHistory(phoneduration, id));
    }

    public String addPlayRecord(String playName, String playNameID
            , String playColumn, String playColumnID,int playPercent
            , int playType, int videoDuration
            , String phone, String stbNumber
            , String stuid) {
        return execute(RetrofitUtilExclusive.getInstance().edumsCall()
                .addPlayRecord(playName, playNameID, playColumn, playColumnID
                       , playPercent , playType, videoDuration, phone, stbNumber, stuid));
    }


    private String execute(Call<String> call) {
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return "call execute boy error " + e.getMessage();
        }
    }
}
