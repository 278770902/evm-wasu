package com.evmtv.cloudvideo.common.presenter.monitor.share;

import android.view.View;
import android.widget.CheckBox;

public class ShareFriendOnClickListener implements View.OnClickListener {
    private int groupPosition, childPosition;

    public ShareFriendOnClickListener(int groupPosition, int childPosition) {
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
    }

    @Override
    public void onClick(View v) {
        String GUID = ShareFriendsTool.getInstance().getItems().get(groupPosition).getChildren().get(childPosition).getGUID();
        ShareFriendsTool.getInstance().getGroupCheckBoxSelect().get(groupPosition).put(GUID, new Object[]{childPosition, ((CheckBox)v).isChecked()});
    }
}
