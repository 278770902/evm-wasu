package com.evmtv.cloudvideo.common.presenter.setting.edit;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

public interface EditPersonalPresenter {
    void updatePicker(ArrayList<ImageItem> items , EditPersonalPresenterImpl.OnEditPersonalUpdateSuccessListener listener);
}
