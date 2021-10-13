package com.evmtv.cloudvideo.common.http.ums;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UmsApiService {

    //@GET("/repos/{owner}/{repo}/contributors")
//    @GET("getBindedUserInfo")
//    Call<String> getBindUserInfo(@Query("userGUID") String userGUID);

    @GET("bindUser")
    Call<String> bindUser(@Query("STBUserGUID") String STBUserGUID, @Query("clientUserGUID") String clientUserGUID);

    @GET("applyFriend")
    Call<String> applyFriend(@Query("currentUserGUID") String currentUserGUID, @Query("opposingUserGUID") String otherUserGUID);

    @Multipart
    @POST("updateUserInfo")
    Call<String> updateUserInfo(@Part List<MultipartBody.Part> parts);

    @GET("getUserFriends")
    Call<String> getUserFriends(@Query("userGUID") String userGUID, @Query("friendsType") String friendsType);


    @GET("getUserFriends")
    Call<String> getUserFriends(@Query("userGUID") String userGUID);

    //http://123.160.94.231:9554/ums_v3/getBindedUserInfo?userGUID=41000000000178
    @GET("getBindedUserInfo")
    Call<String> getBindedUserInfo(@Query("userGUID") String userGUID);

    @GET("getUserCameraShareDetail")
    Call<String> getUserCameraShareDetail(@Query("userBGUID") String userBGUID);

    @GET("call/getCallTempletList")
    Call<String> getCallTempletList(@Query("userGUID") String userGUID);

    @GET("getBindedUser")
    Call<String> getBindedUser(@Query("userGUID") String userGUID, @Query("STBUserGUID") String STBUserGUID);

    @GET("getMonitorCameraInfo")
    Call<String> getMonitorCameraInfo(@Query("userGUID") String userGUID);

    @GET("saveDeviceToken")
    Call<String> saveDeviceToken(@Query("userGUID") String userGUID, @Query("deviceToken") String deviceToken);

    @GET("getCameraShareUser")
    Call<String> getCameraShareUser(@Query("userGUID") String userGUID, @Query("cameraGUID") String cameraGUID);

}
