package com.evmtv.cloudvideo.common.view.fragment.wechat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogHandler;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogPresenter;
import com.evmtv.cloudvideo.common.presenter.wechat.call.log.CallLogPresenterImpl;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallLogFragment extends BaseWeChatTabFragment {

    private StickyListHeadersListView callLogListViewID;
    private XRefreshView callLogXRefreshView;

    public CallLogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_log, container, false);
    }

    @Override
    protected void initView() {
        CallLogPresenter callLogPresenter = new CallLogPresenterImpl();
        callLogPresenter.InitDisplay(getActivity(), callLogListViewID, callLogXRefreshView);
        callLogPresenter.ExtractData(CallLogHandler.FIRST_NO);
    }
}
