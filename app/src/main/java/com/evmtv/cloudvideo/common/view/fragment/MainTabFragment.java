package com.evmtv.cloudvideo.common.view.fragment;

import android.os.Bundle;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseMainTabFragment;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.presenter.runnable.SecondLevelNavigationRunnable;


public class MainTabFragment extends BaseMainTabFragment {
    private RecyclerView secondLevelNavigationViewID;
    private TextView titleViewID;
    private String TAG = getClass().getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main_tab, container, false);
    }

    @Override
    protected void initView() {
        AppExecutors.getInstance().scheduledExecutor().execute(new SecondLevelNavigationRunnable(secondLevelNavigationViewID, titleViewID, beanItem,getActivity()));
    }
}
