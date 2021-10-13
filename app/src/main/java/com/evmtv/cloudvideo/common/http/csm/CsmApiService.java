package com.evmtv.cloudvideo.common.http.csm;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface CsmApiService {

    //@GET("/repos/{owner}/{repo}/contributors")
    @GET("TAPI/videoCall/getHistoryVideoCallInfo")
    Call<String> getHistoryVideoCallInfo(@Query("userId") String userId, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    @GET("TAPI/videoCall/getHistoryVideoCallInfo")
    Call<String> getHistoryVideoCallInfo(@Query("userId") String userId, @Query("circleGUID") String circleGUID
            , @Query("circleType") String circleType
            , @Query("pageIndex") int pageIndex, @Query("pageSize") String pageSize);


    @GET("TAPI/ipc/keep")
    Call<String> evmMediaKeep(@Query("sessionId") String sessionId);

    @GET("TAPI/ipc/stopPlay")
    Call<String> evmMediaStopPlay(@Query("sessionId") String sessionId);

    @GET("TAPI/ipcAlarm/getAlarmDeviceStatus")
    Call<String> getAlarmDeviceStatus(@Query("userGUID") String userGUID);

    @GET("TAPI/ipc/isOnline")
    Call<String> isOnline(@Query("sn") String sn);

    @GET("TAPI/ipcAlarm/setAlarmDevice")
    Call<String> setAlarmDevice(@Query("deviceSn") String deviceSn, @Query("open") int open);

    @GET("TAPI/ipc/startPlay")
    Call<String> startPlay(@Query("sn") String sn, @Query("viewUserId") String viewUserId
            , @Query("hostUserId") String hostUserId, @Query("mobile") Boolean mobile);

    @GET("TAPI/ipc/startPlay")
    Call<String> startPlay(@Query("sn") String sn, @Query("viewUserId") String viewUserId
            , @Query("hostUserId") String hostUserId, @Query("mobile") Boolean mobile
            , @Query("startTime") String startTime, @Query("endTime") Boolean endTime);

    @GET("TAPI/ipc/keep")
    Call<String> keep(@Query("sessionId") String sessionId);

    @GET("TAPI/ipc/stopPlay")
    Call<String> stopPlay(@Query("sessionId") String sessionId);

    @GET("TAPI/ipc/ptzCtrl")
    Call<String> ptzCtrl(@Query("guid") String guid, @Query("action") String action);


}
