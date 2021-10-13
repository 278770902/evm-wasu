package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceEntity;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceStatusEntity;
import com.evmtv.cloudvideo.common.model.http.csm.OnlineEntity;
import com.evmtv.cloudvideo.common.model.http.ums.BindUserEntity;
import com.evmtv.cloudvideo.common.model.http.ums.CallTempletListEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class TerminalCameraTool {
    private Context mContext;
    private String TAG = getClass().getName();
    private static TerminalCameraTool instance;
    private KanJiaBaoCameraAdapter cameraAdapter;

    public static TerminalCameraTool getInstance(Context mContext) {
        synchronized (TerminalCameraTool.class) {
            if (instance == null)
                instance = new TerminalCameraTool(mContext);
        }
        return instance;
    }


    public Boolean isPass(MonitorCameraInfoEntity entity) {
        if (entity.getErrCode() == -1)
            return false;
        if (entity.getDataSize() <= 0)
            return false;
        return true;
    }

    public void setCameraAdapter(KanJiaBaoCameraAdapter cameraAdapter) {
        this.cameraAdapter = cameraAdapter;
    }

    public KanJiaBaoCameraAdapter getCameraAdapter() {
        return cameraAdapter;
    }

    public TerminalCameraTool(Context mContext) {
        this.mContext = mContext;
    }

    public CallTempletListEntity parseCallTemp(String json) {
        try {
            return JSON.parseObject(json, CallTempletListEntity.class);
        } catch (Exception e) {
            return new CallTempletListEntity(-1);
        }
    }

    public BindUserEntity parseBindUser(String json) {
        try {
            return JSON.parseObject(json, BindUserEntity.class);
        } catch (Exception e) {
            return new BindUserEntity(-1);
        }
    }

    public MonitorCameraInfoEntity parseMonitorCameraInfo(String json) {
        try {
            return JSON.parseObject(json, MonitorCameraInfoEntity.class);
        } catch (Exception e) {
            return new MonitorCameraInfoEntity(-1);
        }
    }

    public AlarmDeviceStatusEntity parseAlarmStatus(String json) {
        try {
            return JSON.parseObject(json, AlarmDeviceStatusEntity.class);
        } catch (Exception e) {
            return new AlarmDeviceStatusEntity(false);
        }
    }

    public OnlineEntity parseOnlineEntity(String json) {
        try {
            return JSON.parseObject(json, OnlineEntity.class);
        } catch (Exception e) {
            return new OnlineEntity(false);
        }
    }

     public AlarmDeviceEntity parseAlarmDeviceEntity(String json) {
        try {
            return JSON.parseObject(json, AlarmDeviceEntity.class);
        } catch (Exception e) {
            return new AlarmDeviceEntity(false);
        }
    }



    public void initCameraIsOnline(String sn, ImageView imageView) {
        AppExecutors.getInstance().networkIOToMain(mContext,new OrderMethodInter() {
            OnlineEntity entity;

            @Override
            public void IO() {
                entity = parseOnlineEntity(CsmInteractive.getInstance().isOnline(sn));
            }

            @Override
            public void Main() {
                if (entity.isResult() && entity.isOnline()) {
                    imageView.setImageResource(R.drawable.item_kanjiabao_camera_online);
                    imageView.setTag(R.id.KanJiaBaoItemOnline, true);
                } else {
                    imageView.setImageResource(R.drawable.item_kanjiabao_camera);
                    imageView.setTag(R.id.KanJiaBaoItemOnline, false);
                }
            }
        });
    }

    public void kanJiaModelSwitchBg(boolean isOpen, KanJiaBaoCameraViewHolder holder) {
        if (isOpen) {
            holder.startModeKanJiaBaoItemViewID.setBackground(mContext.getResources().getDrawable(R.drawable.layer_kanjiabao_user_on));
            holder.startModeKanJiaBaoItemTextViewID.setText(R.string.kanJiaBaoMainItemModeOffText);
        } else {
            holder.startModeKanJiaBaoItemViewID.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            holder.startModeKanJiaBaoItemTextViewID.setText(R.string.kanJiaBaoMainItemModeOnText);
        }
    }
}
