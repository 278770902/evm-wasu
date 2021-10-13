package com.evmtv.cloudvideo.common.presenter.monitor.share;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.http.ums.CameraShareUserEntity;
import com.evmtv.cloudvideo.common.model.http.ums.UserFriendsEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveEntity;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.grouped.list.BaseViewHolder;
import com.evmtv.cloudvideo.common.view.tool.grouped.list.GroupedRecyclerViewAdapter;

import java.util.ArrayList;

public class ShareFriendsPresenterImpl implements ShareFriendsPresenter, GroupedRecyclerViewAdapter.OnHeaderClickListener {
    private Context mContext;

    public ShareFriendsPresenterImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void InitDisplay(RecyclerView shareFriendsRecyclerViewId) {
        String deviceGUID = MonitorLiveEntity.getInstance().getDeviceGUID();
        String UserGUID = SharedPreferencesUtil.getInstance().getUserGuid(false);
        AppExecutors.getInstance().networkIOToMain(mContext, new OrderMethodInter() {
            @Override
            public void IO() {
                dealData(parseCameraShareUserEntity(UMSInteractive.getInstance().getCameraShareUser(UserGUID, deviceGUID))
                        , JSONLocalObject.parseObject(UMSInteractive.getInstance().getUserFriends(), UserFriendsEntity.class));
            }

            @Override
            public void Main() {
                ShareFriendGroupedListAdapter adapter = new ShareFriendGroupedListAdapter(mContext);
                shareFriendsRecyclerViewId.setLayoutManager(new LinearLayoutManager(mContext));
                shareFriendsRecyclerViewId.setAdapter(adapter);
                adapter.setOnHeaderClickListener(ShareFriendsPresenterImpl.this);
            }
        });
    }

    private void dealData(CameraShareUserEntity cameraShareUserEntity, UserFriendsEntity userFriendsEntity) {
        ShareFriendsTool.getInstance().setItems(new ArrayList<>());
        int cameraNum = 0;
        ShareFriendsTool.getInstance().getItems().add(cameraNum, new ShareFriendsEntity());
        ShareFriendsTool.getInstance().getItems().get(cameraNum).setExpand(false);
        ShareFriendsTool.getInstance().getItems().get(cameraNum).setTitle("已分享好友");
        ShareFriendsTool.getInstance().getItems().get(cameraNum).setChildren(new ArrayList<>());
        if (cameraShareUserEntity.getUsers() != null) {
            for (CameraShareUserEntity.UsersBean entity : cameraShareUserEntity.getUsers()) {
                ShareChildEntity childEntity = new ShareChildEntity();
                childEntity.setGUID(entity.getGUID());
                childEntity.setName(entity.getName());
                childEntity.setShare(true);
                childEntity.setTel(entity.getTEL());
                ShareFriendsTool.getInstance().getItems().get(cameraNum).getChildren().add(childEntity);
            }
        }

        int userFriendNum = 1;
        ShareFriendsTool.getInstance().getItems().add(userFriendNum, new ShareFriendsEntity());
        ShareFriendsTool.getInstance().getItems().get(userFriendNum).setExpand(false);
        ShareFriendsTool.getInstance().getItems().get(userFriendNum).setTitle("未分享好友");
        ShareFriendsTool.getInstance().getItems().get(userFriendNum).setChildren(new ArrayList<>());
        if (userFriendsEntity.getUsers() != null) {
            for (UserFriendsEntity.UsersBean bean : userFriendsEntity.getUsers()) {
                ShareChildEntity childEntity = new ShareChildEntity();
                childEntity.setTel(bean.getTEL());
                childEntity.setShare(false);
                childEntity.setName(bean.getName());
                childEntity.setGUID(bean.getGUID());
                ShareFriendsTool.getInstance().getItems().get(userFriendNum).getChildren().add(childEntity);
            }
        }
    }

    public CameraShareUserEntity parseCameraShareUserEntity(String json) {
        try {
            return JSON.parseObject(json, CameraShareUserEntity.class);
        } catch (Exception e) {
            return new CameraShareUserEntity(-1);
        }
    }

    @Override
    public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition) {
        ShareFriendGroupedListAdapter expandableAdapter = (ShareFriendGroupedListAdapter) adapter;
        if (expandableAdapter.isExpand(groupPosition)) {
            expandableAdapter.collapseGroup(groupPosition);
            holder.setImageResource(R.id.shareMainIconViewId, R.drawable.svg_kanjiabao_arrow_right);
        } else {
            holder.setImageResource(R.id.shareMainIconViewId, R.drawable.svg_kanjiabao_arrow_down);
            expandableAdapter.expandGroup(groupPosition);
        }
    }
}
