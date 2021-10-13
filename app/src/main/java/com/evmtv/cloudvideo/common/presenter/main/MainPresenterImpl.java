package com.evmtv.cloudvideo.common.presenter.main;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

import org.greenrobot.eventbus.EventBus;


public class MainPresenterImpl implements MainPresenter, BottomNavigationView.OnNavigationItemSelectedListener {
    private AppCompatActivity activity;
    private OnNavigationItemSelectedListener listener;
    private NavigationBean.MainNavigationBean mainBottomNavigationEntity;
    private Object MainBottomNavigationView;
    private FragmentTransactionUtils.TransactionMainUtils transactionUtils;

    @Override
    public void InitDisplay(AppCompatActivity activity, Object view, Object PageView, OnNavigationItemSelectedListener listener) {
        this.listener = listener;
        this.activity = activity;
        this.MainBottomNavigationView = view;


        mainBottomNavigationEntity = getBottomNavigation();
        /*设置底部导航栏*/
        if (view instanceof BottomNavigationView) {
            initBottomNavigationView((BottomNavigationView) view);
        }

        if (view instanceof TabLayout) {
            initBottomNavigationView((TabLayout) view);
        }
        initFragmentTransactionUtils();

        /*设置显示的Fragment*/
        initPageView(PageView);

        /*设置首选项*/
        setSelectedItemId(mainBottomNavigationEntity.getFirstChoiceItemId());
    }


    /**
     * 不能超过5个 如果需要超过五个需要更换view
     *
     * @param MainBottomNavigationViewID
     */
    private void initBottomNavigationView(BottomNavigationView MainBottomNavigationViewID) {

//     API28   MainBottomNavigationViewID.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        MainBottomNavigationViewID.setItemIconTintList(null);

        if (mainBottomNavigationEntity == null)
            return;
        for (NavigationBean.MainNavigationBean.NavigationContentBean bean : mainBottomNavigationEntity.getNavigationContent()) {
            MainBottomNavigationViewID.getMenu().add(bean.getGroupId()
                    , bean.getItemId()
                    , bean.getOrder()
                    , bean.getTitle())
                    .setIcon(ResConversionUtil.getDrawableOrMipmapId(bean.getIcon()))
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MainBottomNavigationViewID.setOnNavigationItemSelectedListener(this);
    }

    private void initBottomNavigationView(TabLayout MainBottomNavigationViewID) {
        if (mainBottomNavigationEntity == null)
            return;
        for (NavigationBean.MainNavigationBean.NavigationContentBean bean : mainBottomNavigationEntity.getNavigationContent()) {
            TabLayout.Tab tab = MainBottomNavigationViewID.newTab();
            tab.setText(bean.getTitle());
            tab.setTag(bean);
            tab.setIcon(ResConversionUtil.getDrawableOrMipmapId(bean.getIcon()));
            MainBottomNavigationViewID.addTab(tab);
        }
        MainBottomNavigationViewID.addOnTabSelectedListener(new OnMainTabSelectedListener(listener, transactionUtils));
    }


    private void initPageView(Object PageView) {
        if (PageView instanceof ViewPager) {
            ViewPager view = (ViewPager) PageView;
            if (mainBottomNavigationEntity.getNavigationContent() != null
                    && mainBottomNavigationEntity.getNavigationContent().size() > 0) {
                MainPageViewAdapter mainPageViewAdapter = new MainPageViewAdapter(activity.getSupportFragmentManager()
                        , activity, mainBottomNavigationEntity.getNavigationContent());
                view.setAdapter(mainPageViewAdapter);
            }
        }

        if (PageView instanceof FrameLayout) {
            transactionUtils.show(0);
        }
    }

    @Override
    public FragmentTransactionUtils.TransactionMainUtils getFragmentTransactionUtils() {
        return transactionUtils;
    }



    private FragmentTransactionUtils.TransactionMainUtils initFragmentTransactionUtils() {
//        return transactionUtils = new FragmentTransactionUtils(activity.getSupportFragmentManager()
//                , activity, mainBottomNavigationEntity.getNavigationContent()
//                , R.id.MainViewPagerViewID);
        return transactionUtils=new  FragmentTransactionUtils(activity,R.id.MainViewPagerViewID).MainTool(mainBottomNavigationEntity.getNavigationContent(),activity.getSupportFragmentManager());
    }


    public void setSelectedItemId(int ItemId) {
        int order = 0;
        /*设置底部导航栏*/
        if (MainBottomNavigationView instanceof BottomNavigationView) {
            BottomNavigationView MainBottomNavigationViewID = (BottomNavigationView) MainBottomNavigationView;
            MainBottomNavigationViewID.setSelectedItemId(ItemId);
            order = MainBottomNavigationViewID.getMenu().getItem(ItemId).getOrder();
        }
        EventBus.getDefault().postSticky(new SendMessageEntity<>()
                .setMsgId(SendMessageEntity.MsgId.SEND_MAIN_FRAGMENT_ENTITY)
                .setData(mainBottomNavigationEntity.getNavigationContent().get(order)));
    }


    private NavigationBean.MainNavigationBean getBottomNavigation() {
        return ReadLocalJsonFile.getMainBottomNavigationBean();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (listener != null) {
            listener.onNavigationItemSelected(menuItem.getOrder(), mainBottomNavigationEntity.getNavigationContent().get(menuItem.getOrder()));
            return true;
        }
        return false;
    }
}
