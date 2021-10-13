package com.evmtv.cloudvideo.common.presenter.scan;

import android.content.Intent;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.view.AgentWebActivity;

public interface ScanEventPresenter {
    void startIntent(String action, IntentLocalBean bean);

    void addFriend(String GUID, String Name);

    void login(String token);

    void bindUser(String GUID);

    void BindStb(String card);
}
