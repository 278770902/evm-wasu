package com.evmtv.cloudvideo.common.presenter.wechat;

import android.widget.BaseAdapter;
import android.widget.SectionIndexer;

import com.evmtv.cloudvideo.common.model.http.csm.CallLogEntity;
import com.evmtv.cloudvideo.common.view.tool.sticky.StickyListHeadersAdapter;

public abstract class BaseStickyAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {
    public abstract void setEntity(Object entity);
}
