package com.evmtv.cloudvideo.common.presenter.base;

import android.os.Bundle;

import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.evmtv.cloudvideo.common.presenter.main.MainPageViewAdapter;

public abstract class BaseMainTabFragment extends BaseFragment {
    private String TAG = getClass().getName();
    protected NavigationBean.MainNavigationBean.NavigationContentBean beanItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        beanItem = arguments.getParcelable(MainPageViewAdapter.FRAGMENT_INPUT_TYPE_VALUE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMsg(SendMessageEntity msg) {
    }
}
