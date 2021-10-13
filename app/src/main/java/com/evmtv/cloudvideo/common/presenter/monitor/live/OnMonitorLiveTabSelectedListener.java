package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.MonitorControlEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class OnMonitorLiveTabSelectedListener implements TabLayout.OnTabSelectedListener {

    private OnNavigationItemSelectedListener listener;
    //    private FragmentTransactionUtils.TransactionMainUtils transactionUtils;
    private String TAG = getClass().getName();

    public OnMonitorLiveTabSelectedListener(OnNavigationItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.i(TAG, "onTabSelected position=" + tab.getPosition());
        if (listener == null) return;
        MonitorControlEntity.ControlBean bean = (MonitorControlEntity.ControlBean) tab.getCustomView().getTag(R.id.MonitorLiveTabEntity);
        listener.onNavigationItemSelected(tab.getPosition(), bean);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.i(TAG, "onTabUnselected position=" + tab.getPosition());
//        if (transactionUtils != null)
//            transactionUtils.hide(tab.getPosition());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.i(TAG, "onTabReselected position=" + tab.getPosition());
    }
}
