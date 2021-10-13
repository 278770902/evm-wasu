package com.evmtv.cloudvideo.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.model.local.MySettingInfoEntity;
import com.evmtv.cloudvideo.common.presenter.setting.OnFootClickListener;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingTwoItemSelectedListener;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;
import com.evmtv.cloudvideo.common.view.AgentWebActivity;
import com.evmtv.cloudvideo.common.view.action.ActionName;

import java.util.List;

public class MainSettingRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context activity;
    private MySettingInfoEntity mySettingInfoEntity;
    private OnSettingTwoItemSelectedListener listener;
    private final int HEADER_TYPE = -2, FOOT_TYPE = -1;
    private int FootLayoutRes = -1, HeaderLayoutRes = -1;
    private List<MySettingInfoEntity.LevelTwoBean> levelTwoBean;

    public MainSettingRecyclerAdapter(Context context, MySettingInfoEntity mySettingInfoEntity, OnSettingTwoItemSelectedListener listener) {
        this.activity = context;
        this.listener = listener;
        this.mySettingInfoEntity = mySettingInfoEntity;
        this.levelTwoBean = mySettingInfoEntity.getLevelTwo();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOT_TYPE)
            return new MainSettingRecyclerFootHolder(LayoutInflater.from(activity).inflate(FootLayoutRes, parent, false));
        if (viewType == HEADER_TYPE)
            return new MainSettingRecyclerFootHolder(LayoutInflater.from(activity).inflate(HeaderLayoutRes, parent, false));
        return new MainSettingRecyclerHolder(LayoutInflater.from(activity).inflate(R.layout.main_setting_adapter_layout, parent, false));
    }


    public void addFoot(@LayoutRes int resource) {
        this.FootLayoutRes = resource;
    }

    public void addHeader(@LayoutRes int resource) {
        this.HeaderLayoutRes = resource;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mySettingInfoEntity == null)
            return;
        if (holder instanceof MainSettingRecyclerHolder) {
            MainSettingRecyclerHolder itemHolder = (MainSettingRecyclerHolder) holder;
            MySettingInfoEntity.LevelTwoBean item = levelTwoBean.get(position);
            itemHolder.settingHeadIconViewID.setImageResource(ResConversionUtil.getDrawableOrMipmapId(item.getIcon()));
            itemHolder.settingNameViewID.setText(item.getName());
            itemHolder.itemView.setOnClickListener(new OnClickListener(item, listener));
        }

        if (holder instanceof MainSettingRecyclerFootHolder) {
            MainSettingRecyclerFootHolder itemHolder = (MainSettingRecyclerFootHolder) holder;
            itemHolder.tvFiveVersion.setText(AppUtils.getInstance(null).getVersionName());
            itemHolder.textUserRule.setOnClickListener(new OnFootClickListener(mySettingInfoEntity.getUserAgreement(),activity));
            itemHolder.textHideRule.setOnClickListener(new OnFootClickListener(mySettingInfoEntity.getPrivacyPolicy(),activity));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && HeaderLayoutRes != -1)
            return HEADER_TYPE;
        if (position + 1 == getItemCount())
            return FOOT_TYPE;
        return position;
    }

    @Override
    public int getItemCount() {
        return updateItemCount(HeaderLayoutRes, updateItemCount(FootLayoutRes, levelTwoBean == null ? 0 : levelTwoBean.size()));
    }

    private int updateItemCount(int LayoutRes, int num) {
        return LayoutRes == -1 ? num : (num + 1);
    }


    class MainSettingRecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView settingHeadIconViewID;
        private TextView settingNameViewID;

        public MainSettingRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            ViewBindUtil.bindViews(this, itemView);
        }
    }

    class MainSettingRecyclerFootHolder extends RecyclerView.ViewHolder {
        private TextView tvFiveVersion, textUserRule, textHideRule;

        public MainSettingRecyclerFootHolder(@NonNull View itemView) {
            super(itemView);
            ViewBindUtil.bindViews(this, itemView);
        }
    }

    class OnClickListener implements View.OnClickListener {
        private MySettingInfoEntity.LevelTwoBean item;
        private OnSettingTwoItemSelectedListener listener;

        public OnClickListener(MySettingInfoEntity.LevelTwoBean item, OnSettingTwoItemSelectedListener listener) {
            this.item = item;
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onNavigationItemSelected(item);
        }
    }

}
