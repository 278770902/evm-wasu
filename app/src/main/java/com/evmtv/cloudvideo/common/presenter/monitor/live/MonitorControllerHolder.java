package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.monitor.live.controller.CircleViewTool;
import com.evmtv.cloudvideo.common.presenter.monitor.live.controller.HdViewTool;
import com.evmtv.cloudvideo.common.presenter.monitor.live.controller.RecordTool;
import com.evmtv.cloudvideo.common.presenter.monitor.live.controller.ScreenshotTool;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class MonitorControllerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CircleViewByImage kanJiaBaoCircleViewByImageViewID;
    private ImageButton KanJiaBaoCallPoliceJJ, KanJiaBaoCallPoliceHZ, KanJiaBaoCallPoliceDQ, KanJiaBaoCallPoliceService;
    private CheckBox kanjiabaoLiveHdCheckBoxViewId;
    private ImageButton kanJiaBaoMicroPhoneIBViewId, kanJiaBaoMicroPhoneCloseViewId;
    private ProgressBar livePortraitProgressBarViewId;
    private TextView kanjiabaoLiveVideoPlayerRecordTimeTextViewId;
    private ImageButton kanjiabaoLiveVideoPlayerRecordTimeButtonViewId, KanJiaBaoScreenshotViewId;
    private Activity mContext;

    public MonitorControllerHolder(View itemView, MonitorLiveControllerView controllerView, Activity mContext) {
        super(itemView);
        this.mContext = mContext;
        try {
            kanJiaBaoCircleViewByImageViewID = itemView.findViewById(R.id.kanJiaBaoCircleViewByImageViewID);
            CircleViewTool.getInstance().initView(controllerView, kanJiaBaoCircleViewByImageViewID);
            XLog.e(getClass().getName(), " circle control");
        } catch (Exception e) {
        }
        //---------------------------------------------
        try {
            itemView.findViewById(R.id.KanJiaBaoCallPoliceJJ).setOnClickListener(this::onClick);
            itemView.findViewById(R.id.KanJiaBaoCallPoliceHZ).setOnClickListener(this::onClick);
            itemView.findViewById(R.id.KanJiaBaoCallPoliceDQ).setOnClickListener(this::onClick);
            itemView.findViewById(R.id.KanJiaBaoCallPoliceService).setOnClickListener(this::onClick);
            XLog.e(getClass().getName(), " police control");
        } catch (Exception e) {
        }
        try {
            kanjiabaoLiveHdCheckBoxViewId = itemView.findViewById(R.id.kanjiabaoLiveHdCheckBoxViewId);
            HdViewTool.getInstance().initView(controllerView, kanjiabaoLiveHdCheckBoxViewId);
            XLog.e(getClass().getName(), " hd control");
        } catch (Exception e) {
        }

        try {
            kanJiaBaoMicroPhoneIBViewId = itemView.findViewById(R.id.kanJiaBaoMicroPhoneIBViewId);
            kanJiaBaoMicroPhoneCloseViewId = itemView.findViewById(R.id.kanJiaBaoMicroPhoneCloseViewId);
            livePortraitProgressBarViewId = itemView.findViewById(R.id.livePortraitProgressBarViewId);
            XLog.e(getClass().getName(), "micro control");
        } catch (Exception e) {
        }

        try {
            kanjiabaoLiveVideoPlayerRecordTimeButtonViewId = itemView.findViewById(R.id.kanjiabaoLiveVideoPlayerRecordTimeButtonViewId);
            kanjiabaoLiveVideoPlayerRecordTimeTextViewId = itemView.findViewById(R.id.kanjiabaoLiveVideoPlayerRecordTimeTextViewId);
            kanjiabaoLiveVideoPlayerRecordTimeButtonViewId.setOnClickListener(this::onClick);
            XLog.e(getClass().getName(), "record control");
        } catch (Exception e) {
        }

        try {
            KanJiaBaoScreenshotViewId = itemView.findViewById(R.id.KanJiaBaoScreenshotViewId);
            KanJiaBaoScreenshotViewId.setOnClickListener(this::onClick);
            XLog.e(getClass().getName(), "screenShort control");
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.KanJiaBaoCallPoliceJJ:
                ToastUtil.setToast("急救报警");
                break;
            case R.id.KanJiaBaoCallPoliceHZ:
                ToastUtil.setToast("火灾报警");
                break;
            case R.id.KanJiaBaoCallPoliceDQ:
                ToastUtil.setToast("盗贼报警");
                break;
            case R.id.KanJiaBaoCallPoliceService:
                ToastUtil.setToast("广电服务");
                break;
            case R.id.KanJiaBaoScreenshotViewId:
                ScreenshotTool.getInstance().startScreenshot(MonitorLiveEntity.getInstance().getVideoViewID()
                        , mContext, MonitorLiveEntity.getInstance().getSerialNum()
                        , MonitorLiveEntity.getInstance().getScreenShotView()
                        , true, true);
                break;
            case R.id.kanjiabaoLiveVideoPlayerRecordTimeButtonViewId:
                if (!MonitorLiveEntity.getInstance().isPassPermissionRecord()) {
                    ToastUtil.setToast("请先申请录制权限");
                    MonitorLiveTool.getInstance(null).checkPermissions(mContext, null);
                    break;
                }
                RecordTool.getInstance(mContext).setRecordTimeButtonView(kanjiabaoLiveVideoPlayerRecordTimeButtonViewId, kanjiabaoLiveVideoPlayerRecordTimeTextViewId);
                RecordTool.getInstance(mContext).RawRecord(MonitorLiveEntity.getInstance().getVideoViewID());
                break;
        }
    }
}
