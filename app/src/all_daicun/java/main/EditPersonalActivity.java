package main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.edit.personal.EditPersonalModel;
import com.evmtv.cloudvideo.common.persenter.edit.personal.EditPersonalModelImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class EditPersonalActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private int sex = 0;
    private TextView titleViewID;
    private RadioGroup rgSexViewID;
    private ImageButton backViewID;
    private EditPersonalModel model;
    private RadioButton maleViewId, femaleViewId;
    private Button EditTextRegisterClassIdViewID, btnOutLoginViewID;
    private EditText registerEditUserNameViewId, EditTextRegisterAgeViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_edit_personal);
        initView();
    }

    private void initView() {
        model = new EditPersonalModelImpl(this);
        model.InitDisplay();
        model.setClassTextView(EditTextRegisterClassIdViewID);
        model.setClassId(SharedPreferencesUtil.getInstance().getUserClass());
        titleViewID.setText("编辑");
        backViewID.setOnClickListener(this);
        rgSexViewID.setOnCheckedChangeListener(this);
        btnOutLoginViewID.setOnClickListener(this::onClick);
        EditTextRegisterClassIdViewID.setOnClickListener(this::onClick);
//        registerEditUserNameViewId.setText((String) SharedPreferencesUtil.getInstance().getData(SharedPreferencesText.LOGIN_CPN_USER_NAME, "--"));
//        EditTextRegisterAgeViewID.setText(SharedPreferencesUtil.getInstance().getData(SharedPreferencesText.LOGIN_CPN_USER_AGE, 0) + "");
        registerEditUserNameViewId.setText(SharedPreferencesUtil.getInstance().getUserName());
        EditTextRegisterAgeViewID.setText(" " + SharedPreferencesUtil.getInstance().getUserAge());
        setSex();
    }

    private void setSex() {
        String sex = SharedPreferencesUtil.getInstance().getUserSex();
        if (sex.equals("2")) {
            maleViewId.setChecked(true);
            femaleViewId.setChecked(false);
        } else {
            maleViewId.setChecked(false);
            femaleViewId.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        SystemUtil.hideSoftInputFromWindow(EditPersonalActivity.this, v);
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.EditTextRegisterClassIdViewID:
                model.showClassId();
                break;
            case R.id.btnOutLoginViewID:
                String name = registerEditUserNameViewId.getText().toString() + "";
                String age = EditTextRegisterAgeViewID.getText().toString() + "";
                if (name.isEmpty() || age.isEmpty() || model.getClassId().isEmpty()) {
                    ToastUtil.setToast("请输入完整");
                    return;
                }
                model.updateInfo((String) SharedPreferencesUtil.getInstance().getData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_PASS_WORLD, ""),
                        name, sex + "", age, model.getClassId(), (String) SharedPreferencesUtil.getInstance().getData(SharedPreferencesText.LOGIN_CPN_USER_GUID, ""));
                break;
        }
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
