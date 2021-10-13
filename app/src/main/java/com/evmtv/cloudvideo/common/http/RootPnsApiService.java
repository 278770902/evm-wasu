package com.evmtv.cloudvideo.common.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RootPnsApiService {

    //@GET("/repos/{owner}/{repo}/contributors")
    @GET("/getBindedUserInfo/{userGUID}")
    Call<String> getBindUserInfo(@Path("userGUID") String userGUID);
}
