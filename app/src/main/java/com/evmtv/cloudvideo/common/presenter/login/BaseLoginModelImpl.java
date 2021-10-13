package com.evmtv.cloudvideo.common.presenter.login;

import com.evmtv.cloudvideo.common.http.ums.UmsApiService;
import com.evmtv.cloudvideo.common.model.http.CpnUserInfoBean;
import com.evmtv.cloudvideo.common.model.http.LoginUserBean;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.Validator;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.ERTCCommand;
import com.evmtv.rtc.csInteractive.ums.UMSInteractive;
import com.evmtv.rtc.csInteractive.ums.entity.GetDeviceTokenResult;
import com.evmtv.rtc.csInteractive.ums.entity.GetUserInfoResult;

public class BaseLoginModelImpl implements LoginModel {

    @Override
    public void login(final LoginUserBean user, final OnLoginFinishedListener listener) {
        if (user == null || (user.getPassWorldLogin() == null && user.getTelLogin() == null)) {
            if (listener != null)
                listener.onLoginError(BaseResult.UNKNOWN_ERROR, "请先填写完整");
            return;
        }
        if (user.getPassWorldLogin() != null) {
            if (user.getPassWorldLogin().getUserName() == null
                    || user.getPassWorldLogin().getUserPassWorld() == null
                    || user.getPassWorldLogin().getUserName().isEmpty()
                    || user.getPassWorldLogin().getUserPassWorld().isEmpty()) {
                if (listener != null)
                    listener.onLoginError(BaseResult.UNKNOWN_ERROR, "请先输入用户名或者密码");
                return;
            }
        }

        if (user.getTelLogin() != null) {
            if (!Validator.isMobile(String.valueOf(user.getTelLogin().getTel()))) {
                if (listener != null)
                    listener.onLoginError(BaseResult.UNKNOWN_ERROR, "请先输入用户名或者密码");
                return;
            }
        }
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                final String loginName = user.getPassWorldLogin().getUserName();
                final String loginPassWorld = user.getPassWorldLogin().getUserPassWorld();
                final String deviceToken = "evm" + System.currentTimeMillis();
                SharedPreferencesUtil.getInstance().saveData(SharedPreferencesText.LOGIN_CPN_DEVICE_TOKEN, deviceToken);
                //帐号密码登录
                if (user.getPassWorldLogin() != null) {
                    final BaseResult baseResult = ERTCCommand.getInstance().mobile.common.login(loginName
                            , loginPassWorld
                            , deviceToken);
                    final GetUserInfoResult getUserInfoResult = UMSInteractive.getInstance().getUserInfo(loginName, loginPassWorld);
                    saveUserInfo(loginName, loginPassWorld);
                    if (getUserInfoResult != null && getUserInfoResult.getUser() != null && getUserInfoResult.getUser().getUserGUID() != null)
                        com.evmtv.cloudvideo.common.http.ums.UMSInteractive.getInstance().saveDeviceToken(getUserInfoResult.getUser().getUserGUID(), deviceToken);
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (baseResult.getResult() == BaseResult.SUCCESS) {
                                CpnUserInfoBean loginUserBean = JSONLocalObject.parseObject(baseResult.getResultJson(), CpnUserInfoBean.class);
                                SharedPreferencesUtil.getInstance()
                                        .saveData(SharedPreferencesText.LOGIN_CPN_USER_GUID, loginUserBean.getUserGUID());
                                SharedPreferencesUtil.getInstance()
                                        .saveData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_NAME, loginName);
                                SharedPreferencesUtil.getInstance()
                                        .saveData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_PASS_WORLD, loginPassWorld);

                                if (listener != null)
                                    listener.onSuccess();
                            } else {
                                if (listener != null)
                                    listener.onLoginError(baseResult.getResult(), baseResult.getErrorMessage().equals("PE") ? "帐号或密码错误" : baseResult.getErrorMessage());
                            }
                        }
                    });
                    return;
                }
                //电话号码验证登录
                if (user.getTelLogin() != null) {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null)
                                listener.onLoginError(BaseResult.UNKNOWN_ERROR, "未开放电话号码登录");
                        }
                    });
                    return;
                }
            }
        });
    }


    public void saveUserInfoIO(String loginName, String loginPassWorld) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                saveUserInfo(loginName, loginPassWorld);
            }
        });
    }

    private void saveUserInfo(String loginName, String loginPassWorld) {
        final GetUserInfoResult getUserInfoResult = UMSInteractive.getInstance().getUserInfo(loginName, loginPassWorld);
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                if (getUserInfoResult.getResult() == BaseResult.SUCCESS && getUserInfoResult.getUser() != null) {
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_UMS_USER_ICON, getUserInfoResult.getUser().getIconUrl());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_NAME, getUserInfoResult.getUser().getUserName());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_UMS_USER_TEL, getUserInfoResult.getUser().getTEL());
                }
            }
        });
    }
}
