package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class OnUserTabSelectedListener implements TabLayout.OnTabSelectedListener {
    private KanJiaBaoPresenter presenter;
    private Context mContext;

    public OnUserTabSelectedListener(KanJiaBaoPresenter presenter, Context mContext) {
        this.presenter = presenter;
        this.mContext = mContext;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UserSTBTabTool.getInstance(null).setCurrentIndex((int) tab.getCustomView().getTag(R.id.KanJiaBaoTabSelectIndex));
        UserSTBTabTool.getInstance(null).setCurrentStbGUID((String) tab.getCustomView().getTag(R.id.KanJiaBaoTabSelectStbGuid));
        initStbUserViewHolderImageResource((UserSTBViewHolder) tab.getCustomView().getTag(R.id.KanJiaBaoTabSelectView), true);
        AppExecutors.getInstance().networkIOToMain(mContext,new OrderMethodInter() {
            @Override
            public void IO() {
                presenter.initMonitorCameraInfo(UserSTBTabTool.getInstance(null).getCurrentStbGUID());
            }

            @Override
            public void Main() {
                TerminalCameraTool.getInstance(null).getCameraAdapter().setEntity(KanJiaBaoCameraEntity.getInstance());
            }
        });
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        initStbUserViewHolderImageResource((UserSTBViewHolder) tab.getCustomView().getTag(R.id.KanJiaBaoTabSelectView), false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


    private void initStbUserViewHolderImageResource(UserSTBViewHolder holder, Boolean isSelect) {
        if (isSelect) {
            holder.icon.setImageResource(R.drawable.svg_kanjiabao_user_on_stb_icon);
            holder.bg.setBackgroundResource(R.drawable.layer_kanjiabao_user_stb_bg_true);
        } else {
            holder.icon.setImageResource(R.drawable.svg_kanjiabao_user_off_stb_icon);
            holder.bg.setBackgroundResource(R.drawable.layer_kanjiabao_user_stb_bg_false);
        }
    }
}
