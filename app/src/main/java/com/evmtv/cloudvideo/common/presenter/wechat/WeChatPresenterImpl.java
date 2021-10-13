package com.evmtv.cloudvideo.common.presenter.wechat;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

import org.greenrobot.eventbus.EventBus;


public class WeChatPresenterImpl implements WeChatPresenter, BottomNavigationView.OnNavigationItemSelectedListener {
    private Activity activity;
    private OnWeChatItemSelectedListener listener;
    private WeChatBean.WeChatNavigationBean weChatNavigationBean;
    private Object MainBottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransactionUtils.TransactionWeChatUtils transactionUtils;


    public WeChatPresenterImpl(WeChatBean.WeChatNavigationBean weChatNavigationBean) {
        this.weChatNavigationBean = weChatNavigationBean;
    }

    @Override
    public void InitDisplay(Activity activity, FragmentManager fragmentManager, Object view, Object PageView, OnWeChatItemSelectedListener listener) {
        this.listener = listener;
        this.activity = activity;
        this.MainBottomNavigationView = view;
        this.fragmentManager=fragmentManager;

        /*设置导航栏*/
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
        setSelectedItemId(weChatNavigationBean.getFirstChoiceItemId());
    }


    /**
     * 不能超过5个 如果需要超过五个需要更换view
     *
     * @param MainBottomNavigationViewID
     */
    private void initBottomNavigationView(BottomNavigationView MainBottomNavigationViewID) {

//     API28   MainBottomNavigationViewID.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        MainBottomNavigationViewID.setItemIconTintList(null);
        if (weChatNavigationBean == null)
            return;
        for (WeChatBean.WeChatNavigationBean.NavigationContentBean bean : weChatNavigationBean.getNavigationContent()) {
            MainBottomNavigationViewID.getMenu().add(bean.getGroupId()
                    , bean.getItemId()
                    , bean.getOrder()
                    , bean.getTitle())
//                    .setIcon(ResConversionUtil.getDrawableOrMipmapId(bean.getIcon()))
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MainBottomNavigationViewID.setOnNavigationItemSelectedListener(this);
    }

    private void initBottomNavigationView(TabLayout MainBottomNavigationViewID) {
        if (weChatNavigationBean == null)
            return;
        for (WeChatBean.WeChatNavigationBean.NavigationContentBean bean : weChatNavigationBean.getNavigationContent()) {
            TabLayout.Tab tab = MainBottomNavigationViewID.newTab();
            tab.setText(bean.getTitle());
            tab.setTag(bean);
//            tab.setIcon(ResConversionUtil.getDrawableOrMipmapId(bean.getIcon()));
            MainBottomNavigationViewID.addTab(tab);
        }
        MainBottomNavigationViewID.addOnTabSelectedListener(new OnWeChatTabSelectedListener(listener, transactionUtils));
    }


    private void initPageView(Object PageView) {
        if (PageView instanceof ViewPager) {
            ViewPager view = (ViewPager) PageView;
            if (weChatNavigationBean.getNavigationContent() != null
                    && weChatNavigationBean.getNavigationContent().size() > 0) {
                WeChatPageViewAdapter mainPageViewAdapter = new WeChatPageViewAdapter(fragmentManager
                        , activity, weChatNavigationBean.getNavigationContent());
                view.setAdapter(mainPageViewAdapter);
            }
        }

        if (PageView instanceof FrameLayout) {
            transactionUtils.show(0);
        }
    }

    @Override
    public FragmentTransactionUtils.TransactionWeChatUtils getFragmentTransactionUtils() {
        return transactionUtils;
    }

    private FragmentTransactionUtils.TransactionWeChatUtils initFragmentTransactionUtils() {
        return transactionUtils=new  FragmentTransactionUtils(activity,R.id.weChatFrameViewID)
                .WeChatTool(weChatNavigationBean.getNavigationContent(),fragmentManager);
    }


    public void setSelectedItemId(int ItemId) {
        int order = 0;
        /*设置底部导航栏*/
        if (MainBottomNavigationView instanceof BottomNavigationView) {
            BottomNavigationView MainBottomNavigationViewID = (BottomNavigationView) MainBottomNavigationView;
            MainBottomNavigationViewID.setSelectedItemId(ItemId);
            order = MainBottomNavigationViewID.getMenu().getItem(ItemId).getOrder();
        }
        EventBus.getDefault().post(new SendMessageEntity<>()
                .setMsgId(SendMessageEntity.MsgId.SEND_MAIN_FRAGMENT_ENTITY)
                .setData(weChatNavigationBean.getNavigationContent().get(order)));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (listener != null) {
            listener.onNavigationItemSelected(menuItem.getOrder(), weChatNavigationBean.getNavigationContent().get(menuItem.getOrder()));
            return true;
        }
        return false;
    }
}
