package com.evmtv.cloudvideo.common.presenter.wechat.friends;

import android.app.Activity;

public interface WeFriendPresenter {

    void InitDisplay(Activity activity, Object listView, Object xRefreshView);

    void ExtractData(int NoType);
}
