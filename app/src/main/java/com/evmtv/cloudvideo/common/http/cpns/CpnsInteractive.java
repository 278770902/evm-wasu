package com.evmtv.cloudvideo.common.http.cpns;

import com.evmtv.cloudvideo.common.http.RetrofitUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

import java.io.IOException;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Query;

public class CpnsInteractive {
    private static CpnsInteractive instance = null;
    private String TAG = getClass().getName();

    static {
        instance = new CpnsInteractive();
    }

    public static CpnsInteractive getInstance() {
        return instance;
    }


    public String isUserRegistered(String tel) {
        return execute(RetrofitUtil.getInstance().pnsCall()
                .isUserRegistered(tel));
    }

    public String queryIdentifyCode(String tel, String UUID) {
        return execute(RetrofitUtil.getInstance().pnsCall()
                .queryIdentifyCode(UUID, tel));
    }

    public String resetPassword(String password, String TEL, String uuid, String identifyCode) {
        return execute(RetrofitUtil.getInstance().pnsCall()
                .resetPassword(password, TEL, uuid, identifyCode));
    }

    public String registerUser(String userName
            , String TEL, String password
            , String uuid, String identifyCode
            , String regionId) {
        return execute(RetrofitUtil.getInstance().pnsCall()
                .registerUser(userName, TEL, password, uuid, identifyCode, regionId));
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
