package com.evmtv.cloudvideo.common.presenter.main;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

public class OnMainTabSelectedListener implements TabLayout.OnTabSelectedListener {

    private OnNavigationItemSelectedListener listener;
    private FragmentTransactionUtils.TransactionMainUtils transactionUtils;

    public OnMainTabSelectedListener(OnNavigationItemSelectedListener listener, FragmentTransactionUtils.TransactionMainUtils transactionUtils) {
        this.listener = listener;
        this.transactionUtils = transactionUtils;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Object item = tab.getTag();
        if (item instanceof NavigationBean.MainNavigationBean.NavigationContentBean) {
            NavigationBean.MainNavigationBean.NavigationContentBean bean = (NavigationBean.MainNavigationBean.NavigationContentBean) item;
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
