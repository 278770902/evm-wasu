package com.evmtv.cloudvideo.common.presenter.guide;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.splash.SplashScreenPresenter;
import com.evmtv.cloudvideo.common.presenter.splash.SplashScreenPresenterImpl;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;
import com.evmtv.cloudvideo.common.view.SCGuideActivity;

import java.util.ArrayList;
import java.util.List;

public class GuidePresenterImpl implements GuidePresenter, View.OnTouchListener, View.OnClickListener {
    private List<View> views;
    private Activity mContext;
    private int currentItem;
    private SplashScreenPresenter screenPresenter;

    public GuidePresenterImpl(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void InitDisplay(ViewPager vpGuide) {
        List<NavigationBean.GuideSplashBean> item = ReadLocalJsonFile.getGuideSplashBg();
        screenPresenter = new SplashScreenPresenterImpl();
        views = new ArrayList<>();
        for (int i = 0; i < item.size(); i++) {
            if (i + 1 == item.size()) {
                View v2 = LayoutInflater.from(mContext).inflate(R.layout.item_guide_end_splash_layout, null);
                v2.findViewById(R.id.guideItemEndViewId).setBackgroundResource(ResConversionUtil.getDrawableOrMipmapId(item.get(i).getIcon()));
                v2.findViewById(R.id.guideStartUpViewId).setOnClickListener(this);
                views.add(v2);
                continue;
            }
            View v1 = LayoutInflater.from(mContext).inflate(R.layout.item_guide_splash_layout, null);
            ((ImageView) v1.findViewById(R.id.guideItemViewId)).setImageResource(ResConversionUtil.getDrawableOrMipmapId(item.get(i).getIcon()));
            views.add(v1);
        }
        vpGuide.setAdapter(new GuideViewPagerAdapter(views));
        vpGuide.addOnPageChangeListener(new PageChangeListener());
        vpGuide.setOnTouchListener(this::onTouch);
    }

    float startX;
    float startY;
    float endX;
    float endY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                WindowManager windowManager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                //获取屏幕的宽度
                Point size = new Point();
                windowManager.getDefaultDisplay().getSize(size);
                int width = size.x;
                //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                if (currentItem == (views.size() - 1) && startX - endX > 0 && startX - endX >= (width / 4)) {
                    screenPresenter.StartNext(mContext);
//                    enterStarActivity();
//                            Log.i(LOG, "进入了触摸");
//                            goToMainActivity();
//                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        screenPresenter.StartNext(mContext);
    }


    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {
        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            currentItem = position;
        }

    }
}
