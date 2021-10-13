package com.evmtv.cloudvideo.common.presenter.runnable;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.adapter.MainTabRecyclerAdapter;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.model.local.SecondLevelNavigationEntity;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;


public class SecondLevelNavigationRunnable implements Runnable {
    protected RecyclerView presenter;
    private TextView titleViewID;
    private Activity activity;
    private NavigationBean.MainNavigationBean.NavigationContentBean beanItem;
    private String TAG = getClass().getName();

    public SecondLevelNavigationRunnable(RecyclerView presenter, TextView titleViewID, NavigationBean.MainNavigationBean.NavigationContentBean beanItem, Activity activity) {
        this.presenter = presenter;
        this.activity = activity;
        this.titleViewID = titleViewID;
        this.beanItem = beanItem;
    }

    @Override
    public void run() {
        if (beanItem == null || beanItem.getContent() == null)
            return;
        final SecondLevelNavigationEntity secondLevelNavigationEntity
                = JSONLocalObject.parseObject(beanItem.getContent().getValue(), SecondLevelNavigationEntity.class);
        Log.i(TAG, "---------------1-----------");
        if (presenter != null && secondLevelNavigationEntity != null) {
            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "---------------2-----------" + beanItem.getContent().getValue());
                    presenter.setLayoutManager(new LinearLayoutManager(activity));
                    titleViewID.setText(beanItem.getTitle());
                    presenter.setAdapter(new MainTabRecyclerAdapter(activity, secondLevelNavigationEntity));
                }
            });
        }
    }
}
