package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.http.ums.BindedUserInfoEntity;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class UserSTBTabTool {
    private Context mContext;
    private int CurrentIndex = 0;
    private String CurrentStbGUID;
    private static UserSTBTabTool instance;
    private String TAG = "kanJiaBao";

    public static UserSTBTabTool getInstance(Context mContext) {
        synchronized (UserSTBTabTool.class) {
            if (instance == null)
                instance = new UserSTBTabTool(mContext);
        }
        return instance;
    }

    public UserSTBTabTool(Context mContext) {
        this.mContext = mContext;
    }

    public int getCurrentIndex() {
        return CurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        CurrentIndex = currentIndex;
    }

    public String getCurrentStbGUID() {
        return CurrentStbGUID;
    }

    public void setCurrentStbGUID(String currentStbGUID) {
        CurrentStbGUID = currentStbGUID;
    }

    public Boolean IsPass(BindedUserInfoEntity entity) {
        if (entity.getIsSuccess() == -1) {
            XLog.i(TAG, "getBindUserInfo fail");
            return false;
        }

        if (entity.getIsSuccess() == 0) {
            XLog.i(TAG, "has no bind account");
            return false;
        }

        if (entity.getBindUserArray() == null || entity.getBindUserArray().size() <= 0)
            return false;

        return true;
    }

    public BindedUserInfoEntity parseObject(String json) {
        try {
            return JSON.parseObject(json, BindedUserInfoEntity.class);
        } catch (Exception e) {
            return new BindedUserInfoEntity(-1);
        }
    }

    /**
     * @return
     */
    public View makeTabView(BindedUserInfoEntity.BindUserArrayBean entity, int index) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.item_kanjiabao_user_stb, null);
        UserSTBViewHolder viewHolder = new UserSTBViewHolder(tabView);
        viewHolder.title.setText(entity.getName());
        tabView.setTag(R.id.KanJiaBaoTabSelectView, viewHolder);
        tabView.setTag(R.id.KanJiaBaoTabSelectIndex, index);
        tabView.setTag(R.id.KanJiaBaoTabSelectStbGuid, entity.getGUID());
        if (CurrentIndex == index) {
            viewHolder.icon.setImageResource(R.drawable.svg_kanjiabao_user_on_stb_icon);
            viewHolder.bg.setBackgroundResource(R.drawable.layer_kanjiabao_user_stb_bg_true);
        } else {
            viewHolder.icon.setImageResource(R.drawable.svg_kanjiabao_user_off_stb_icon);
            viewHolder.bg.setBackgroundResource(R.drawable.layer_kanjiabao_user_stb_bg_false);
        }
        return tabView;
    }

    public void initUserNavigationTabLayout(TabLayout MainBottomNavigationViewID, BindedUserInfoEntity bindedUserInfoEntity,KanJiaBaoPresenter presenter) {
        if (bindedUserInfoEntity == null
                || bindedUserInfoEntity.getBindUserArray() == null
                || bindedUserInfoEntity.getBindUserArray().size() <= 0)
            return;
        for (BindedUserInfoEntity.BindUserArrayBean bean : bindedUserInfoEntity.getBindUserArray()) {
            MainBottomNavigationViewID.addTab(MainBottomNavigationViewID.newTab());
        }
        for (int i = 0; i < bindedUserInfoEntity.getBindUserArray().size(); i++) {
            MainBottomNavigationViewID.getTabAt(i).setCustomView(
                    UserSTBTabTool.getInstance(mContext).makeTabView(bindedUserInfoEntity.getBindUserArray().get(i), i));
        }
        MainBottomNavigationViewID.addOnTabSelectedListener(new OnUserTabSelectedListener(presenter,mContext));
        MainBottomNavigationViewID.getTabAt(UserSTBTabTool.getInstance(mContext).getCurrentIndex()).select();
    }
}
