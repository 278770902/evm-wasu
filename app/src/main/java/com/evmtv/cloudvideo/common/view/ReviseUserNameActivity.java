package com.evmtv.cloudvideo.common.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.ums.UmsApiService;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.csInteractive.ums.UMSInteractive;
import com.evmtv.rtc.csInteractive.ums.entity.ChangeUserNameResult;

import org.greenrobot.eventbus.EventBus;

public class ReviseUserNameActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit_user_name;
    private Button SaveViewID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_user_name);
        initview();
    }

    private void initview() {
        SaveViewID.setOnClickListener(this);
        edit_user_name.setText(SharedPreferencesUtil.getInstance().getUserName());
    }

    @Override
    public void onClick(View v) {
        String name = edit_user_name.getText().toString().intern();
        if (name == null || name.length() <= 0) {
            ToastUtil.setToast("请先输入名称");
            return;
        }
        AppExecutors.getInstance().networkIOToMain(ReviseUserNameActivity.this,new OrderMethodInter() {
            ChangeUserNameResult result;

            @Override
            public void IO() {
                result = UMSInteractive.getInstance().changeUserName(SharedPreferencesUtil.getInstance().getUserGuid(false)
                        , SharedPreferencesUtil.getInstance().getUserLoginPassWorld(), name);
            }

            @Override
            public void Main() {
                if (result.getResult() == BaseResult.SUCCESS) {
                    ToastUtil.setToast("更新成功");
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_NAME, name);
                    finish();
                }
            }
        });
    }
}
