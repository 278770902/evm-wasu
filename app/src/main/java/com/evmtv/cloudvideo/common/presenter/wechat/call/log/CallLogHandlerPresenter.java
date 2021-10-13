package com.evmtv.cloudvideo.common.presenter.wechat.call.log;


import android.app.Activity;

import com.andview.refreshview.XRefreshView;
import com.evmtv.cloudvideo.common.model.http.csm.CallLogEntity;
import com.evmtv.cloudvideo.common.presenter.wechat.BaseStickyAdapter;

public interface CallLogHandlerPresenter {
    void initXRefreshView(BaseStickyAdapter adapter);

//    void ExtractData(boolean isFirst);

    void ExtractDataAdapter(int type, boolean isSuccess,Object entity);

    void setXRefreshViewListener(XRefreshView.XRefreshViewListener l);
}
