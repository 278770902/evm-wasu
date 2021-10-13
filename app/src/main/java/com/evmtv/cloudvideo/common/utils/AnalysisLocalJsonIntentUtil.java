package com.evmtv.cloudvideo.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;

//system
public class AnalysisLocalJsonIntentUtil {
    private static final String START_TYPE_SYSTEM = "system";
    private static final String START_TYPE_LOCAL = "local";

    private static AnalysisLocalJsonIntentUtil instance;

    public static AnalysisLocalJsonIntentUtil getInstance() {
        synchronized (AnalysisLocalJsonIntentUtil.class) {
            if (instance == null)
                instance = new AnalysisLocalJsonIntentUtil();
        }
        return instance;
    }

    /**
     * @param activity
     * @param bean     value:json 传递内容
     */
    public void startActivity(Activity activity, NavigationBean.MainNavigationBean.NavigationContentBean.ContentBean bean) {
        IntentLocalBean entity = JSONLocalObject.parseObject(bean.getValue(), IntentLocalBean.class);
        if (entity == null || entity.getAction() == null)
            return;
        switch (entity.getAction()) {
            case "android.intent.action.VIEW":
                if (Validator.isUrl(entity.getUrl())) {
                    Intent intent = new Intent(entity.getAction(), Uri.parse(entity.getUrl()));
                    activity.startActivity(intent);
                } else {
                    ToastUtil.setToast("请正确填些Url:" + entity.getUrl());
                }

                break;
        }
    }

}
