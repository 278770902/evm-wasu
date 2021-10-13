package com.evmtv.cloudvideo.common.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.SecondLevelNavigationEntity;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;


public class MainTabRecyclerAdapter extends RecyclerView.Adapter<MainTabRecyclerAdapter.MainTabRecycleHolder> {

    private Activity context;
    private String TAG = getClass().getName();
    private SecondLevelNavigationEntity secondLevelNavigationEntity;

    public MainTabRecyclerAdapter(Activity context, SecondLevelNavigationEntity secondLevelNavigationEntity) {
        this.context = context;
        this.secondLevelNavigationEntity = secondLevelNavigationEntity;
    }

    @NonNull
    @Override
    public MainTabRecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_tab_item_layout, parent, false);
        return new MainTabRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainTabRecycleHolder holder, int position) {
        final SecondLevelNavigationEntity.SecondLevelNavigationBean bean
                = secondLevelNavigationEntity.getSecondLevelNavigation().get(position);
        holder.mainTabItemViewID.setImageResource(ResConversionUtil
                .getDrawableOrMipmapId(bean.getIcon()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(MainApplication.initContext.getAppPackageName(), bean.getChildActivity());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (secondLevelNavigationEntity == null
                || secondLevelNavigationEntity.getSecondLevelNavigation() == null)
                ? 0 : secondLevelNavigationEntity.getSecondLevelNavigation().size();
    }

    class MainTabRecycleHolder extends RecyclerView.ViewHolder {
        private ImageView mainTabItemViewID;

        public MainTabRecycleHolder(@NonNull View itemView) {
            super(itemView);
            mainTabItemViewID = itemView.findViewById(R.id.mainTabItemViewID);
        }
    }
}
