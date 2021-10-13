package com.evmtv.cloudvideo.common.view.fragment.wechat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.XRefreshView;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogHandler;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogPresenter;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.wechat.friends.WeFriendPresenter;
import com.evmtv.cloudvideo.common.presenter.wechat.friends.WeFriendPresenterImpl;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFriendsFragment extends BaseWeChatTabFragment {

    private StickyListHeadersListView weFriendListViewID;
    private XRefreshView weFriendXRefreshView ;

    public MyFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friends, container, false);
    }

    @Override
    protected void initView() {
        WeFriendPresenter weFriendPresenter = new WeFriendPresenterImpl();
        weFriendPresenter.InitDisplay(getActivity(),  weFriendListViewID,weFriendXRefreshView);
        weFriendPresenter.ExtractData(CallLogHandler.FIRST_NO);
    }
}
