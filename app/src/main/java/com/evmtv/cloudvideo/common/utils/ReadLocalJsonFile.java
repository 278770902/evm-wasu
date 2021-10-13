package com.evmtv.cloudvideo.common.utils;

import android.content.Context;
import android.util.Log;

import com.evmtv.cloudvideo.common.model.local.MonitorControlEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.utils.fir.config.UpdateKey;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class ReadLocalJsonFile {
    private static NavigationBean navigationBean;

    public static String read(Context activity, String fileName) {
        /*获取到assets文件下的TExt.json文件的数据，并以输出流形式返回。*/
        String fileUrl = "assets/" + fileName;
        try {
            File f = new File(fileUrl);
            f.exists();
        } catch (Exception e) {
            e.getMessage();
        }

        InputStream is = activity.getClassLoader().getResourceAsStream(fileUrl);
        InputStreamReader streamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                // stringBuilder.append(line);
                stringBuilder.append(line);
            }
            reader.close();
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("litao", stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static void initData(Context activity) {
        String navigationJson = ReadLocalJsonFile.read(activity, "navigationInfo.json");
        navigationBean = JSONLocalObject.parseObject(navigationJson, NavigationBean.class);
        UpdateKey.APP_ID = navigationBean.getFirAppId();
        Collections.sort(navigationBean.getMainNavigation().getNavigationContent());
    }

    public static String LocalJsonCode() {
        if (navigationBean != null)
            return navigationBean.getVersionCode();
        ToastUtil.setToast("获取本地Json失败");
        return null;
    }

    public static String LocalJsonName() {
        if (navigationBean != null)
            return navigationBean.getVersionName();
        ToastUtil.setToast("获取本地Json失败");
        return null;
    }

    public static NavigationBean.MainNavigationBean getMainBottomNavigationBean() {
        if (navigationBean != null)
            return navigationBean.getMainNavigation();
        ToastUtil.setToast("获取本地Json失败");
        return null;
    }

    public static NavigationBean getLocalBean() {
        return navigationBean;
    }

    public static String logOutTitle() {
        return navigationBean.getLogOutTitle();
    }

    public static int getDownloadAppQRID() {
        return ResConversionUtil.getDrawableOrMipmapId(navigationBean.getDownloadAppQR());
    }

    public static String getDownloadAppUrl() {
        return navigationBean.getDownloadAppUrl();
    }

    public static String getPublicAccountGuid() {
        return navigationBean.getPublicAccountGuid();
    }

    public static NavigationBean.LoginTextOnClick getLoginTextOnClickEntity() {
        return navigationBean.getLoginTextOnClick();
    }

    public static String getStartForgetPassWorldAction() {
        return navigationBean.getStartForgetPassWorldAction();
    }

    public static MonitorControlEntity getMonitorControl() {
        return JSONLocalObject.parseObject("monitor/control.json", MonitorControlEntity.class);
    }

    public static List<NavigationBean.GuideSplashBean> getGuideSplashBg() {
        if (navigationBean == null)
            return null;
        if (navigationBean.getGuideSplash() != null && navigationBean.getGuideSplash().size() == 0)
            return null;
        if (navigationBean.getGuideSplash() != null)
            Collections.sort(navigationBean.getGuideSplash());
        return navigationBean.getGuideSplash();
    }
}
