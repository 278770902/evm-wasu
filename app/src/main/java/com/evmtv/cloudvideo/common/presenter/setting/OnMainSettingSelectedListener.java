package com.evmtv.cloudvideo.common.presenter.setting;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.evmtv.cloudvideo.common.model.local.MySettingInfoEntity;

public class OnMainSettingSelectedListener implements TabLayout.OnTabSelectedListener {

    private OnSettingItemSelectedListener listener;
    private String TAG = getClass().getName();

    public OnMainSettingSelectedListener(OnSettingItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        fun(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        fun(tab);
    }


    private void fun(TabLayout.Tab tab) {
        Object item = tab.getTag();
        if (item instanceof MySettingInfoEntity.LevelOneBean) {
            MySettingInfoEntity.LevelOneBean bean = (MySettingInfoEntity.LevelOneBean) item;
            listener.onNavigationItemSelected(bean);
        }
    }
}
