package com.evmtv.cloudvideo.common.presenter.wechat.call.log;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.andview.refreshview.XRefreshView;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

public interface CallLogPresenter {
    void InitDisplay(Activity activity, Object listView, Object xRefreshView);

    void ExtractData(int  NoType);
}
