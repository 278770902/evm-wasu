package com.evmtv.cloudvideo.common.presenter.wechat.call.log;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.CallLogEntity;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.tool.sticky.CustomHeaderRefreshView;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersListView;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class CallLogPresenterImpl implements CallLogPresenter, XRefreshView.XRefreshViewListener {
    private Activity activity;
    private Object xRefreshView, listView;
    private CallLogHandler handler;
    private CallLogHandlerPresenter callLogPresenter;


    @Override
    public void InitDisplay(Activity activity, Object ListView, Object xRefreshViews) {
        this.xRefreshView = xRefreshViews;
        this.listView = ListView;
        this.activity = activity;
        initXRefreshView(null);
    }

    @Override
    public void ExtractData(int NoType) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                CallLogEntity entity = JSONLocalObject.parseObject(CsmInteractive.getInstance().getHistoryVideoCallInfo(), CallLogEntity.class);
                handler.sendMessage(Message.obtain(handler, NoType,entity.isResult()?CallLogHandler.RESULT_SUCCESS:CallLogHandler.RESULT_FAIL,0, entity));
            }
        });
    }


    public void initXRefreshView(CallLogEntity entity) {
        if (listView instanceof StickyListHeadersListView && xRefreshView instanceof XRefreshView) {
            StickyListHeadersListView view = ((StickyListHeadersListView) listView);
            XRefreshView refreshView = ((XRefreshView) xRefreshView);
            callLogPresenter = new XROnRefreshTime(activity, view, refreshView);
            callLogPresenter.setXRefreshViewListener(this);
            handler = new CallLogHandler(callLogPresenter);
            callLogPresenter.initXRefreshView(new CallLogAdapter(entity, activity));
        }
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onRefresh(boolean isPullDown) {
        ExtractData(CallLogHandler.REFRESH_NO);
    }

    @Override
    public void onLoadMore(boolean isSilence) {
        ExtractData(CallLogHandler.LOAD_MORE_NO);
    }

    @Override
    public void onRelease(float direction) {
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }
}
