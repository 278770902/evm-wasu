package com.evmtv.cloudvideo.common.presenter.base;

import android.util.Log;

import com.evmtv.cloudvideo.common.presenter.web.EWebViewPresenter;
import com.evmtv.cloudvideo.common.presenter.web.EWebViewPresenterImpl;

public abstract class BaseAgentWebFragment extends BaseMainTabFragment {
    private String TAG = this.getClass().getName();
    protected EWebViewPresenter presenter;

    @Override
    protected void initView() {
        presenter = new  EWebViewPresenterImpl(getActivity(), title);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null)
            presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null)
            presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.onDestroy();
    }
}
