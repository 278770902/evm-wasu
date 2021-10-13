package com.evmtv.cloudvideo.common.utils.fir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.evmtv.cloudvideo.common.utils.fir.config.DownloadKey;
import com.evmtv.cloudvideo.common.utils.fir.utils.GetAppInfo;
import com.evmtv.cloudvideo.common.utils.fir.view.UpdateDialog;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;

import java.lang.ref.WeakReference;


/**
 * Created by hugeterry(http://hugeterry.cn)
 */
public class HandleUpdateResult implements Runnable {

    private String version = "";
    private Up_handler up_handler;

    public HandleUpdateResult(Context context) {
        up_handler = new Up_handler(context);
        this.version = GetAppInfo.getAppVersionName(context);
    }

    private static class Up_handler extends Handler {
        WeakReference<Context> mActivityReference;

        public Up_handler(Context context) {
            mActivityReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final Context context = mActivityReference.get();
            if (context != null) {
                switch (msg.arg1) {
                    case 1:
                        showNoticeDialog(context);
                        break;
                    case 2:
                        if (DownloadKey.ISManual) {
                            DownloadKey.LoadManual = false;
                            ToastUtil.setToast("网络不畅通");
                        }
                        break;
                    case 3:
                        if (DownloadKey.ISManual) {
                            DownloadKey.LoadManual = false;
                            ToastUtil.setToast("版本已是最新");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void run() {
        Update update = new Update();
        update.start();

        Message msg = new Message();
        try {
            update.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (DownloadKey.version == null) {
            Log.i("UpdateFun TAG", "获取的应用信息为空，不更新，请确认网络是否畅通或者应用ID及API_TOKEN是否正确");
            msg.arg1 = 2;
            up_handler.sendMessage(msg);
        } else if (compareVersion(DownloadKey.version,version)>0) {
            Log.i("UpdateFun TAG", "需更新版本");
            msg.arg1 = 1;
            up_handler.sendMessage(msg);
        } else {
            Log.i("UpdateFun TAG", "版本已是最新");
            msg.arg1 = 3;
            up_handler.sendMessage(msg);
        }
    }

    public static void showNoticeDialog(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UpdateDialog.class);
        ((Activity) context).startActivityForResult(intent, 100);
    }
    /**
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        Log.d("HomePageActivity", "version1Array=="+version1Array.length);
        Log.d("HomePageActivity", "version2Array=="+version2Array.length);
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        Log.d("HomePageActivity", "verTag2=2222="+version1Array[index]);
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }
}
