package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.support.design.widget.TabLayout;
import android.view.ViewGroup;

public interface MonitorLivePresenter {
    void validateCredentials(MonitorLiveControllerView controllerView);

    void initNavigation(TabLayout tabLayout, ViewGroup viewGroup);
}
