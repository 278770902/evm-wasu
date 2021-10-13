package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.MonitorControlEntity;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.presenter.monitor.UserSTBTabTool;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;

import java.lang.reflect.Field;

public class MonitorLivePresenterImpl implements MonitorLivePresenter, OnNavigationItemSelectedListener {
    private Activity mContext;
    private ViewGroup viewGroup;
    private MonitorLiveControllerView controllerView;

    public MonitorLivePresenterImpl(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void validateCredentials(MonitorLiveControllerView controllerView) {
        this.controllerView = controllerView;
        MonitorLiveTool.getInstance(mContext).startVideoPlay(controllerView, null);
    }

    @Override
    public void initNavigation(TabLayout tabLayout, ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        MonitorControlEntity entity = ReadLocalJsonFile.getMonitorControl();
        if (entity == null || entity.getControl().size() <= 0)
            return;
        for (MonitorControlEntity.ControlBean bean : entity.getControl()) {
            tabLayout.addTab(tabLayout.newTab());
        }

        for (int i = 0; i < entity.getControl().size(); i++) {
            tabLayout.getTabAt(i).setCustomView(
                    MonitorLiveTool.getInstance(mContext).makeTabView(entity.getControl().get(i), i));
        }
        tabLayout.addOnTabSelectedListener(new OnMonitorLiveTabSelectedListener(this::onNavigationItemSelected));
        if (entity.getControl().size() > MonitorLiveTool.getInstance(mContext).getCurrentIndex())
            MonitorLiveTool.getInstance(null).makeTabLayoutView(viewGroup
                    , entity.getControl().get(MonitorLiveTool.getInstance(mContext).getCurrentIndex()), controllerView);
    }

    /**
     * tab播控点击事件
     *
     * @param position
     * @param bean
     */
    @Override
    public void onNavigationItemSelected(int position, Object bean) {
        MonitorLiveTool.getInstance(null).setCurrentIndex(position);
        MonitorLiveTool.getInstance(null).makeTabLayoutView(viewGroup
                , (MonitorControlEntity.ControlBean) bean, controllerView);
        MonitorLiveTool.getInstance(null).makeTabStartActivity((MonitorControlEntity.ControlBean) bean);
    }
}
