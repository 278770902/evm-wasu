package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.StartPlayEntity;
import com.evmtv.cloudvideo.common.model.local.MonitorControlEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.controller.HdViewTool;
import com.evmtv.cloudvideo.common.presenter.video.VideoTimeHandler;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.utils.permissions.PermissionsUtils;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.util.view.EvmPlayerView;

import java.lang.reflect.Field;

public class MonitorLiveTool {
    private Activity mContext;
    private int CurrentIndex = 0;
    private static MonitorLiveTool instance;
    private PermissionsUtils.IPermissionsResult permissionsResult;
    public static String[] recordPermission = new String[]{"android.permission.RECORD_AUDIO", Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static MonitorLiveTool getInstance(Activity mContext) {
        synchronized (MonitorLiveTool.class) {
            if (instance == null)
                instance = new MonitorLiveTool(mContext);
        }
        return instance;
    }

    public int getCurrentIndex() {
        return CurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        CurrentIndex = currentIndex;
    }

    public MonitorLiveTool(Activity mContext) {
        this.mContext = mContext;
    }

    public StartPlayEntity parseStartPlayEntity(String json) {
        if (json != null)
            XLog.i(getClass().getName(), "----json=" + json);
        try {
            return JSON.parseObject(json, StartPlayEntity.class);
        } catch (Exception e) {
            return new StartPlayEntity(false);
        }
    }

    public Boolean dealStartPlay(StartPlayEntity entity) {
        if (entity.getResult())
            return true;
        if (entity.getErrorDeviceOffLine()) {
            MonitorLiveEntity.getInstance().getVideoViewID().setOutlineVisibleBg();
            ToastUtil.setToast("摄像头不在线，请查看连接");
            return false;
        }
        if (entity.getErrorDeviceBusy()) {
            MonitorLiveEntity.getInstance().getVideoViewID().setBeforePlayVisibleBg();
            ToastUtil.setToast("设备繁忙，请稍后再试");
            return false;
        }
        MonitorLiveEntity.getInstance().getVideoViewID().setBeforePlayVisibleBg();
        return false;
    }

    public void startVideoPlay(MonitorLiveControllerView controllerView, CompoundButton buttonView) {
        try {
            MonitorLiveEntity.getInstance().getVideoViewID().stopPlay();
            controllerView.removeMessages(VideoTimeHandler.LIVE_MONITOR_KEEP_MESSAGE_ID);
        } catch (Exception e) {
            XLog.e(getClass().getName(), e.getMessage());
        }
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            StartPlayEntity entity;

            @Override
            public void IO() {
                if (controllerView != null && controllerView.getSession() != null && controllerView.getSession().length() > 0) {
                    CsmInteractive.getInstance().stopPlay(controllerView.getSession());
                    controllerView.setSession("");
                }
                entity = parseStartPlayEntity(
                        CsmInteractive.getInstance().startPlay(MonitorLiveEntity.getInstance().getSerialNum(),
                                SharedPreferencesUtil.getInstance().getUserGuid(true)
                                , MonitorLiveEntity.getInstance().getSTBGuid()
                                , MonitorLiveEntity.getInstance().getMode()));

            }

            @Override
            public void Main() {
                if (!dealStartPlay(entity)) {
                    if (buttonView != null) {
                        MonitorLiveEntity.getInstance().setMode(!MonitorLiveEntity.getInstance().getMode());
                        HdViewTool.getInstance().setChecked(!buttonView.isChecked());
                    }
                    return;
                }
                MonitorLiveEntity.getInstance().getVideoViewID().setMediaController(controllerView);
                controllerView.setSession(entity.getSessionId());
                controllerView.startPlay(entity.getPlayUrl());
            }
        });
    }

    public void stopVideoPlay(MonitorLiveControllerView controllerView) {
        try {
            controllerView.removeMessages(VideoTimeHandler.LIVE_MONITOR_KEEP_MESSAGE_ID);
            MonitorLiveEntity.getInstance().getVideoViewID().stopPlay();
            if (controllerView.getSession() == null || controllerView.getSession().length() <= 0)
                return;
            AppExecutors.getInstance().networkIO().execute(new Runnable() {
                @Override
                public void run() {
                    CsmInteractive.getInstance().stopPlay(controllerView.getSession());
                    controllerView.setSession("");
                }
            });
        } catch (Exception e) {
            XLog.e("MonitorLiveTool", "error=" + e.getMessage());
        }

    }


    /**
     * @return
     */
    public View makeTabView(MonitorControlEntity.ControlBean entity, int index) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.item_monitor_control_tab, null);
        MonitorLiveControlViewHolder viewHolder = new MonitorLiveControlViewHolder(tabView);
        tabView.setTag(R.id.MonitorLiveTabSelectView, viewHolder);
        viewHolder.ControlViewID.setImageResource(ResConversionUtil.getDrawableOrMipmapId(entity.getIcon()));
        tabView.setTag(R.id.MonitorLiveTabEntity, entity);
//        tabView.setTag(R.id.KanJiaBaoTabSelectStbGuid, entity.getGUID());
        return tabView;
    }

    /**
     * 配置竖屏播控
     *
     * @param viewGroup
     * @param entity
     * @param controllerView
     * @return
     */
    public View makeTabLayoutView(ViewGroup viewGroup, MonitorControlEntity.ControlBean entity, MonitorLiveControllerView controllerView) {
        if (viewGroup == null)
            return null;
        try {
            viewGroup.removeAllViews();
            Field field = R.layout.class.getField(entity.getLayout());
            int layoutId = field.getInt(new R.layout());
            View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
            viewGroup.setTag(new MonitorControllerHolder(view, controllerView, mContext));
            viewGroup.addView(view);
            return view;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void makeTabStartActivity(MonitorControlEntity.ControlBean entity) {
        String action = entity.getLayout();
        if (!SystemUtil.getInstance(mContext).isActionSupport(action))
            return;
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        mContext.startActivity(intent);
    }

    public void checkPermissions(Activity context, @NonNull PermissionsUtils.IPermissionsResult permissionsResult) {
        if (permissionsResult != null)
            this.permissionsResult = permissionsResult;
        PermissionsUtils.getInstance().checkPermissions(context, recordPermission, this.permissionsResult);
    }


    public void onRequestPermissionsResult(Activity context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsUtils.getInstance().onRequestPermissionsResult(context, requestCode, permissions, grantResults);
    }


    public void WindowSwitch(int requestedOrientation) {
        EvmPlayerView playerView = MonitorLiveEntity.getInstance().getVideoViewID();
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            ConstraintLayout.LayoutParams lp1 = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
            lp1.dimensionRatio = "h,16:9";
            lp1.bottomToBottom = ConstraintLayout.LayoutParams.UNSET;
            lp1.topToTop = ConstraintLayout.LayoutParams.UNSET;
//            lp1.topToBottom = R.id.titleRelViewID;
            playerView.requestLayout();
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        if (!playerView.isPlaying())
            return;

        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
            lp.dimensionRatio = null;
            lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            playerView.requestLayout();
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
