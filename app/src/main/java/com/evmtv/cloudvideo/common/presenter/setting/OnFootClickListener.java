package com.evmtv.cloudvideo.common.presenter.setting;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.view.AgentWebActivity;
import com.evmtv.cloudvideo.common.view.action.ActionName;

public class OnFootClickListener implements View.OnClickListener {

    private String url;
    private Context activity;

    public OnFootClickListener(String url, Context activity) {
        this.url = url;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(AgentWebActivity.EXTRA_VALUE, new IntentLocalBean(url, null, null,true));
        intent.setAction(ActionName.ACTIVITY_ACTION_AGENT_WEB);
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        activity.startActivity(intent);
    }
}
