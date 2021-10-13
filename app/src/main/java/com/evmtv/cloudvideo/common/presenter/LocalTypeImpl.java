package com.evmtv.cloudvideo.common.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.model.local.LocalType;
import com.evmtv.cloudvideo.common.model.local.StartRegisterActionEntity;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.dialog.LogOutDialog;

public class LocalTypeImpl implements LocalType {
    private Activity activity;
    private View.OnClickListener loginMode;

    public LocalTypeImpl(Activity activity) {
        this.activity = activity;
    }

    public void setLoginMode(View.OnClickListener loginMode) {
        this.loginMode = loginMode;
    }

    public void use(View view, String type, String json, View.OnClickListener loginMode) {
        switch (type) {
            case LOGIN_LAYOUT_TYPE:
                new LogOutDialog(activity).show();
                break;
            case START_SYSTEM_ACTIVITY:
                break;
            case STRING_ACTIVITY_REGISTER_LOCAL:
                StartMoreActivity(json);
                break;
            case STRING_ACTIVITY_LOCAL_LOGIN:
                if (loginMode != null)
                    loginMode.onClick(view);
                break;
            case STRING_ACTIVITY_LOCAL:
                if (StartActivityLocal(json))
                    break;
            default:
                ToastUtil.setToast("该栏目即将开放，尽情期待。");
                break;
        }
    }


    public void use(View view, String type, String json) {
        use(view, type, json, null);
    }

    private void startIntent(String action, IntentLocalBean bean) {
        Intent intent = new Intent();
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        intent.putExtra("value", bean);
        intent.setAction(action);
        activity.startActivity(intent);
    }

    private void StartMoreActivity(String json) {
        StartRegisterActionEntity entity = JSONLocalObject.parseObject(json, StartRegisterActionEntity.class);
        if (entity == null)
            return;

        if (entity.getDirectStartAction() != null || !entity.getDirectStartAction().isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString("title", entity.getDirectText());
            startActivity(entity.getDirectStartAction(), bundle);
            return;
        }

        if (entity.getActionArr() != null) {
            return;
        }
    }

    private void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null)
            intent.putExtras(bundle);
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        activity.startActivity(intent);
    }

    private boolean StartActivityLocal(String json) {
        IntentLocalBean bean = null;
        if (json != null && json.length() > 0) {
            if (json.endsWith(".json")) {
                bean = JSONLocalObject.parseObject(json, IntentLocalBean.class);
                json = bean.getAction();
            }
            startIntent(json, bean);
            return true;
        }
        return false;
    }
}
