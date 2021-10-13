package com.evmtv.cloudvideo.common.http.edums;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EdumsApiService {

    @GET("login")
    Call<String> login(@Query("phone") String phone, @Query("password") String password);

    @GET("login")
    Call<String> login(@Query("phone") String phone, @Query("identifyCode") String identifyCode, @Query("sessionid") String sessionId);

    @GET("logout")
    Call<String> logout(@Query("phone") String phone);


    @GET("getAreaTree")
    Call<String> getAreaTree();

    @GET("sendMsg")
    Call<String> sendMsg(@Query("telePhone") String phone);


    @GET("register")
    Call<String> register(@Query("name") String name, @Query("sex") String sex
            , @Query("age") String age, @Query("phone") String phone
            , @Query("password") String password, @Query("pwd2") String pwd2
            , @Query("classid") String classid, @Query("identifyCode") String identifyCode
            , @Query("sessionid") String sessionid);

    //http://127.0.0.1:8080/EDUMS/edumsStudentInterface/updatePwdByPhone?password=1234567&pwd2=1234567&phone=15581497725&identifyCode=153591&sessionid=9FEC579336AA293A8D2B2C063BE068C3

    @GET("updatePwdByPhone")
    Call<String> updatePwdByPhone(@Query("phone") String phone, @Query("password") String password, @Query("pwd2") String pwd2
            , @Query("identifyCode") String identifyCode, @Query("sessionid") String sessionid, @Query("telephone") String telePhone1
            , @Query("ssoToken") String ssoToken);


    @GET("updateInfo")
    Call<String> updateInfo(@Query("password") String password, @Query("name") String name
            , @Query("sex") String sex, @Query("age") String age
            , @Query("classid") String classid, @Query("id") String id, @Query("telephone") String telePhone1
            , @Query("ssoToken") String ssoToken);

    @GET("findPhoneIfExist")
    Call<String> findPhoneIfExist(@Query("telePhone") String telePhone, @Query("telephone") String telePhone1
            , @Query("ssoToken") String ssoToken);

    @GET("findPhoneIfExist")
    Call<String> findPhoneIfExist(@Query("telePhone") String telePhone);

    @GET("boosBinCard")
    Call<String> boosBinCard(@Query("phone") String phone, @Query("cardno") String cardno, @Query("requestURL") String requestURL, @Query("telephone") String telePhone1
            , @Query("ssoToken") String ssoToken);

    @GET("boosReBinCard")
    Call<String> boosReBinCard(@Query("phone") String phone, @Query("cardno") String cardno, @Query("requestURL") String requestURL, @Query("telephone") String telePhone1
            , @Query("ssoToken") String ssoToken);

    @GET("boosCheckphone")
    Call<String> boosCheckphone(@Query("phone") String phone, @Query("requestURL") String requestURL);

    @GET("boosCheckCard")
    Call<String> boosCheckCard(@Query("cardno") String phone, @Query("requestURL") String requestURL);

    @GET("boosBinCard")
    Call<String> boosBinCard(@Query("phone") String phone, @Query("cardno") String cardno);

    @GET("boosReBinCard")
    Call<String> boosReBinCard(@Query("phone") String phone, @Query("cardno") String cardno);

    @GET("boosCheckphone")
    Call<String> boosCheckphone(@Query("phone") String phone, @Query("telephone") String telePhone1
            , @Query("ssoToken") String ssoToken);

    @GET("boosCheckCard")
    Call<String> boosCheckCard(@Query("cardno") String phone);

    /**
     *
     * @param playName
     * @param playNameID
     * @param playColumn
     * @param playColumnID
     * @param playType 1 视频  0 文章
     * @param videoDuration
     * @param phone
     * @param stbNumber
     * @param stuid
     * @return
     */
    @GET("addPlayRecord")
    Call<String> addPlayRecord(@Query("playName") String playName, @Query("playNameID") String playNameID
            , @Query("playColumn") String playColumn, @Query("playColumnID") String playColumnID
            ,@Query("playPercent") int playPercent, @Query("playType") int playType, @Query("videoDuration") int videoDuration
            , @Query("phone") String phone, @Query("stbNumber") String stbNumber
            , @Query("stuid") String stuid);


    @GET("addPlayHistory")
    Call<String> addPlayHistory(@Query("phoneduration") String phoneduration, @Query("id") String id);
}
