package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.persenter.forget.pwd.ForgetPassWorldPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.forget.pwd.ForgetPassWorldPresenter;
import com.evmtv.cloudvideo.common.presenter.forget.pwd.BaseForgetPassWorldPresenterImpl;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.XEditText;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private TextView titleViewID;
    private ImageButton backViewID;
    private EditText edtPhoneNumb, edtIdentCode;
    private Button butGetReturn, btnOutLogin;
    private XEditText edtNewPassword, surPassword;
    private ForgetPassWorldPresenter presenter;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    private void initView() {
        presenter = new ForgetPassWorldPresenterImpl(this);
        IntentLocalBean bean = getIntent().getParcelableExtra("value");
        titleViewID.setText("忘记密码");
        if (bean != null && bean.getTitle() != null)
            titleViewID.setText(bean.getTitle());
        butGetReturn.setOnClickListener(this);
        backViewID.setOnClickListener(this);
        btnOutLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tel = edtPhoneNumb.getText().toString() + "";
        SystemUtil.hideSoftInputFromWindow(ForgetPasswordActivity.this, v);
        switch (v.getId()) {
            case R.id.butGetReturn:
                if (tel.isEmpty()) {
                    ToastUtil.setToast("请先输入电话号码");
                    return;
                }
                presenter.senMsgCode(tel, butGetReturn);
                break;
            case R.id.btnOutLogin:
                String pwd1 = edtNewPassword.getText().toString() + "";
                String pwd2 = surPassword.getText().toString() + "";
                String code = edtIdentCode.getText().toString() + "";
                if (tel.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty() || code.isEmpty()) {
                    ToastUtil.setToast("请先输入完整");
                    return;
                }
                if (!pwd1.equals(pwd2)) {
                    ToastUtil.setToast("两次密码不一致");
                    return;
                }

                presenter.sendForgetPassWorldHttp(tel, code, pwd1, pwd2);
                break;
            case R.id.backViewID:
                finish();
                break;
        }
    }
}
