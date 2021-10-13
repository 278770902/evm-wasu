package com.evmtv.cloudvideo.common.presenter.monitor.live.controller;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.evmtv.cloudvideo.common.presenter.monitor.live.CircleViewByImage;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveControllerView;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveTool;

public class HdViewTool implements CompoundButton.OnCheckedChangeListener {
    private MonitorLiveControllerView controllerView;
    private CheckBox checkBox;

    private static HdViewTool instance;

    public static HdViewTool getInstance() {
        synchronized (HdViewTool.class) {
            if (instance == null)
                instance = new HdViewTool();
        }
        return instance;
    }

    public void initView(MonitorLiveControllerView controllerView, CheckBox checkBox) {
        this.controllerView = controllerView;
        this.checkBox = checkBox;
        checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        MonitorLiveEntity.getInstance().setMode(!isChecked);
//        MonitorLiveTool.getInstance(null).stopVideoPlay(controllerView);
        MonitorLiveTool.getInstance(null).startVideoPlay(controllerView, null);
    }

    public void setChecked(Boolean isChecked) {
        if (checkBox == null) return;
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(isChecked);
        checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
    }
}
