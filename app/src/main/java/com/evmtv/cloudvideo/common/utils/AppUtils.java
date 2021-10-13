package com.evmtv.cloudvideo.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.service.AppNoticeService;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.action.ActionName;
import com.evmtv.rtc.ERTCCommand;

import java.util.List;

public class AppUtils {
    private Context context;
    private static AppUtils instance;
    private PackageInfo packageInfo = null;
    private PackageManager packageManager = null;

    private AppUtils(Context context) {
        this.context = context;
    }

    public synchronized static AppUtils getInstance(Context context) {
        synchronized (AppUtils.class) {
            if (instance == null && context != null)
                instance = new AppUtils(context);
        }
        return instance;
    }

    public void init() {
        try {
            packageManager = context.getPackageManager();
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用程序名称
     */
    public String getAppName() {
        if (packageInfo == null)
            return "";
        return context.getResources().getString(packageInfo.applicationInfo.labelRes);
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public String getVersionName() {
        if (packageInfo != null)
            return packageInfo.versionName;
        return null;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public int getVersionCode() {
        if (packageInfo != null)
            return packageInfo.versionCode;
        return 0;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public String getPackageName() {
        if (packageInfo != null)
            return packageInfo.packageName;
        return null;
    }


    /**
     * 获取图标 bitmap
     */
    public Bitmap getBitmap() {
        Drawable d = null; //xxx根据自己的情况获取drawable
        try {
            d = packageManager.getApplicationIcon(packageInfo.packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }


    public static void startAppNoticeService(Context context) {
        if (SharedPreferencesUtil.getInstance().getUserGuid(false).isEmpty())
            return;
        if (!isServiceExisted(context, "com.evmtv.cloudvideo.common.service.AppNoticeService")) {
            Intent intent = new Intent(context, AppNoticeService.class);
            context.startService(intent);
        }
    }

    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;

            if (serviceName.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    public static void startLogin(Activity activity) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                com.evmtv.cloudvideo.common.http.ums.UMSInteractive.getInstance().saveDeviceToken(SharedPreferencesUtil.getInstance().getUserGuid(false),"");
                SharedPreferencesUtil.getInstance().saveData(SharedPreferencesText.LOGIN_CPN_USER_GUID, "");
            }
        });
        ERTCCommand.getInstance().mobile.common.logout();
        activity.stopService(new Intent(activity, AppNoticeService.class));
        Intent intent = new Intent();
        intent.setAction(ActionName.LOGIN_ACTION);
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        activity.startActivity(intent);
        activity.finish();
    }
}
