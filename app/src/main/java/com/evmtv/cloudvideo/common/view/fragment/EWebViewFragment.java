package com.evmtv.cloudvideo.common.view.fragment;


import android.os.Bundle;


import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseAgentWebFragment;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;


/**
 * A simple {@link } subclass.
 */
public class EWebViewFragment extends BaseAgentWebFragment {

    private LinearLayout AgentWebViewID;
    private String TAG = getClass().getName();
    private IntentLocalBean webPageEntity;

    public EWebViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return NoNeedReload(inflater.inflate(R.layout.fragment_eweb_view, container, false));
        return NoNeedReload(inflater.inflate(R.layout.fragment_eweb_view, container, false));
    }

    @Override
    protected void initView() {
        webPageEntity = JSONLocalObject.parseObject(beanItem.getContent().getValue(), IntentLocalBean.class);
        title = webPageEntity.getTitle();
        super.initView();
        Log.i(TAG, "main initView");
        presenter.attributeSetting(AgentWebViewID);
        presenter.loadPage(webPageEntity.getUrl());
//        AppExecutors.getInstance().scheduledExecutor().execute(new WebLoadPageRunnable(presenter,beanItem));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (this != null && !hidden && webPageEntity.isStartRefresh()) {
            presenter.loadPage(webPageEntity.getUrl());
        }
    }
}
