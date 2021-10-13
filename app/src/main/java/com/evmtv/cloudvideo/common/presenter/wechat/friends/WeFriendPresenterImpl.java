package com.evmtv.cloudvideo.common.presenter.wechat.friends;

import android.app.Activity;
import android.os.Message;
import android.util.Log;

import com.andview.refreshview.XRefreshView;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.CallLogEntity;
import com.evmtv.cloudvideo.common.model.http.ums.UserFriendsEntity;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogHandler;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogHandlerPresenter;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.XROnRefreshTime;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersListView;

public class WeFriendPresenterImpl implements WeFriendPresenter, XRefreshView.XRefreshViewListener {

    private Object xRefreshView, listView;
    private CallLogHandlerPresenter presenter;
    private Activity activity;
    private CallLogHandler handler;

    @Override
    public void InitDisplay(Activity activity, Object listView, Object xRefreshView) {
        this.xRefreshView = xRefreshView;
        this.listView = listView;
        this.activity = activity;
        initXRefreshView(null);
    }

    @Override
    public void ExtractData(int NoType) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                UserFriendsEntity entity = JSONLocalObject.parseObject(UMSInteractive.getInstance().getUserFriends(), UserFriendsEntity.class);
                handler.sendMessage(Message.obtain(handler, NoType, entity.getIsSuccess(), 0, entity));
            }
        });
    }

    public void initXRefreshView(UserFriendsEntity entity) {
        if (listView instanceof StickyListHeadersListView && xRefreshView instanceof XRefreshView) {
            StickyListHeadersListView view = ((StickyListHeadersListView) listView);
            XRefreshView refreshView = ((XRefreshView) xRefreshView);
            presenter = new XROnRefreshTime(activity, view, refreshView);
            presenter.setXRefreshViewListener(this);
            presenter.initXRefreshView(new WeFriendAdapter(entity, activity));
            handler = new CallLogHandler(presenter);
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
