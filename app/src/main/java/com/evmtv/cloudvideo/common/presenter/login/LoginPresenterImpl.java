package com.evmtv.cloudvideo.common.presenter.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.LoginUserBean;
import com.evmtv.cloudvideo.common.model.local.LocalType;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.LocalTypeImpl;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.view.action.ActionName;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import java.util.List;

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    private LoginControllerView controllerView;
    private LoginModel loginModel;
    private Activity activity;

    public LoginPresenterImpl(Activity activity, LoginModel loginModel) {
        this.activity = activity;
        this.controllerView = (LoginControllerView) activity;
        this.loginModel = loginModel;
    }

    @Override
    public void validateCredentials(LoginUserBean user) {
        if (controllerView != null) {
            controllerView.showProgress();
        }
        loginModel.login(user, this);
    }

    @Override
    public void initTextOnClick(ViewGroup view, View.OnClickListener loginModeListener) {
        if (view == null)
            return;
        view.removeAllViews();
        NavigationBean.LoginTextOnClick entity = ReadLocalJsonFile.getLoginTextOnClickEntity();
        if (entity == null) {
            XLog.i("local", "LoginTextOnClick entity=null");
            return;
        }
        initTextLink(entity, view, loginModeListener);
    }

    private void initTextLink(NavigationBean.LoginTextOnClick entity
            , ViewGroup view, View.OnClickListener loginModeListener) {
        List<NavigationBean.LoginTextOnClick.ContextBean> arr = entity.getLoginContext();
        LocalType localType = new LocalTypeImpl(activity);
        for (NavigationBean.LoginTextOnClick.ContextBean bean : arr) {
            TextView textView = new TextView(activity);
            textView.setTag(bean.getText());
            String[] text = bean.getText().split("-");
            textView.setText(text[0]);
            textView.setPadding(20, 20, 20, 0);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextColor(activity.getResources().getColor(R.color.title_background));
            view.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    localType.use(v, bean.getStartType(), bean.getAction(), loginModeListener);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        controllerView = null;
    }

    @Override
    public void onLoginError(int type, String errorString) {
        if (controllerView != null) {
            controllerView.hideProgress();
            controllerView.setLoginError(type, errorString);
        }
    }

    @Override
    public void onSuccess() {
        if (controllerView != null) {
            controllerView.hideProgress();
            controllerView.showSuccess();
        }
    }

    @Override
    public void LoginSuccess() {
        startActivity(ActionName.MAIN_ACTIVITY_ACTION);
        activity.finish();
    }

    private void startActivity(String action) {
        startActivity(action, null);
    }

    private void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null)
            intent.putExtras(bundle);
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        activity.startActivity(intent);
    }

    @Override
    public void startForgetPassword() {
        String action = ReadLocalJsonFile.getStartForgetPassWorldAction();
        startActivity(action, null);
    }

    @Override
    public void SendSMS(String phone, Button btn) {
        SendSMSUtils.getInstance().senMsgCode(phone, btn,true);
    }
}
