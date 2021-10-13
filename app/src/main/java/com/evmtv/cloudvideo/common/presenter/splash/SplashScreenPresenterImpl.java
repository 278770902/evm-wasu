package com.evmtv.cloudvideo.common.presenter.splash;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.MainThread;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.model.http.LoginUserBean;
import com.evmtv.cloudvideo.common.persenter.login.LoginModelImpl;
import com.evmtv.cloudvideo.common.presenter.login.LoginModel;
import com.evmtv.cloudvideo.common.presenter.login.BaseLoginModelImpl;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SCGuideActivity;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.action.ActionName;

import java.util.concurrent.TimeUnit;


public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    /**
     * mode 启动模式，A 必须先登陆 B:不必须先登陆
     *
     * @param activity
     */
    @Override
    public void StartNext(Activity activity) {
        SharedPreferencesUtil.getInstance().saveFirstOpen(false);
        AppExecutors.getInstance().scheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                String GUID = SharedPreferencesUtil.getInstance()
                        .getUserGuid(false);
                SplashScreenModel splashScreenModel = new splash.SplashScreenImpl();
                int mode = splashScreenModel.StartUpMode();
                login(GUID);
                switch (mode) {
                    case SplashScreenModel.ModeA:
                        Intent intent = new Intent();
                        if (GUID == null || GUID.isEmpty()) {
                            intent.setAction(ActionName.LOGIN_ACTION);
                        } else {
                            intent.setAction(ActionName.MAIN_ACTIVITY_ACTION);
                        }
                        intent.setPackage(MainApplication.initContext.getAppPackageName());
                        activity.startActivity(intent);
                        activity.finish();
                        break;
                    case SplashScreenModel.ModeB:
                        Intent intentB = new Intent();
                        intentB.setAction(ActionName.MAIN_ACTIVITY_ACTION);
                        intentB.setPackage(MainApplication.initContext.getAppPackageName());
                        activity.startActivity(intentB);
                        activity.finish();
                        break;
                    case SplashScreenModel.ModeC:
                        break;
                    case SplashScreenModel.ModeD:
                        break;
                }
            }
        }, 1, TimeUnit.SECONDS);

    }

    private void login(String GUID) {
        if (GUID != null && GUID.length() > 0) {
            LoginModel model = new LoginModelImpl();
            model.login(new LoginUserBean(
                    SharedPreferencesUtil.getInstance().getUserLoginName()
                    , SharedPreferencesUtil.getInstance().getUserLoginPassWorld()), null);
        }
    }
}
