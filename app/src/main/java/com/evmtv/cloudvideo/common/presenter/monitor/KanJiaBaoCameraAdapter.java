package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceStatusEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.CornersTransformPhoto;


public class KanJiaBaoCameraAdapter extends RecyclerView.Adapter<KanJiaBaoCameraViewHolder> {

    private Context context;
    private KanJiaBaoCameraEntity entity;

    public KanJiaBaoCameraAdapter(Context context, KanJiaBaoCameraEntity entity) {
        this.context = context;
        this.entity = entity;
    }

    @Override
    public KanJiaBaoCameraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kanjiabao_camera, parent, false);
        return new KanJiaBaoCameraViewHolder(view);
    }


    public void setEntity(KanJiaBaoCameraEntity entity) {
        this.entity = entity;
        notifyDataSetChanged();
    }

    private MonitorCameraInfoEntity.DataBean getEntity(int position) {
        return entity.getCameraInfoEntity().getData().get(position);
    }

    @Override
    public void onBindViewHolder(KanJiaBaoCameraViewHolder holder, int position) {
        MonitorCameraInfoEntity.DataBean bean = getEntity(position);
        holder.kanJiaBaoCameraNameTextViewId.setText(bean.getDeviceName());
        TerminalCameraTool.getInstance(null).initCameraIsOnline(bean.getSerialNum(), holder.kanJiaBaoMainInfoCameraImageViewId);
        AlarmDeviceStatusEntity.DeviceListBean deviceStateEntity = (AlarmDeviceStatusEntity.DeviceListBean) KanJiaBaoCameraEntity.getInstance().getCameraState().get(bean.getSerialNum());
        TerminalCameraTool.getInstance(null).kanJiaModelSwitchBg(deviceStateEntity.getAlarm() == 1, holder);
        holder.startModeKanJiaBaoItemViewID.setOnClickListener(new SetUpAlarmDeviceOnClickListenerImpl(position, holder));
        holder.itemView.setOnClickListener(new MonitorLiveOnClickListenerImpl(position, context));
        Glide.with(context)
                .load(SharedPreferencesUtil.getInstance().getData(bean.getSerialNum(),""))
                .placeholder(R.drawable.item_kanjiabao_default_bg)
                .dontAnimate()
                .fallback(R.drawable.item_kanjiabao_default_bg)
                .transform(new CornersTransformPhoto(context, 10))
                .into(holder.kanJiaBaoMainInfoImgViewId);

    }


    @Override
    public int getItemCount() {
        return (entity.getCameraInfoEntity() == null || entity.getCameraInfoEntity().getData() == null) ? 0 : entity.getCameraInfoEntity().getData().size();
    }

}
