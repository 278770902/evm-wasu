package com.evmtv.cloudvideo.common.presenter.monitor;

import android.support.v7.widget.RecyclerView;

public interface KanJiaBaoPresenter {
    void InitDisplay(Object view, RecyclerView recyclerView);

    void initMonitorCameraInfo(String STBGuid);
}
