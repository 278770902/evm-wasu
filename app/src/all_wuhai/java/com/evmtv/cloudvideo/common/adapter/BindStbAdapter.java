package com.evmtv.cloudvideo.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;

public class BindStbAdapter extends BaseAdapter {
    private String card;
    private Context mContext;
    private UnBindOnClickListener unBindOnClickListener;

    public BindStbAdapter(String card, Context mContext) {
        this.card = card;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_bind_stb_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.CardTvID = view.findViewById(R.id.CardTvID);
            viewHolder.UnBindBtViewID = view.findViewById(R.id.UnBindBtViewID);
            view.setTag(viewHolder);// 将ViewHolder存储在View中。
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); //重新获取ViewHolder
        }
        viewHolder.CardTvID.setText(card);
        viewHolder.UnBindBtViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unBindOnClickListener != null)
                    unBindOnClickListener.onClick(card);
            }
        });
        return view;
    }

    class ViewHolder {
        TextView CardTvID;
        Button UnBindBtViewID;
    }

    public interface UnBindOnClickListener {
        void onClick(String card);
    }

    public void setUnBindOnClickListener(UnBindOnClickListener unBindOnClickListener) {
        this.unBindOnClickListener = unBindOnClickListener;
    }
}
