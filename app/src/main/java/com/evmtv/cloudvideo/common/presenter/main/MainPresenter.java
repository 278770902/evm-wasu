package com.evmtv.cloudvideo.common.presenter.main;


import android.support.v7.app.AppCompatActivity;

import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

public interface MainPresenter {
    void InitDisplay(AppCompatActivity activity, Object BottomView, Object PageView, OnNavigationItemSelectedListener listener);

    FragmentTransactionUtils.TransactionMainUtils getFragmentTransactionUtils();

}
