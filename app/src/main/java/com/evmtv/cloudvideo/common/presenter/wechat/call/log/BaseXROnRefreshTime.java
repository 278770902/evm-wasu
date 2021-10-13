package com.evmtv.cloudvideo.common.presenter.wechat.call.log;

import android.view.View;
import android.widget.AbsListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersListView;

public  class BaseXROnRefreshTime implements OnTopRefreshTime, OnBottomLoadMoreTime, AbsListView.OnScrollListener{
    private int mTotalItemCount;
    protected StickyListHeadersListView view;
    protected XRefreshView refreshView;

    public BaseXROnRefreshTime(StickyListHeadersListView view, XRefreshView refreshView) {
        this.view = view;
        this.refreshView = refreshView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalItemCount = totalItemCount;
    }


    @Override
    public boolean isTop() {
        if (view.getLastVisiblePosition() == mTotalItemCount - 1) {
            View lastChild = view.getListChildAt(view.getListChildCount() - 1);
            if (lastChild == null)
                return false;
            return (lastChild.getBottom() + view.getPaddingBottom()) <= view.getMeasuredHeight();
        }
        //没有到达底部则返回false
        return false;
    }

    @Override
    public boolean isBottom() {
        if (view.getFirstVisiblePosition() == 0) {
            View firstVisibleChild = view.getListChildAt(0);
            if (firstVisibleChild == null)
                return true;
            return firstVisibleChild.getTop() >= 0;
        }
        return false;
    }
}
