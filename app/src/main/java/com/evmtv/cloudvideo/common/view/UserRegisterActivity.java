package com.evmtv.cloudvideo.common.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.register.RegisterModelImpl;
import com.evmtv.cloudvideo.common.utils.Validator;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;
import com.evmtv.cloudvideo.common.view.tool.XEditText;

public class UserRegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backViewID;
    private TextView titleViewID;
    private EditText register_edit_user_name, edt_phone_numb, edt_ident_code;
    private XEditText edt_password, sur_password;
    private Button but_get_return, btn_out_login;

    private RegisterModelImpl registerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initView();
    }

    private void initView() {
        registerModel = new RegisterModelImpl(this);
        titleViewID.setText("用户注册");
        backViewID.setOnClickListener(this);
        but_get_return.setOnClickListener(this);
        btn_out_login.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.but_get_return:
                String phone = edt_phone_numb.getText().toString() + "";
                if (!Validator.isMobile(phone)) {
                    ToastUtil.setToast("请输入电话号码");
                    return;
                }
                registerModel.senMsg(phone, but_get_return);
                break;
            case R.id.btn_out_login:
                String name = register_edit_user_name.getText().toString() + "";
                String phone1 = edt_phone_numb.getText().toString() + "";
                String code = edt_ident_code.getText().toString() + "";
                String pwd1 = edt_password.getText().toString() + "";
                String pwd2 = sur_password.getText().toString() + "";
                if (name.isEmpty() || phone1.isEmpty() || code.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty()) {
                    ToastUtil.setToast("请完整输入");
                    return;
                }
                if (!pwd1.equals(pwd2)) {
                    ToastUtil.setToast("两次密码不一致");
                    return;
                }
                registerModel.register(name, phone1, pwd1, SendSMSUtils.getInstance().getSessionId(), code, "");
                break;
        }
    }
}
