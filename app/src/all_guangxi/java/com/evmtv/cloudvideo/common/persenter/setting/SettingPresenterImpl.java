package com.evmtv.cloudvideo.common.persenter.setting;

import android.content.Context;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.setting.BaseSettingPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingItemSelectedListener;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingTwoItemSelectedListener;

public class SettingPresenterImpl extends BaseSettingPresenterImpl {

    public SettingPresenterImpl(Context activity, NavigationBean.MainNavigationBean.NavigationContentBean beanItem, OnSettingItemSelectedListener listener, OnSettingTwoItemSelectedListener listenerTwo) {
        super(activity, beanItem, listener, listenerTwo);
    }
}
