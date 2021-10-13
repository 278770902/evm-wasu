package com.evmtv.cloudvideo.common.http.ums;

import com.evmtv.cloudvideo.common.http.RetrofitUtil;
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

public class UMSInteractive {
    private static UMSInteractive instance = null;
    private String TAG = getClass().getName();

    static {
        instance = new UMSInteractive();
    }

    public static UMSInteractive getInstance() {
        return instance;
    }

    public String getBindUserInfo() {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getBindedUserInfo((String) SharedPreferencesUtil.getInstance()
                        .getUserGuid(true)));
    }

    public String bindUser(String GUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .bindUser(SharedPreferencesUtil.getInstance()
                        .getUserGuid(true), GUID));
    }

    public String applyFriend(String otherUserGUID) {
        return execute(RetrofitUtil.getInstance().umsCall().applyFriend(SharedPreferencesUtil.getInstance()
                .getUserGuid(false), otherUserGUID));
    }

    public String updateUserInfo(File file) {
        return this.updateUserInfo(SharedPreferencesUtil.getInstance().getUserGuid(false)
                , SharedPreferencesUtil.getInstance().getUserLoginPassWorld(), file);
    }

    public String updateUserInfo(String GUID, String imageGUID, File file) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        parts.add(MultipartBody.Part.createFormData("GUID", GUID));
//        parts.add(MultipartBody.Part.createFormData("imageGUID", imageGUID));
        parts.add(MultipartBody.Part.createFormData("imgFile", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file)));
        Call<String> call = RetrofitUtil.getInstance().umsCall().updateUserInfo(parts);
        return execute(call);
    }

    public String getUserFriends() {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getUserFriends((String) SharedPreferencesUtil.getInstance()
                        .getUserGuid(false)));
    }


    public String getBindedUserInfo(String userGUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getBindedUserInfo(userGUID));
    }

    public String getUserCameraShareDetail(String userGUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getUserCameraShareDetail(userGUID));
    }

    public String getCallTempletList(String userGUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getCallTempletList(userGUID));
    }

    public String getBindedUser(String userGUID, String STBUserGUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getBindedUser(userGUID, STBUserGUID));
    }

    public String getMonitorCameraInfo(String userGUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getMonitorCameraInfo(userGUID));
    }

    public String saveDeviceToken(String userGUID, String deviceToken) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .saveDeviceToken(userGUID, deviceToken));
    }

    public String getCameraShareUser(String userGUID, String cameraGUID) {
        return execute(RetrofitUtil.getInstance().umsCall()
                .getCameraShareUser(userGUID, cameraGUID));
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
