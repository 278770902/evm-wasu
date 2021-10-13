package com.evmtv.cloudvideo.common.http;

import org.junit.runners.Parameterized;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RootCsmApiService {

    //@GET("/repos/{owner}/{repo}/contributors")
    @GET("/getBindedUserInfo/{userGUID}")
    Call<String> getBindUserInfo(@Path("userGUID") String userGUID);
}
