package com.evmtv.cloudvideo.common.presenter.wechat.call.log;

import android.app.Activity;

import com.andview.refreshview.XRefreshView;
import com.evmtv.cloudvideo.common.model.http.csm.CallLogEntity;
import com.evmtv.cloudvideo.common.presenter.wechat.BaseStickyAdapter;
import com.evmtv.cloudvideo.common.view.tool.sticky.CustomHeaderRefreshView;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersListView;

public class XROnRefreshTime extends BaseXROnRefreshTime implements CallLogHandlerPresenter {

    private Activity activity;
    //    private CallLogAdapter adapter;
    private BaseStickyAdapter adapter;
    private XRefreshView.XRefreshViewListener listener;


    public XROnRefreshTime(Activity activity, StickyListHeadersListView view, XRefreshView refreshView) {
        super(view, refreshView);
        this.activity = activity;
    }

    public void setXRefreshViewListener(XRefreshView.XRefreshViewListener l) {
        this.listener = l;
    }


    @Override
    public void initXRefreshView( BaseStickyAdapter adapter) {
//        adapter = new CallLogAdapter(entity, activity);
        this.adapter = adapter;
        view.setOnScrollListener(this);
        view.setAdapter(adapter);

        refreshView.setPullLoadEnable(false);
        refreshView.setAutoRefresh(false);
        refreshView.setPinnedTime(1000);
        refreshView.setOnTopRefreshTime(this);
        refreshView.setOnBottomLoadMoreTime(this);
        refreshView.setCustomHeaderView(new CustomHeaderRefreshView(activity));
        if (listener != null)
            refreshView.setXRefreshViewListener(listener);
    }


    @Override
    public void ExtractDataAdapter(int type, boolean isSuccess,Object entity) {
        switch (type) {
            case CallLogHandler.LOAD_MORE_NO:
                refreshView.stopLoadMore(isSuccess);
                break;
            case CallLogHandler.REFRESH_NO:
                refreshView.stopRefresh(isSuccess);
                break;
        }
        adapter.setEntity(entity);
    }
}
