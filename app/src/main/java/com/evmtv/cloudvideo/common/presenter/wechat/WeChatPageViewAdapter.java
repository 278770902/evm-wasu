package com.evmtv.cloudvideo.common.presenter.wechat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;

import java.util.List;


public class WeChatPageViewAdapter extends FragmentPagerAdapter {
    private List<WeChatBean.WeChatNavigationBean.NavigationContentBean> weChatBottomNavigationBean;
    private Activity activity;
    public static String FRAGMENT_INPUT_TYPE_VALUE = "fragment info";



    public WeChatPageViewAdapter(FragmentManager fm, Activity activity, List<WeChatBean.WeChatNavigationBean.NavigationContentBean> mainBottomNavigationBean) {
        super(fm);
        this.weChatBottomNavigationBean = mainBottomNavigationBean;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ResConversionUtil.getFragmentId(activity, weChatBottomNavigationBean.get(position).getChildFragment());
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAGMENT_INPUT_TYPE_VALUE, weChatBottomNavigationBean.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return weChatBottomNavigationBean == null ? 0 : weChatBottomNavigationBean.size();
    }
}
