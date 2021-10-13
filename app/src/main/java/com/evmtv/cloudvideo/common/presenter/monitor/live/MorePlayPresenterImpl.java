package com.evmtv.cloudvideo.common.presenter.monitor.live;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.StartPlayEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.KanJiaBaoCameraEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayItemBean;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.MorePlayTool;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.OnHeaderItemClickListener;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.VideoKeepHandler;
import com.evmtv.cloudvideo.common.utils.UnitConversion;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.utils.view.SpacesItemDecoration;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MorePlayPresenterImpl implements MorePlayPresenter, OnHeaderItemClickListener {
    private Context context;
    private MorePlayAdapter morePlayAdapter;
    private VideoKeepHandler handler;
    private RecyclerView morePlayCameraListRecyclerViewId;

    public MorePlayPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void validateCredentials(RecyclerView morePlayCameraListRecyclerViewId) {
        this.morePlayCameraListRecyclerViewId = morePlayCameraListRecyclerViewId;
        morePlayCameraListRecyclerViewId.addItemDecoration(new SpacesItemDecoration(UnitConversion.px2dip(context, 10), 1));
        morePlayAdapter = new MorePlayAdapter(context);
        validateCameraListSelect();
    }


    private void validateCameraListSelect() {
        AppExecutors.getInstance().networkIOToMain(context,new OrderMethodInter() {
            @Override
            public void IO() {
                MorePlayTool.getInstance(context).stopAllVideoPlay();
                MonitorCameraInfoEntity cameraInfoEntity = KanJiaBaoCameraEntity.getInstance().getCameraInfoEntity();
                if (cameraInfoEntity == null || cameraInfoEntity.getData() == null)
                    return;

                //记录是否选择
                List<MorePlayItemBean> itemBeans = MorePlayEntity.getInstance().getItemBeans();
                Map<String, Integer> map = null;
                if (itemBeans != null && itemBeans.size() > 0) {
                    map = new HashMap<>();
                    for (MorePlayItemBean bean : itemBeans) {
                        map.put(bean.getSerialNum(), bean.getIsSelectIndex());
                    }
                }
                MorePlayEntity.getInstance().setItemBeans(new ArrayList<>());
                for (int i = 0; i < cameraInfoEntity.getData().size(); i++) {
                    StartPlayEntity entity = parseStartPlayEntity(
                            CsmInteractive.getInstance().startPlay(cameraInfoEntity.getData().get(i).getSerialNum()
                                    , SharedPreferencesUtil.getInstance().getUserGuid(true)
                                    , KanJiaBaoCameraEntity.getInstance().getSTBGuid()
                                    , true));
                    MorePlayItemBean bean = new MorePlayItemBean();
                    bean.setName(cameraInfoEntity.getData().get(i).getDeviceName());
                    bean.setSessionId(entity.getSessionId());
                    bean.setErrorDeviceBusy(entity.getErrorDeviceBusy());
                    bean.setErrorDeviceOffLine(entity.getErrorDeviceOffLine());
                    bean.setPlayUrl(entity.getPlayUrl());
                    bean.setSerialNum(cameraInfoEntity.getData().get(i).getSerialNum());
                    if (map != null) {
                        bean.setIsSelectIndex(map.get(cameraInfoEntity.getData().get(i).getSerialNum()));
                    }
                    MorePlayEntity.getInstance().getItemBeans().add(i, bean);
                }
            }

            @Override
            public void Main() {
                CameraListSelectInit();
            }
        });
    }

    private void CameraListSelectInit() {
        MorePlayEntity.getInstance().cleanCopyItemBeans();
        morePlayCameraListRecyclerViewId.setNestedScrollingEnabled(false);
        GridLayoutManager manager = new GridLayoutManager(context, 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        morePlayCameraListRecyclerViewId.setLayoutManager(manager);
        MorePlayTool.getInstance(context).setOnHeaderItemClickListener(this::OnHeaderItemOnClick);
        MorePlayEntity.getInstance().setMorePlayCameraList(true);
        morePlayCameraListRecyclerViewId.setAdapter(morePlayAdapter);
    }

    private void CameraPlaySelectInit() {
        MorePlayEntity.getInstance().cleanCopyItemBeans();
        morePlayCameraListRecyclerViewId.setNestedScrollingEnabled(false);
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        MorePlayEntity.getInstance().setMorePlayCameraList(false);
        MorePlayTool.getInstance(context).setOnHeaderItemClickListener(this::OnHeaderItemOnClick);
        morePlayCameraListRecyclerViewId.setLayoutManager(manager);
        morePlayCameraListRecyclerViewId.setAdapter(morePlayAdapter);
    }

    public StartPlayEntity parseStartPlayEntity(String json) {
        if (json != null)
            XLog.i(getClass().getName(), "----json=" + json);
        try {
            return JSON.parseObject(json, StartPlayEntity.class);
        } catch (Exception e) {
            return new StartPlayEntity(false);
        }
    }

    @Override
    public void OnHeaderItemOnClick(int selectNum, boolean isMorePlayCameraList) {
        if (isMorePlayCameraList) {
            if (selectNum > 1)
                CameraPlaySelectInit();
            else
                ToastUtil.setToast("请先选择摄像头");
        } else {
            validateCameraListSelect();
        }
    }
}
