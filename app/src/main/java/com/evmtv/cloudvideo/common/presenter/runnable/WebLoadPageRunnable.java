package com.evmtv.cloudvideo.common.presenter.runnable;

import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.web.EWebViewPresenter;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class WebLoadPageRunnable implements Runnable {
    protected EWebViewPresenter presenter;
    private NavigationBean.MainNavigationBean.NavigationContentBean beanItem;
    private String TAG = getClass().getName();

    public WebLoadPageRunnable(EWebViewPresenter presenter, NavigationBean.MainNavigationBean.NavigationContentBean beanItem) {
        this.presenter = presenter;
        this.beanItem = beanItem;
    }

    @Override
    public void run() {
//        if (beanItem == null || beanItem.getContent() == null)
//            return;
//        final IntentLocalBean webPageEntity = JSONLocalObject.parseObject(beanItem.getContent().getValue(), IntentLocalBean.class);
//        if (!presenter.isNUll() && webPageEntity != null && webPageEntity.getUrl() != null) {
//            AppExecutors.getInstance().mainThread().execute(new Runnable() {
//                @Override
//                public void run() {
//                    presenter.loadPage(webPageEntity.getUrl());
//                }
//            });
//        }
    }
}
