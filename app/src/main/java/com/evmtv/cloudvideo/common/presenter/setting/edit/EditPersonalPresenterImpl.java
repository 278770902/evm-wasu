package com.evmtv.cloudvideo.common.presenter.setting.edit;

import android.app.Activity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.http.ums.UpdateUserInfoEntity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.GlideCircleTransform;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;

public class EditPersonalPresenterImpl implements EditPersonalPresenter {
    private Activity activity;
    private String TAG = getClass().getName();

    public EditPersonalPresenterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void updatePicker(ArrayList<ImageItem> items, OnEditPersonalUpdateSuccessListener listener) {
        if (items != null && items.size() > 0) {
            AppExecutors.getInstance().networkIO().execute(new Runnable() {
                @Override
                public void run() {
                    File file = new File(items.get(0).path);
                    String json = UMSInteractive.getInstance().updateUserInfo(file);
                    UpdateUserInfoEntity entity = null;
                    try {
                        entity = JSON.parseObject(json, UpdateUserInfoEntity.class);
                    } catch (Exception e) {
                        Log.i(TAG, e.getMessage());
                        entity = new UpdateUserInfoEntity(-1);
                    }

                    if (entity.getIsSuccess() == 1) {
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                listener.UpdateImage(items.get(0).path);
                            }
                        });
                    }
                }
            });
        } else {
            ToastUtil.setToast("没有图片");
        }
    }


    public interface OnEditPersonalUpdateSuccessListener {
        void UpdateImage(String path);
    }
}
