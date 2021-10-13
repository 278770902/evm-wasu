package com.evmtv.cloudvideo.common.presenter.wechat;

import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.model.local.WeChatBean;

public interface OnWeChatItemSelectedListener {
    void onNavigationItemSelected(int position, WeChatBean.WeChatNavigationBean.NavigationContentBean bean);
}
