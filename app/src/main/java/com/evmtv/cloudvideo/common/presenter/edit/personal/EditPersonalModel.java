package com.evmtv.cloudvideo.common.presenter.edit.personal;

import android.widget.Button;
import android.widget.TextView;

public interface EditPersonalModel {
    void InitDisplay();

    void showClassId();

    void setClassTextView(TextView text);

    void setClassId(String classId);

    String getClassId();

    void updateInfo(String password, String name, String sex
            , String age
            , String classid
            , String id);

}
