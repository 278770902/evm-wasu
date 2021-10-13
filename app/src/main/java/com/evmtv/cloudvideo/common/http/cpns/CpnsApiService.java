package com.evmtv.cloudvideo.common.http.cpns;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CpnsApiService {


    //@GET("/repos/{owner}/{repo}/contributors")
    @GET("/getBindedUserInfo/{userGUID}")
    Call<String> getBindUserInfo(@Path("userGUID") String userGUID);

    @GET("TAPI/isUserRegistered")
    Call<String> isUserRegistered(@Query("TEL") String TEL);

    @GET("TAPI/queryIdentifyCode")
    Call<String> queryIdentifyCode(@Query("uuid") String uuid, @Query("TEL") String TEL);

    @GET("TAPI/resetPassword")
    Call<String> resetPassword(@Query("password") String password, @Query("TEL") String TEL, @Query("uuid") String uuid, @Query("identifyCode") String identifyCode);

    @GET("TAPI/registerUser")
    Call<String> registerUser(@Query("userName") String userName
            , @Query("TEL") String TEL, @Query("password") String password
            , @Query("uuid") String uuid, @Query("identifyCode") String identifyCode
            , @Query("regionId") String regionId);


}
