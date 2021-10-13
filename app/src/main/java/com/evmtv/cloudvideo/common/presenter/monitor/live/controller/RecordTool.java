package com.evmtv.cloudvideo.common.presenter.monitor.live.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.util.view.EvmPlayerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RecordTool {
    private Context mContext;
    private long RecordTime = 0;
    private static RecordTool instance;
    private KanJiaBaoRawRecordEntity entity;

    private final int SEND_RECORD_START_MESSAGE_ID = 11111;
    private final int SEND_RECORD_STOP_MESSAGE_ID = SEND_RECORD_START_MESSAGE_ID + 1;
    private final int SEND_RECORD_KEEP_MESSAGE_ID = SEND_RECORD_STOP_MESSAGE_ID + 1;

    private ImageButton RecordTimeButtonView;
    private TextView RecordTimeViewId;

    public RecordTool(Context mContext) {
        this.mContext = mContext;
    }

    public static RecordTool getInstance(Context mContext) {
        synchronized (RecordTool.class) {
            if (instance == null)
                instance = new RecordTool(mContext);
        }
        return instance;
    }

    public void setRecordTimeButtonView(ImageButton recordTimeButtonView, TextView RecordTimeViewId) {
        RecordTimeButtonView = recordTimeButtonView;
        this.RecordTimeViewId = RecordTimeViewId;
    }

    public void RawRecord(EvmPlayerView playerView) {
        if (playerView == null || !playerView.isPlaying())
            return;
        entity = (KanJiaBaoRawRecordEntity) playerView.getTag();
        RecordTime = 0;
        if (entity == null || entity.getState() == null || entity.getState().equals("stop")) {
            String path = Environment.getExternalStorageDirectory().getPath() + "/evm";
            if (!new File(path).exists()) {
                new File(path).mkdir();
            }
            if (entity == null)
                entity = new KanJiaBaoRawRecordEntity();
            entity.setRawRecordPath(path + "/" + getDateToString(System.currentTimeMillis()) + ".mp4");
            playerView.startRawRecord(entity.getRawRecordPath());
            entity.setState("start");
            playerView.setTag(entity);
            timerHandler.sendEmptyMessage(SEND_RECORD_START_MESSAGE_ID);
            if (RecordTimeButtonView != null)
                RecordTimeButtonView.setImageResource(R.drawable.item_monitor_start_record_bg);
        } else {
            playerView.stopRawRecord();
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + playerView.getTag())));
            KanJiaBaoRawRecordEntity entityStop = (KanJiaBaoRawRecordEntity) playerView.getTag();
            entityStop.setState("stop");
            playerView.setTag(entityStop);
            timerHandler.sendEmptyMessage(SEND_RECORD_STOP_MESSAGE_ID);
            if (RecordTimeButtonView != null)
                RecordTimeButtonView.setImageResource(R.drawable.item_monitor_stop_record_bg);
        }
    }

    private static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private Handler timerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (this == null) {
                return;
            }
            switch (msg.what) {
                case SEND_RECORD_START_MESSAGE_ID:
                    if (RecordTimeViewId != null)
                        RecordTimeViewId.setText("00:00:00");
                    RecordTime = 0;
                    timerHandler.sendEmptyMessageDelayed(SEND_RECORD_KEEP_MESSAGE_ID, 1000);
                    break;
                case SEND_RECORD_STOP_MESSAGE_ID:
                    RecordTime = 0;
                    if (RecordTimeViewId != null)
                        RecordTimeViewId.setText("00:00:00");
                    timerHandler.removeMessages(SEND_RECORD_KEEP_MESSAGE_ID);
                    break;
                case SEND_RECORD_KEEP_MESSAGE_ID:
                    RecordTime += 1000;
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
                    if (RecordTimeViewId != null)
                        RecordTimeViewId.setText(sdf.format(RecordTime));
                    timerHandler.sendEmptyMessageDelayed(SEND_RECORD_KEEP_MESSAGE_ID, 1000);
                    break;
            }
        }
    };
}
