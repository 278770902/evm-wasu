package com.evmtv.cloudvideo.common.presenter.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;

import java.util.List;


public class MainPageViewAdapter extends FragmentPagerAdapter {
    private List<NavigationBean.MainNavigationBean.NavigationContentBean> mainBottomNavigationBean;
    private Activity activity;
    public static String FRAGMENT_INPUT_TYPE_VALUE = "fragment info";



    public MainPageViewAdapter(FragmentManager fm, Activity activity, List<NavigationBean.MainNavigationBean.NavigationContentBean> mainBottomNavigationBean) {
        super(fm);
        this.mainBottomNavigationBean = mainBottomNavigationBean;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ResConversionUtil.getFragmentId(activity, mainBottomNavigationBean.get(position).getChildFragment());
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAGMENT_INPUT_TYPE_VALUE, mainBottomNavigationBean.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mainBottomNavigationBean == null ? 0 : mainBottomNavigationBean.size();
    }
}
