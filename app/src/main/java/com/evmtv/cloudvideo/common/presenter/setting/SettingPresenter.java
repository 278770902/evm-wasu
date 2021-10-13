package com.evmtv.cloudvideo.common.presenter.setting;


public interface SettingPresenter {
    void InitDisplay(Object TabLayout, Object RecyclerView);

    void startEditPersonalActivity();

    String getBackgroundIcon();

    String getUserState();

    void UpdatePersonalInfo(MessageRefreshListener messageRefreshListener);
}
