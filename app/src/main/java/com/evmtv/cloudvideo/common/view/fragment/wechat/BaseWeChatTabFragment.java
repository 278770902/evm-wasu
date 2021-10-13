package com.evmtv.cloudvideo.common.view.fragment.wechat;

import android.os.Bundle;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.presenter.wechat.WeChatPageViewAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseWeChatTabFragment extends BaseFragment {

    private String TAG = getClass().getName();
    protected WeChatBean.WeChatNavigationBean.NavigationContentBean beanItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        beanItem = arguments.getParcelable(WeChatPageViewAdapter.FRAGMENT_INPUT_TYPE_VALUE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMsg(SendMessageEntity msg) {
    }
}
