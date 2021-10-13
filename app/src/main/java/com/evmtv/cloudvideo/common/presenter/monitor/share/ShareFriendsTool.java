package com.evmtv.cloudvideo.common.presenter.monitor.share;

import android.content.Context;

import com.evmtv.cloudvideo.common.presenter.monitor.UserSTBTabTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareFriendsTool {
    private List<ShareFriendsEntity> items;
    private static ShareFriendsTool instance;
    private Map<Integer, Map<String, Object[]>> GroupCheckBoxSelect;

    public static ShareFriendsTool getInstance() {
        synchronized (ShareFriendsTool.class) {
            if (instance == null)
                instance = new ShareFriendsTool();
        }
        return instance;
    }

    public List<ShareFriendsEntity> getItems() {
        return items;
    }

    public void setItems(List<ShareFriendsEntity> items) {
        this.items = items;
    }

    public Map<Integer, Map<String, Object[]>> getGroupCheckBoxSelect() {
        return GroupCheckBoxSelect == null ? new HashMap<>() : GroupCheckBoxSelect;
    }

    public void initGroupPositionCheckBox(int groupPosition, int childPosition) {
        if (GroupCheckBoxSelect == null)
            GroupCheckBoxSelect = new HashMap<>();
        if (!GroupCheckBoxSelect.containsKey(groupPosition))
            GroupCheckBoxSelect.put(groupPosition, new HashMap<>());
        if (!GroupCheckBoxSelect.get(groupPosition).containsKey(childPosition))
            GroupCheckBoxSelect.get(groupPosition).put(items.get(groupPosition).getChildren().get(childPosition).getGUID(), new Object[]{childPosition, false});
    }

    public void setGroupCheckBoxSelect(Map<Integer, Map<String, Object[]>> groupCheckBoxSelect) {
        GroupCheckBoxSelect = groupCheckBoxSelect;
    }
}
