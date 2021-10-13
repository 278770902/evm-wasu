package com.evmtv.cloudvideo.common.presenter.monitor.share;


import android.content.Context;
import android.widget.CheckBox;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.view.tool.grouped.list.BaseViewHolder;
import com.evmtv.cloudvideo.common.view.tool.grouped.list.GroupedRecyclerViewAdapter;

import java.util.Map;

public class ShareFriendGroupedListAdapter extends GroupedRecyclerViewAdapter {

    public ShareFriendGroupedListAdapter(Context context) {
        super(context);
    }


    @Override
    public int getGroupCount() {
        return ShareFriendsTool.getInstance().getItems().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //如果当前组收起，就直接返回0，否则才返回子项数。这是实现列表展开和收起的关键。
        if (!isExpand(groupPosition)) {
            return 0;
        }
        return ShareFriendsTool.getInstance().getItems().get(groupPosition).getChildren().size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return isExpand(groupPosition);
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_share_friend_group_schedule;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.item_share_adapter_footer;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_kanjiabao_share_friend_group_child_schedule;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        holder.setText(R.id.shareMainTextViewId, ShareFriendsTool.getInstance().getItems().get(groupPosition).getTitle());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
        if (groupPosition == 0) {
            holder.setImageResource(R.id.shareAdapterFooterOKViewID, R.mipmap.item_kanjiabao_bt_cancel_share);
        }

        if (groupPosition == 1) {
            holder.setImageResource(R.id.shareAdapterFooterOKViewID, R.mipmap.item_kanjiabao_bt_share);
        }



    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        holder.setText(R.id.shareMainTextViewId, ShareFriendsTool.getInstance().getItems().get(groupPosition).getChildren().get(childPosition).getName());
        holder.setText(R.id.shareMainTelTextViewId, ShareFriendsTool.getInstance().getItems().get(groupPosition).getChildren().get(childPosition).getTel());
        ShareFriendsTool.getInstance().initGroupPositionCheckBox(groupPosition, childPosition);
        CheckBox checkBox = holder.get(R.id.shareMainCheckViewId);
        checkBox.setChecked((Boolean) ShareFriendsTool.getInstance().getGroupCheckBoxSelect().get(groupPosition).get(
                ShareFriendsTool.getInstance().getItems().get(groupPosition).getChildren().get(childPosition).getGUID())[1]);
        checkBox.setOnClickListener(new ShareFriendOnClickListener(groupPosition, childPosition));
        checkBox.setOnCheckedChangeListener(new ShareFriendOnCheckedChangeListener(ShareFriendsTool.getInstance().getItems().get(groupPosition).getChildren().get(childPosition).getGUID()));
    }

    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition) {
        return ShareFriendsTool.getInstance().getItems().get(groupPosition).isExpand();
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     */
    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
        ResetCheckBoxSelect(groupPosition, true);
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void expandGroup(int groupPosition, boolean animate) {
        ShareFriendsTool.getInstance().getItems().get(groupPosition).setExpand(true);
        if (animate) {
            notifyChildrenInserted(groupPosition);
        } else {
            notifyDataChanged();
        }
    }

    private void ResetCheckBoxSelect(int groupPosition, boolean isNotifyChildrenChanged) {
        if (!ShareFriendsTool.getInstance().getGroupCheckBoxSelect().containsKey(groupPosition))
            return;
        for (Map.Entry<String, Object[]> map : ShareFriendsTool.getInstance().getGroupCheckBoxSelect().get(groupPosition).entrySet()) {
//            map.setValue(false);
            map.setValue(new Object[]{map.getValue()[0], false});
        }
        if (isNotifyChildrenChanged)
            notifyChildrenChanged(groupPosition);
        else
            notifyDataSetChanged();
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     */
    public void collapseGroup(int groupPosition) {
        collapseGroup(groupPosition, false);
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void collapseGroup(int groupPosition, boolean animate) {
        ShareFriendsEntity entity = ShareFriendsTool.getInstance().getItems().get(groupPosition);
        entity.setExpand(false);
        if (animate) {
            notifyChildrenRemoved(groupPosition);
        } else {
            notifyDataChanged();
        }
    }

}
