package com.evmtv.cloudvideo.common.presenter.wechat;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

public class OnWeChatTabSelectedListener implements TabLayout.OnTabSelectedListener {

    private OnWeChatItemSelectedListener listener;
    private FragmentTransactionUtils.TransactionWeChatUtils transactionUtils;

    public OnWeChatTabSelectedListener(OnWeChatItemSelectedListener listener, FragmentTransactionUtils.TransactionWeChatUtils transactionUtils) {
        this.listener = listener;
        this.transactionUtils = transactionUtils;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Object item = tab.getTag();
        if (item instanceof WeChatBean.WeChatNavigationBean.NavigationContentBean) {
            WeChatBean.WeChatNavigationBean.NavigationContentBean bean = (WeChatBean.WeChatNavigationBean.NavigationContentBean) item;
            listener.onNavigationItemSelected(tab.getPosition(), bean);
        }
        Log.i("litao", "onTabSelected position=" + tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.i("litao", "onTabUnselected position=" + tab.getPosition());
        if (transactionUtils != null)
            transactionUtils.hide(tab.getPosition());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.i("litao", "onTabReselected position=" + tab.getPosition());
    }
}
