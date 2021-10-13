package com.evmtv.cloudvideo.common.presenter.wechat;


import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

public interface WeChatPresenter {
    void InitDisplay(Activity activity, FragmentManager fragmentManager, Object BottomView, Object PageView, OnWeChatItemSelectedListener listener);

    FragmentTransactionUtils.TransactionWeChatUtils getFragmentTransactionUtils();
}
