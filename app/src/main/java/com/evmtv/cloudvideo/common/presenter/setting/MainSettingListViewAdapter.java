package com.evmtv.cloudvideo.common.presenter.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.adapter.MainSettingRecyclerAdapter;
import com.evmtv.cloudvideo.common.model.local.MySettingInfoEntity;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;

import java.util.List;

public class MainSettingListViewAdapter extends BaseAdapter {
    private Context activity;
    private MySettingInfoEntity mySettingInfoEntity;
    private OnSettingTwoItemSelectedListener listener;
    private List<MySettingInfoEntity.LevelTwoBean> levelTwoBean;

    public MainSettingListViewAdapter(Context context, MySettingInfoEntity mySettingInfoEntity, OnSettingTwoItemSelectedListener listener) {
        this.activity = context;
        this.listener = listener;
        this.mySettingInfoEntity = mySettingInfoEntity;
        this.levelTwoBean = mySettingInfoEntity.getLevelTwo();
    }

    @Override
    public int getCount() {
        return levelTwoBean == null ? 0 : levelTwoBean.size();
    }

    @Override
    public MySettingInfoEntity.LevelTwoBean getItem(int position) {
        return levelTwoBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_setting_adapter_layout, parent, false);
            holder.settingHeadIconViewID = convertView.findViewById(R.id.settingHeadIconViewID);
            holder.settingNameViewID = convertView.findViewById(R.id.settingNameViewID);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.settingHeadIconViewID.setImageResource(ResConversionUtil.getDrawableOrMipmapId(getItem(position).getIcon()));
        holder.settingNameViewID.setText(getItem(position).getName());
        convertView.setOnClickListener(new OnClickListener(getItem(position), listener));
        return convertView;
    }


    private class ViewHolder {
        private ImageView settingHeadIconViewID;
        private TextView settingNameViewID;
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
