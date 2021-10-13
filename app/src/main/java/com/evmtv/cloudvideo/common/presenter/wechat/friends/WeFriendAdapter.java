package com.evmtv.cloudvideo.common.presenter.wechat.friends;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.ums.UserFriendsEntity;
import com.evmtv.cloudvideo.common.presenter.wechat.BaseStickyAdapter;

public class WeFriendAdapter extends BaseStickyAdapter {


    private UserFriendsEntity entity;
    private Activity activity;

    public WeFriendAdapter(UserFriendsEntity entity, Activity activity) {
        this.entity = entity;
        this.activity = activity;
    }

    /**
     * 看需求使用
     *
     * @param position    The position of the item within the adapter's data set of the item whose
     *                    header view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view is
     *                    non-null and of an appropriate type before using. If it is not possible to
     *                    convert this view to display the correct data, this method can create a new
     *                    view.
     * @param parent      The parent that this view will eventually be attached to.
     * @return
     */
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        return initHeaderView(convertView);
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return entity == null ? 0 : entity.getUsers().size();
    }

    @Override
    public UserFriendsEntity.UsersBean getItem(int position) {
        return entity.getUsers().get(position);
    }

    public void setEntity(Object entity) {
        if (entity instanceof UserFriendsEntity) {
            this.entity = (UserFriendsEntity) entity;
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserFriendsEntity.UsersBean bean = getItem(position);
        convertView = initView(convertView, parent);
        WeFriendAdapter.ViewHolder holder = getHolder(convertView);
        if (holder == null)
            return convertView;
        holder.itemWeFriendNameViewID.setText(bean.getName());

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    public static class ViewHolder {
        public ImageView itemWeFriendIconViewID, imgTypeViewID, itemWeFriendCallViewID;
        public TextView itemWeFriendNameViewID;
    }

    public static class HeaderViewHolder {
        public TextView tvLvItemTagViewID;
    }


    private View initView(View convertView, ViewGroup parent) {
        WeFriendAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new WeFriendAdapter.ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_we_friend_item, parent, false);
            holder.itemWeFriendIconViewID = convertView.findViewById(R.id.itemWeFriendIconViewID);
            holder.itemWeFriendNameViewID = convertView.findViewById(R.id.itemWeFriendNameViewID);
            holder.itemWeFriendCallViewID = convertView.findViewById(R.id.itemWeFriendCallViewID);
            holder.imgTypeViewID = convertView.findViewById(R.id.imgTypeViewID);
            convertView.setTag(holder);
        }
        return convertView;
    }

    private WeFriendAdapter.ViewHolder getHolder(View view) {
        if (view != null) {
            return (WeFriendAdapter.ViewHolder) view.getTag();
        }
        return null;
    }

    private WeFriendAdapter.HeaderViewHolder getHeaderHolder(View view) {
        if (view != null) {
            return (WeFriendAdapter.HeaderViewHolder) view.getTag();
        }
        return null;
    }


    private View initHeaderView(View convertView) {
        WeFriendAdapter.HeaderViewHolder holder;
        if (convertView == null) {
            holder = new WeFriendAdapter.HeaderViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_we_friend_header, null);
            holder.tvLvItemTagViewID = convertView.findViewById(R.id.tvLvItemTagViewID);
            convertView.setTag(holder);
        }
        return convertView;
    }
}
