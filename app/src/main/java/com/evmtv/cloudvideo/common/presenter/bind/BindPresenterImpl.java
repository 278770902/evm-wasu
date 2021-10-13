package com.evmtv.cloudvideo.common.presenter.bind;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.adapter.UserRelationAdapter;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.http.ums.BindUserEntity;
import com.evmtv.cloudvideo.common.model.http.ums.BindedUserInfoEntity;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class BindPresenterImpl implements BindPresenter {
    private String TAG = getClass().getName().toString();

    @Override
    public void InitDisplay(RecyclerView view, Activity activity) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                BindedUserInfoEntity entity = JSONLocalObject.parseObject(UMSInteractive.getInstance().getBindUserInfo(), BindedUserInfoEntity.class);
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (entity.getIsSuccess() == 1) {
                            view.setLayoutManager(new LinearLayoutManager(activity));
                            view.setAdapter(new UserRelationAdapter(entity.getBindUserArray(), activity));
                        }
                    }
                });

            }
        });
    }
}
