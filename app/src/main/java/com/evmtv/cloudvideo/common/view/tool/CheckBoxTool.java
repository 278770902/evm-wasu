package com.evmtv.cloudvideo.common.view.tool;

import android.widget.CheckBox;
import android.widget.CompoundButton;

public class CheckBoxTool {

    public static void resumePlayCompoundButton(CheckBox checkBox, Boolean isChecked, CompoundButton.OnCheckedChangeListener changeListener) {
        checkBox.setOnCheckedChangeListener(null);
//        checkBox.setChecked();
        checkBox.setChecked(isChecked);
        checkBox.setOnCheckedChangeListener(changeListener);
    }
}
