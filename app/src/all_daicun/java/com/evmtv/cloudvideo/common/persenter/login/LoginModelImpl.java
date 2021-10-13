package com.evmtv.cloudvideo.common.persenter.login;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.model.http.LoginUserBean;
import com.evmtv.cloudvideo.common.model.http.edums.LoginEntity;
import com.evmtv.cloudvideo.common.presenter.login.BaseLoginModelImpl;
import com.evmtv.cloudvideo.common.presenter.login.LoginModel;
import com.evmtv.cloudvideo.common.presenter.login.OnLoginFinishedListener;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.Validator;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.csInteractive.ums.UMSInteractive;
import com.evmtv.rtc.csInteractive.ums.entity.GetUserInfoResult;

public class LoginModelImpl extends BaseLoginModelImpl {

    private OnLoginFinishedListener listener;

    @Override
    public void login(final LoginUserBean user, final OnLoginFinishedListener listener) {
        this.listener = listener;
        if (user == null || (user.getPassWorldLogin() == null && user.getTelLogin() == null)) {
            if (listener != null)
                listener.onLoginError(BaseResult.UNKNOWN_ERROR, "请先填写完整");
            return;
        }

        if (user.getPassWorldLogin() != null) {
            PassLogin(user.getPassWorldLogin());
            return;
        }

        if (user.getTelLogin() != null) {
            SMSLogin(user.getTelLogin());
            return;
        }
    }


    private void PassLogin(LoginUserBean.PassWorldLogin passWorldLogin) {
        if (passWorldLogin.getUserName() == null
                || passWorldLogin.getUserPassWorld() == null
                || passWorldLogin.getUserName().isEmpty()
                || passWorldLogin.getUserPassWorld().isEmpty()) {
            showToast(listener, BaseResult.UNKNOWN_ERROR, "请先输入用户名或者密码");
            return;
        }

        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                LoginEntity loginEntity;
                try {
                    final String loginName = passWorldLogin.getUserName();
                    final String loginPassWorld = passWorldLogin.getUserPassWorld();
                    //帐号密码登录
                    EdumsInteractive.getInstance().logout(loginName);
                    String json = EdumsInteractive.getInstance().login(loginName, loginPassWorld);
                    loginEntity = JSON.parseObject(json, LoginEntity.class);
                    Log.i("edums login", "json=" + json);
                } catch (JSONException e) {
                    XLog.e("login", "error:" + e.getMessage());
                    loginEntity = new LoginEntity(-1, Text.JsonParseErrorText);

                }
                LoginEntity finalLoginEntity = loginEntity;
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        dealLogin(finalLoginEntity);
                    }
                });
                return;
            }
        });
    }

    private void SMSLogin(LoginUserBean.TelLogin telLogin) {
        if (!Validator.isMobile(telLogin.getTel().intern())) {
            showToast(listener, BaseResult.UNKNOWN_ERROR, "请输入电话号码");
            return;
        }

        if (telLogin.getCode() <= 0) {
            showToast(listener, BaseResult.UNKNOWN_ERROR, "请输入验证码");
            return;
        }
        String sessionId = SendSMSUtils.getInstance().getSessionId();
        if (sessionId == null || sessionId.length() <= 0) {
            showToast(listener, BaseResult.UNKNOWN_ERROR, "请先获取验证码");
            return;
        }
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            LoginEntity loginEntity;

            @Override
            public void IO() {
                EdumsInteractive.getInstance().logout(telLogin.getTel());
                String json = EdumsInteractive.getInstance().login(telLogin.getTel(), "" + telLogin.getCode(), sessionId);
                try {
                    loginEntity = JSON.parseObject(json, LoginEntity.class);
                } catch (Exception e) {
                    loginEntity = new LoginEntity(-1, "网络通讯失败");
                }

                Log.i("edums login", "json=" + json);
            }

            @Override
            public void Main() {
                dealLogin(loginEntity);
            }
        });
    }

    private void dealLogin(LoginEntity loginEntity) {
        if (loginEntity.getCode() == 200) {
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_GUID, loginEntity.getRows().get(0).getId() + "");
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_NAME, loginEntity.getRows().get(0).getPhone());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_PASS_WORLD, loginEntity.getRows().get(0).getPassword());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_NAME, loginEntity.getRows().get(0).getName());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_SEX, loginEntity.getRows().get(0).getSex());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_AGE, "" + loginEntity.getRows().get(0).getAge());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CPN_USER_CLASS, loginEntity.getRows().get(0).getAreaid());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_UMS_USER_TEL, loginEntity.getRows().get(0).getPhone());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_SESSION_ID, loginEntity.getRows().get(0).getSessionid());
            SharedPreferencesUtil.getInstance()
                    .saveData(SharedPreferencesText.LOGIN_CK_STATE, loginEntity.getRows().get(0).getCkstate());

            if (listener != null)
                listener.onSuccess();
        } else {
            if (listener != null)
                listener.onLoginError(loginEntity.getCode(), loginEntity.getMsg());
        }
    }


    private void showToast(OnLoginFinishedListener listener, int type, String info) {
        if (listener != null)
            listener.onLoginError(type, info);
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