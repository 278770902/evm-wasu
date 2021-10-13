package com.evmtv.cloudvideo.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.persenter.login.LoginModelImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.login.LoginModel;
import com.evmtv.cloudvideo.common.presenter.setting.edit.EditPersonalPresenter;
import com.evmtv.cloudvideo.common.presenter.setting.edit.EditPersonalPresenterImpl;
import com.evmtv.cloudvideo.common.utils.picker.GlideImageLoader;
import com.evmtv.cloudvideo.common.utils.picker.PickerPictureTool;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.action.ActionName;
import com.evmtv.cloudvideo.common.view.tool.GlideCircleTransform;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

public class EditPersonalActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backViewID, editPictureViewID;
    private View relHeadViewID, relPassWorldViewID, relNameViewID;
    private TextView NameTextViewID, passWorldTextViewID;
    private String TAG = getClass().getName();
    private EditPersonalPresenter presenter;
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);
        initView();
    }

    private void initView() {
        loginModel = new LoginModelImpl();
        backViewID.setOnClickListener(this::onClick);
        relHeadViewID.setOnClickListener(this::onClick);
        relNameViewID.setOnClickListener(this::onClick);
        relPassWorldViewID.setOnClickListener(this::onClick);
        presenter = new EditPersonalPresenterImpl(this);
        NameTextViewID.setText(SharedPreferencesUtil.getInstance().getUserName());
        Glide.with(this)
                .load(SharedPreferencesUtil.getInstance().getUserIcon())
                .placeholder(R.drawable.my_setting_default_header)
                .transform(new GlideCircleTransform(this))
                .into(editPictureViewID);
    }
    @Override
    protected void onStart() {
        super.onStart();
        NameTextViewID.setText(SharedPreferencesUtil.getInstance().getUserName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.relHeadViewID:
                PickerPictureTool.getInstance(EditPersonalActivity.this).initSingleChoiceMode().start();
                break;
            case R.id.relNameViewID:
//                PickerPictureTool.getInstance(this).startCamera();
                startActivity(new Intent(EditPersonalActivity.this, ReviseUserNameActivity.class));
                break;
            case R.id.relPassWorldViewID:
                Intent intent = new Intent();
                intent.setAction(ActionName.FORGET_PASS_WORLD_ACTIVITY);
                intent.setPackage(MainApplication.initContext.getAppPackageName());
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(getClass().getName(), "onActivityResult" + requestCode + "   " + resultCode);
        ArrayList<ImageItem> items = PickerPictureTool.getInstance(null).onActivityResult(requestCode, resultCode, data);
        presenter.updatePicker(items, new EditPersonalPresenterImpl.OnEditPersonalUpdateSuccessListener() {
            @Override
            public void UpdateImage(String path) {
                Glide.with(EditPersonalActivity.this)
                        .load(path)
                        .placeholder(R.drawable.my_setting_default_header)
                        .transform(new GlideCircleTransform(EditPersonalActivity.this))
                        .into(editPictureViewID);

                EventBus.getDefault().postSticky(new SendMessageEntity<>()
                        .setMsgId(SendMessageEntity.MsgId.SEND_EDIT_PERSONAL)
                        .setData(path));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(SendMessageEntity msg) {
        if (msg.getMsgId() == SendMessageEntity.MsgId.SEND_EDIT_PERSONAL) {
            if (msg.getData() != null) {
                String data = (String) msg.getData();
                if (data != null && data.length() > 0) {
                    loginModel.saveUserInfoIO(SharedPreferencesUtil.getInstance().getUserLoginName()
                            , SharedPreferencesUtil.getInstance().getUserLoginPassWorld());

                }
            }
        }
    }
}
