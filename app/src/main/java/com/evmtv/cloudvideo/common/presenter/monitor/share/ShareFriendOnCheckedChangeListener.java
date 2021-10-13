package com.evmtv.cloudvideo.common.presenter.monitor.share;

import android.widget.CompoundButton;

import com.evmtv.cloudvideo.common.model.http.ums.BindUserEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.KanJiaBaoCameraEntity;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;

public class ShareFriendOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
    private String GUID;

    public ShareFriendOnCheckedChangeListener(String GUID) {
        this.GUID = GUID;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        BindUserEntity entity = KanJiaBaoCameraEntity.getInstance().getUserEntity();
        if (entity == null||entity.getClientUserArray()==null)
            return;

        for (BindUserEntity.ClientUserArrayBean bean:entity.getClientUserArray()){
            if (bean.getGUID().equals(GUID)){
                buttonView.setChecked(false);
                ToastUtil.setToast("该用户已开通");
            }
        }
    }
}
