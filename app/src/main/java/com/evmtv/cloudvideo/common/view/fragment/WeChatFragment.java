package com.evmtv.cloudvideo.common.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.presenter.wechat.OnWeChatItemSelectedListener;
import com.evmtv.cloudvideo.common.presenter.wechat.WeChatPresenter;
import com.evmtv.cloudvideo.common.presenter.wechat.WeChatPresenterImpl;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseFragment implements View.OnClickListener, OnWeChatItemSelectedListener {


    private ImageView scanViewID, CallViewID, AddFriendViewID;
    private TextView titleViewID;
    private TabLayout MainBottomNavigationViewID;
    private FrameLayout weChatFrameViewID;
    private WeChatPresenter weChatPresenter;
    private String TAG = getClass().getName();

    public WeChatFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wechat, container, false);
    }

    @Override
    protected void initView() {
        WeChatBean bean = JSONLocalObject.parseObject("wechat/WeChat.json", WeChatBean.class);
        scanViewID.setOnClickListener(this);
        titleViewID.setText(bean.getTitle());
        scanViewID.setImageResource(R.drawable.svg_focus_focusing);
        CallViewID.setImageResource(R.drawable.svg_call);
        AddFriendViewID.setImageResource(R.drawable.svg_add_friend);
        weChatPresenter = new WeChatPresenterImpl(bean.getWeChatNavigation());
        weChatPresenter.InitDisplay(getActivity(), getFragmentManager(), MainBottomNavigationViewID, weChatFrameViewID, this::onNavigationItemSelected);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanViewID:
                break;
        }
    }


    @Override
    public void onNavigationItemSelected(int position, WeChatBean.WeChatNavigationBean.NavigationContentBean bean) {
        Log.i(TAG, "onNavigationItemSelected=== n" + JSON.toJSONString(bean));
        if (bean.getType().equals("fragment")) {
            weChatPresenter.getFragmentTransactionUtils().show(position);
            return;
        }
    }
}
