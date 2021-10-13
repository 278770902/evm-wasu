package com.evmtv.cloudvideo.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.ums.BindUserEntity;
import com.evmtv.cloudvideo.common.model.http.ums.BindedUserInfoEntity;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;

import java.util.List;

public class UserRelationAdapter extends RecyclerView.Adapter<UserRelationAdapter.UserRelationRecycleHolder> {

    private List<BindedUserInfoEntity.BindUserArrayBean> bindUserArray;
    private Context mContext;

    public UserRelationAdapter(List<BindedUserInfoEntity.BindUserArrayBean> bindUserArray, Context mContext) {
        this.bindUserArray = bindUserArray;
        this.mContext = mContext;
    }

    @Override
    public UserRelationRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserRelationRecycleHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user_relation, parent, false));
    }

    @Override
    public void onBindViewHolder(UserRelationRecycleHolder holder, int position) {
        holder.headUserNameViewID.setText(bindUserArray.get(position).getName());
        holder.headTypeViewID.setText(bindUserArray.get(position).getIsMainClient() == 1 ? "主账号" : "辅账号");
        Glide.with(mContext)
                .load(bindUserArray.get(position).getImageUrl())
                .placeholder(R.drawable.my_setting_default_header)
                .into(holder.headImageViewID);
//        holder.userRelationBtnViewID.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return bindUserArray == null ? 0 : bindUserArray.size();
    }

    class UserRelationRecycleHolder extends RecyclerView.ViewHolder {
        private ImageButton headImageViewID;
        private TextView headUserNameViewID, headTypeViewID;
        private Button userRelationBtnViewID;

        public UserRelationRecycleHolder(@NonNull View itemView) {
            super(itemView);
            ViewBindUtil.bindViews(this, itemView);
        }
    }
}
