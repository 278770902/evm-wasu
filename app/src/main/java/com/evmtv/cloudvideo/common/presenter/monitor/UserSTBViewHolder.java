package com.evmtv.cloudvideo.common.presenter.monitor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;

public class UserSTBViewHolder {
    ImageView icon;
    View bg;
    TextView title;

    public UserSTBViewHolder(View view) {
        title = view.findViewById(R.id.kanJiaBaoUserNameTextViewId);
        icon = view.findViewById(R.id.kanJiaBaoUserNameImageViewId);
        bg = view.findViewById(R.id.kanJiaBaoUserBgConstraintLayoutId);
    }
}
