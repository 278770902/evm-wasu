package com.evmtv.cloudvideo.common.http.csm;

import com.evmtv.cloudvideo.common.http.RetrofitUtil;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Query;

import static com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText.LOGIN_CPN_USER_GUID;
import static com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText.LOGIN_CPN_USER_LOGIN_PASS_WORLD;

public class CsmInteractive {
    private static CsmInteractive instance = null;
    private String TAG = getClass().getName();

    static {
        instance = new CsmInteractive();
    }

    public static CsmInteractive getInstance() {
        return instance;
    }

    public String getHistoryVideoCallInfo() {
        return execute(RetrofitUtil.getInstance().csmCall()
                .getHistoryVideoCallInfo(SharedPreferencesUtil.getInstance()
                        .getUserGuid(false), 0, 0));
    }

    public String evmMediaKeep(String session) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .evmMediaKeep(session));
    }

    public String evmMediaStopPlay(String session) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .evmMediaStopPlay(session));
    }

    public String getAlarmDeviceStatus(String STBGUID) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .getAlarmDeviceStatus(STBGUID));
    }

    public String isOnline(String sn) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .isOnline(sn));
    }

    public String setAlarmDevice(String deviceSn, int open) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .setAlarmDevice(deviceSn, open));
    }


    public String startPlay(String sn, String viewUserId
            , String hostUserId, Boolean mobile
            , String startTime, Boolean endTime) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .startPlay(sn, viewUserId, hostUserId, mobile, startTime, endTime));
    }

    public String startPlay(String sn, String viewUserId
            , String hostUserId, Boolean mobile) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .startPlay(sn, viewUserId, hostUserId, mobile));
    }

    public String keep(String sessionId) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .keep(sessionId));
    }

    public String stopPlay(String sessionId) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .stopPlay(sessionId));
    }

    public String ptzCtrl(String guid, String action) {
        return execute(RetrofitUtil.getInstance().csmCall()
                .ptzCtrl(guid, action));
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
