package com.evmtv.cloudvideo.common.view.tool;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.presenter.main.MainPageViewAdapter;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentTransactionUtils {


    private List<NavigationBean.MainNavigationBean.NavigationContentBean> bean;
    private Activity activity;
    private @IdRes
    int containerViewId;


    public FragmentTransactionUtils(Activity activity
            , @IdRes int containerViewId) {
        this.activity = activity;
        this.containerViewId = containerViewId;
    }


    public TransactionMainUtils MainTool(List<NavigationBean.MainNavigationBean.NavigationContentBean> bean, FragmentManager fm) {
        return new TransactionMainUtils(bean, fm);
    }

    public TransactionWeChatUtils WeChatTool(List<WeChatBean.WeChatNavigationBean.NavigationContentBean> bean, FragmentManager fm) {
        return new TransactionWeChatUtils(bean, fm);
    }

    public TransactionOneUtils OneTool(String fragmentId, FragmentManager fm) {
        return new TransactionOneUtils(fragmentId, fm);
    }

    public class TransactionOneUtils {
        private FragmentManager fm;
        private String FragmentId;
        private Fragment fragment;
        private BaseFragment baseFragment;

        public BaseFragment getBaseFragment() {
            return baseFragment;
        }

        public TransactionOneUtils(String fragmentId, FragmentManager fm) {
            FragmentId = fragmentId;
            this.fm = fm;
        }

        public void show() {
            fragment = ResConversionUtil.getFragmentId(activity, FragmentId);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(containerViewId, fragment);
            ft.commit();

            FragmentTransaction ft1 = fm.beginTransaction();
            ft1.show(fragment);
            ft1.commit();

            if (fragment instanceof BaseFragment)
                this.baseFragment = (BaseFragment) fragment;
        }
    }

    public class TransactionMainUtils {
        private List<NavigationBean.MainNavigationBean.NavigationContentBean> bean;
        private Map<Integer, Fragment> fragmentMap = new HashMap<>();
        private FragmentManager fm;
        private BaseFragment baseFragment;


        public BaseFragment getBaseFragment() {
            return baseFragment;
        }

        public TransactionMainUtils(List<NavigationBean.MainNavigationBean.NavigationContentBean> bean, FragmentManager fm) {
            this.bean = bean;
            this.fm = fm;
        }

        public Fragment getItem(int position) {
            if (fragmentMap.containsKey(position)) {
                return fragmentMap.get(position);
            }
            Fragment fragment = ResConversionUtil.getFragmentId(activity, bean.get(position).getChildFragment());
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainPageViewAdapter.FRAGMENT_INPUT_TYPE_VALUE, bean.get(position));
            fragment.setArguments(bundle);
            fragmentMap.put(position, fragment);
            return fragment;
        }

        public void hide(int position) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(getItem(position));
            ft.commit();
        }

        public void hideAll(FragmentTransaction ft) {
            for (Map.Entry<Integer, Fragment> entry : fragmentMap.entrySet()) {
                ft.hide(entry.getValue());
            }
        }

        public void show(int position) {
            Fragment fragment = getItem(position);
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment.isAdded()) {
                hideAll(ft);
                ft.show(fragment);
            } else {
//            ft.add(R.id.MainViewPagerViewID, fragment);
                ft.add(containerViewId, fragment);
            }
            if (fragment instanceof BaseFragment)
                this.baseFragment = (BaseFragment) fragment;
            ft.commit();
        }
    }

    public class TransactionWeChatUtils {
        private List<WeChatBean.WeChatNavigationBean.NavigationContentBean> bean;
        private FragmentManager fragmentManager;
        private Map<Integer, Fragment> fragmentMap = new HashMap<>();
        private BaseFragment baseFragment;


        public BaseFragment getBaseFragment() {
            return baseFragment;
        }

        public TransactionWeChatUtils(List<WeChatBean.WeChatNavigationBean.NavigationContentBean> bean, FragmentManager fragmentManager) {
            this.bean = bean;
            this.fragmentManager = fragmentManager;
        }

        public Fragment getItem(int position) {
            if (fragmentMap.containsKey(position)) {
                return fragmentMap.get(position);
            }
            Fragment fragment = ResConversionUtil.getFragmentId(activity, bean.get(position).getChildFragment());
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainPageViewAdapter.FRAGMENT_INPUT_TYPE_VALUE, bean.get(position));
            fragment.setArguments(bundle);
            fragmentMap.put(position, fragment);
            return fragment;
        }

        public void hide(int position) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.hide(getItem(position));
            ft.commit();
        }

        public void hideAll(FragmentTransaction ft) {
            for (Map.Entry<Integer, Fragment> entry : fragmentMap.entrySet()) {
                ft.hide(entry.getValue());
            }
        }

        public void show(int position) {
            Fragment fragment = getItem(position);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            hideAll(ft);
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
//            ft.add(R.id.MainViewPagerViewID, fragment);
                ft.add(containerViewId, fragment);
                ft.show(fragment);
            }
            if (fragment instanceof BaseFragment)
                this.baseFragment = (BaseFragment) fragment;
            ft.commit();
        }

    }
}
