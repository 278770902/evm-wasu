package com.evmtv.cloudvideo.common;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.evmtv.cloudvideo.common.persenter.register.RegisterModel;
import com.evmtv.cloudvideo.common.persenter.register.RegisterModelImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.utils.Validator;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.ForgetPasswordActivity;
import com.evmtv.cloudvideo.common.view.tool.XEditText;

import main.MainActivity;

public class LoginRegisterActivity extends BaseActivity implements View.OnClickListener, OnOptionsSelectListener, RadioGroup.OnCheckedChangeListener {

    private ImageButton backViewID;
    private TextView titleViewID;
    private EditText registerEditUserNameViewId, EditTextRegisterAgeViewID, edtPhoneNumbViewID, editIdentCodeViewID;
    private Button butGetReturnViewID, btnOutLoginViewID, EditTextRegisterClassIdViewID;
    private XEditText edtPasswordViewID, surPasswordViewID;
    private RadioGroup rgSexViewID;
    private RegisterModel registerModel;
    private int sex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initFunction();
        initView();
    }

    private void initFunction() {
        registerModel = new RegisterModelImpl(this);
        registerModel.setClassTextView(EditTextRegisterClassIdViewID);
        registerModel.InitDisplay();
    }

    private void initView() {
        Intent intent = getIntent();
        titleViewID.setText(intent.getExtras().getString("title"));
        backViewID.setOnClickListener(this);
        EditTextRegisterClassIdViewID.setOnClickListener(this::onClick);
        butGetReturnViewID.setOnClickListener(this::onClick);
        rgSexViewID.setOnCheckedChangeListener(this::onCheckedChanged);
        btnOutLoginViewID.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        SystemUtil.hideSoftInputFromWindow(LoginRegisterActivity.this, v);
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.EditTextRegisterClassIdViewID:
                registerModel.showClassId();
                break;
            case R.id.butGetReturnViewID:
                String phone = edtPhoneNumbViewID.getText().toString() + "";
                if (!Validator.isMobile(phone)) {
                    ToastUtil.setToast("请输入电话号码");
                    return;
                }
                registerModel.senMsg(phone, butGetReturnViewID);
                break;
            case R.id.btnOutLoginViewID:
                String name = registerEditUserNameViewId.getText().toString() + "";
                String age = EditTextRegisterAgeViewID.getText().toString() + "";
                String phone1 = edtPhoneNumbViewID.getText().toString() + "";
                String code = editIdentCodeViewID.getText().toString() + "";
                String pwd1 = edtPasswordViewID.getText().toString() + "";
                String pwd2 = surPasswordViewID.getText().toString() + "";
                if (name.isEmpty() || age.isEmpty() || phone1.isEmpty() || code.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty()) {
                    ToastUtil.setToast("请完整输入");
                    return;
                }
                if (!pwd1.equals(pwd2)) {
                    ToastUtil.setToast("两次密码不一致");
                    return;
                }
                registerModel.register(name, sex + "", age, phone1, pwd1, pwd2, registerModel.getClassId(), code, registerModel.getSessionid());
                break;
        }
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.maleViewId:
                sex = 2;
                break;
            case R.id.femaleViewId:
                sex = 1;
                break;
        }
    }
}
