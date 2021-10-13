package com.evmtv.cloudvideo.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.evmtv.cloudvideo.common.view.tool.XLog;
public class AppNoticeService extends Service {

    private Thread thread = null;
    private boolean needExitThread = false;
    private String TAG = "AppNoticeService";

    public AppNoticeService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onStartCommandDeal();
        return super.onStartCommand(intent, flags, startId);
    }

    private void onStartCommandDeal() {
        synchronized (this) {
            if (thread == null)
                thread = new Thread(new NoticeServiceRunnable());
            thread.start();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        XLog.i(TAG, "onDestroy");
        synchronized (this) {
            if (thread != null) {
                needExitThread = true;
                thread.interrupt();
            }
        }
        super.onDestroy();
    }

    private void ThreadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class NoticeServiceRunnable implements Runnable {

        @Override
        public void run() {
//            String guid = null;
//            while (!thread.isInterrupted() && !needExitThread) {
//                guid = SharedPreferencesUtil.getInstance().getUserGuid(false);
//                if ((guid == null || guid.isEmpty())) {
//                    XLog.i(TAG, "guid is null");
//                    ThreadSleep(1000);
//                    continue;
//                } else
//                    break;
//            }
//
//            while (!thread.isInterrupted() && !needExitThread) {
//                if (StateMachine.getInstance().isBackground()) {
//                    XLog.d(TAG, "in background");
//                    ThreadSleep(1000);
//                    continue;
//                }
//                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
//                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
//                String json = EdumsInteractive.getInstance().findPhoneIfExist(tel, tel, ssoToken);
//                try {
//                    JSONObject object = JSON.parseObject(json);
//                    String code = object.containsKey("code") ? object.getString("code") : "-1";
//                    String msg = object.containsKey("msg") ? object.getString("msg") : "";
//                    if (code.equals("505") || msg.equals("登录失效，请重新登录!")) {
//                        EventBus.getDefault().postSticky(new SendMessageEntity<>()
//                                .setMsgId(SendMessageEntity.MsgId.SEND_LOG_OUT));
//                        break;
//                    }
//                } catch (Exception e) {
//                }
//
//
//                ThreadSleep(2000);
//            }
        }
    }
}
