package com.evmtv.cloudvideo.common.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveTool;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class FragmentKeyCodeBackTool {
    private Activity mContext;
    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    private static FragmentKeyCodeBackTool instance;
    protected Handler BackTimeHandler = new Handler();


    public static FragmentKeyCodeBackTool getInstance(Activity mContext) {
        synchronized (FragmentKeyCodeBackTool.class) {
            if (instance == null)
                instance = new FragmentKeyCodeBackTool(mContext);
        }
        return instance;
    }

    public FragmentKeyCodeBackTool(Activity mContext) {
        this.mContext = mContext;

    }

    public void KeyCodeBack(BaseFragment.BackTimeRunBle runBle) {
        BackTimeHandler.postDelayed(runBle, 450);
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 400) {
            firstTime = secondTime;
        } else {
            BackTimeHandler.removeCallbacks(runBle);
            Intent intent = new Intent();
            // 为Intent设置Action、Category属性
            intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
            intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
            mContext.startActivity(intent);
        }
    }


}
