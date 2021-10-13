package com.evmtv.cloudvideo.common.presenter.monitor.live.multi;

import android.view.View;
import android.widget.CheckBox;

import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.entity.MorePlayItemBean;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class SelectCameraBindOnListener implements View.OnClickListener {
    private CheckBox morePlayItemListCheckBoxViewId;

    public SelectCameraBindOnListener(CheckBox morePlayItemListCheckBoxViewId) {
        this.morePlayItemListCheckBoxViewId = morePlayItemListCheckBoxViewId;
    }

    @Override
    public void onClick(View v) {
        boolean isChecked = !morePlayItemListCheckBoxViewId.isChecked();
        morePlayItemListCheckBoxViewId.setChecked(isChecked);
        if (isChecked) {
            int num = 0;
            for (MorePlayItemBean entity : MorePlayEntity.getInstance().getItemBeans()) {
                if (entity.getIsSelectIndex() != -1)
                    num += 1;
            }
            XLog.i("tag", "-------num" + num);
            MorePlayEntity.getInstance().getItemBeans().get((int) morePlayItemListCheckBoxViewId.getTag()).setIsSelectIndex(num);
        } else {
            MorePlayEntity.getInstance().getItemBeans().get((int) morePlayItemListCheckBoxViewId.getTag()).setIsSelectIndex(-1);
        }
    }
}
