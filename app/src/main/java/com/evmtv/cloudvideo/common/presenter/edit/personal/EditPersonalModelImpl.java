package com.evmtv.cloudvideo.common.presenter.edit.personal;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;

public class EditPersonalModelImpl implements EditPersonalModel, OnOptionsSelectListener {

    private Activity activity;
    private TextView classTextView;
    private String classId = "";


    public EditPersonalModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void InitDisplay() {
        initClassInfo();
    }

    @Override
    public void setClassTextView(TextView text) {
        this.classTextView = text;
    }

    @Override
    public void showClassId() {
    }

    @Override
    public String getClassId() {
        return classId;
    }

    @Override
    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public void updateInfo(String password, String name, String sex, String age, String classid, String id) {

    }


    private void initClassInfo() {

    }
    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {

    }
}