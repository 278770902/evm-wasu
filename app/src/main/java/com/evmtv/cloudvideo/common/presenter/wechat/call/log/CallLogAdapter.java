package com.evmtv.cloudvideo.common.presenter.wechat.call.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.csm.CallLogEntity;
import com.evmtv.cloudvideo.common.presenter.wechat.BaseStickyAdapter;
import com.evmtv.cloudvideo.common.utils.DateUtils;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.GlideCircleTransform;

public class CallLogAdapter extends BaseStickyAdapter {


    private CallLogEntity entity;
    private Activity activity;

    public CallLogAdapter(CallLogEntity entity, Activity activity) {
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
        return entity == null ? 0 : entity.getList().size();
    }

    @Override
    public CallLogEntity.ListBean getItem(int position) {
        return entity.getList().get(position);
    }

    public void setEntity(Object entity) {
        if (entity instanceof CallLogEntity) {
            this.entity = (CallLogEntity) entity;
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CallLogEntity.ListBean bean = getItem(position);
        convertView = initView(convertView, parent);
        CallLogAdapter.ViewHolder holder = getHolder(convertView);
        if (holder == null)
            return convertView;
        holder.callLogTimeViewID.setText(DateUtils.DateLongToString(DateUtils.DateStringToLong(bean.getStartTime(), "yyyy/MM/dd HH:mm:ss")
                , "yyyy-MM-dd HH:mm:ss"));
        Glide.with(activity).load(bean.getImgUrl()).placeholder(R.drawable.my_setting_default_header)
                .transform(new GlideCircleTransform(activity)).into(holder.callLogUserImageViewID);
        setCallLogImgTypeViewID(holder.callLogImgTypeViewID, isTV(bean.getHostId(), bean.getHostTerminalType(), bean.getGuestId(), bean.getGuestTerminalType()));
        setCallImage(bean.getHostId(), holder.callLogItemVideoViewID);
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
        public ImageView callLogItemVideoViewID, callLogUserImageViewID, callLogImgTypeViewID, callLogImgBackViewID;
        public ImageButton callLogUserCheckViewID;
        public TextView callLogNameViewID, callLogDayViewID, callLogTimeViewID;
    }

    public static class HeaderViewHolder {
        public ImageView refreshViewID;
    }


    private View initView(View convertView, ViewGroup parent) {
        CallLogAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new CallLogAdapter.ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.call_log_item_layout, parent, false);
            holder.callLogNameViewID = convertView.findViewById(R.id.callLogNameViewID);
            //holder.day = (TextView) convertView.findViewById(R.id.day);
            holder.callLogTimeViewID = convertView.findViewById(R.id.callLogTimeViewID);
            holder.callLogUserImageViewID = convertView.findViewById(R.id.callLogUserImageViewID);
            holder.callLogItemVideoViewID = convertView.findViewById(R.id.callLogItemVideoViewID);
            holder.callLogImgTypeViewID = convertView.findViewById(R.id.callLogImgTypeViewID);
            holder.callLogImgBackViewID = convertView.findViewById(R.id.callLogImgBackViewID);
            holder.callLogUserCheckViewID = convertView.findViewById(R.id.callLogUserCheckViewID);
            holder.callLogUserCheckViewID.setVisibility(View.VISIBLE);
            convertView.setTag(holder);
        }
        return convertView;
    }

    private CallLogAdapter.ViewHolder getHolder(View view) {
        if (view != null) {
            return (CallLogAdapter.ViewHolder) view.getTag();
        }
        return null;
    }

    private CallLogAdapter.HeaderViewHolder getHeaderHolder(View view) {
        if (view != null) {
            return (CallLogAdapter.HeaderViewHolder) view.getTag();
        }
        return null;
    }


    private View initHeaderView(View convertView) {
        CallLogAdapter.HeaderViewHolder holder;
        if (convertView == null) {
            holder = new CallLogAdapter.HeaderViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.call_log_header_item_layout, null);
            holder.refreshViewID = convertView.findViewById(R.id.refreshViewID);
            convertView.setTag(holder);
        }
        return convertView;
    }


    private boolean isHost(String hostId) {
        return SharedPreferencesUtil.getInstance().getUserGuid(false).equals(hostId);
    }

    private void setCallImage(String hostId, ImageView imageView) {
        if (isHost(hostId)) {
            imageView.setImageResource(R.drawable.svg_call_out);
        } else {
            imageView.setImageResource(R.drawable.svg_call_in_svg);
        }
    }

    /**
     * @param GUID1
     * @param Terminal1 主叫方终端类型 0:机顶盒 20:手机
     * @param GUID2
     * @param Terminal2
     * @return
     */
    private boolean isTV(String GUID1, int Terminal1, String GUID2, int Terminal2) {
        if (isHost(GUID1)) {
            return Terminal2 == 0;
        } else {
            return Terminal1 == 0;
        }
    }

    private void setCallLogImgTypeViewID(ImageView viewID, boolean isTV) {
        if (isTV) {
            viewID.setImageResource(R.drawable.svg_call_log_contact_tv);
        } else {
            viewID.setImageResource(R.drawable.svg_call_log_contact_phone);
        }
    }

    private String getShowGUID(String GUID1, String GUID2) {
        if (isHost(GUID1)) {
            return GUID2;
        }
        return GUID1;
    }

}
