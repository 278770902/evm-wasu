package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.LoginUserBean;
import com.evmtv.cloudvideo.common.persenter.login.LoginModelImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.login.LoginControllerView;
import com.evmtv.cloudvideo.common.presenter.login.BaseLoginModelImpl;
import com.evmtv.cloudvideo.common.presenter.login.LoginPresenter;
import com.evmtv.cloudvideo.common.presenter.login.LoginPresenterImpl;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XEditText;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginControllerView {

    private XEditText passwordViewID;
    private LoginPresenter presenter;
    private LinearLayout textForViewID;
    private ProgressBar loginProgressBarViewID;
    private Button loginViewID, SMSLoginButtonViewID;
    private EditText userNameViewID, SMSLoginEditViewID;
    private TextView forgetPasswordViewID, registerNewViewID;
    private View PassLoginLayoutViewID, SMSLoginLayoutViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        presenter = new LoginPresenterImpl(this, new LoginModelImpl());
        String UserName = SharedPreferencesUtil.getInstance().getUserLoginName().intern();
        if (UserName.length() > 1)
            userNameViewID.setText(UserName);
        presenter.initTextOnClick(textForViewID, new loginModeListener());
        loginViewID.setOnClickListener(this);
        registerNewViewID.setOnClickListener(this);
        forgetPasswordViewID.setOnClickListener(this);
        SMSLoginButtonViewID.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginViewID:
                if (!isSMSLoginMode()) {
                    presenter.validateCredentials(new LoginUserBean(userNameViewID.getText().toString().intern()
                            , passwordViewID.getText().toString().intern()));
                    break;
                }

                if (SMSLoginEditViewID.getText().toString().isEmpty()) {
                    ToastUtil.setToast("请输入验证码");
                    break;
                }
                presenter.validateCredentials(new LoginUserBean(userNameViewID.getText().toString().intern()
                        , Integer.parseInt(SMSLoginEditViewID.getText().toString())));
                break;
            case R.id.SMSLoginButtonViewID:
                String tel = userNameViewID.getText().toString();
                presenter.SendSMS(tel, SMSLoginButtonViewID);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        loginProgressBarViewID.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loginProgressBarViewID.setVisibility(View.GONE);
    }

    @Override
    public void setLoginError(int type, String errorString) {
        Toast.makeText(LoginActivity.this, errorString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccess() {
        presenter.LoginSuccess();
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
    }

    private boolean isSMSLoginMode() {
        if (SMSLoginLayoutViewID.getVisibility() == View.VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    public class loginModeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = (String) v.getTag();
            String[] value = text.split("-");
            if (SMSLoginLayoutViewID.getVisibility() == View.VISIBLE) {
                PassLoginLayoutViewID.setVisibility(View.VISIBLE);
                SMSLoginLayoutViewID.setVisibility(View.INVISIBLE);
                ((TextView) v).setText(value[0]);
            } else {
                SMSLoginLayoutViewID.setVisibility(View.VISIBLE);
                PassLoginLayoutViewID.setVisibility(View.INVISIBLE);
                ((TextView) v).setText(value[1]);
            }
        }
    }
}
