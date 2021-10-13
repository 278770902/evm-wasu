package com.evmtv.cloudvideo.common.presenter.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

import org.greenrobot.eventbus.EventBus;


public abstract class BaseFragment extends Fragment {
    protected View parentView;
    public String title;
    private Boolean isFragmentDisplaying = false;
    public BackTimeRunBle backTimeRunBle=new BackTimeRunBle();;

    protected abstract void initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewBindUtil.bindViews(this, getView());
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            Log.i(getClass().getName(), "该类未使用EventBus");
        }
        initView();
    }

    protected View NoNeedReload(View view) {
        if (parentView == null)
            parentView = view;
        else {
            ViewGroup viewGroup = (ViewGroup) parentView.getParent();
            if (viewGroup != null)
                viewGroup.removeView(parentView);
        }
        return parentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void KeyCodeBack() {
        if (this instanceof BaseAgentWebFragment){
            BaseAgentWebFragment fragment= (BaseAgentWebFragment) this;
            fragment.presenter.onBack();
        }
    }

    public class BackTimeRunBle implements Runnable {
        @Override
        public void run() {
            KeyCodeBack();
        }
    }
}
