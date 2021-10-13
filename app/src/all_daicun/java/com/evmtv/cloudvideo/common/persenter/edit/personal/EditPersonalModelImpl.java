package com.evmtv.cloudvideo.common.persenter.edit.personal;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.edums.AreaTreeEntity;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.http.edums.SendMsgEntity;
import com.evmtv.cloudvideo.common.presenter.edit.personal.EditPersonalModel;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditPersonalModelImpl implements EditPersonalModel, OnOptionsSelectListener {

    private Activity activity;
    private OptionsPickerView optionPicker;
    private TextView classTextView;
    List<String> options1Items;
    List<List<String>> options2Items;
    List<List<List<String>>> options3Items;
    private Map<String, String> IdClassId = new HashMap<>();
    private String classId = "";
    private int CurrClassIdOptions1 = 0, CurrClassIdOptions2 = 0, CurrClassIdOptions3 = 0;

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
        optionPicker.setSelectOptions(CurrClassIdOptions1, CurrClassIdOptions2, CurrClassIdOptions3);
        optionPicker.show();
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
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            SendMsgEntity sendMsgEntity;

            @Override
            public void IO() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                String json = EdumsInteractive.getInstance().updateInfo(password, name, sex, age, classid, id, tel, ssoToken);
                try {
                    sendMsgEntity = JSON.parseObject(json, SendMsgEntity.class);
                } catch (Exception e) {
                    sendMsgEntity = new SendMsgEntity(-1, Text.JsonParseErrorText);
                }
            }

            @Override
            public void Main() {
                if (sendMsgEntity.getCode() == 200) {
                    ToastUtil.setToast("修改成功");
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_NAME, name);
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_SEX, sex);
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_AGE, " " + age);
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_CLASS, classid);
                    activity.finish();
                } else {
                    ToastUtil.setToast("修改失败:" + sendMsgEntity.getMsg());
                }
            }
        });
    }

    private void OpenPicker() {
        optionPicker = new OptionsPickerBuilder(activity, this::onOptionsSelect)
                .setSubmitText("确定")
                .setTitleText("选择班级")
                .setCancelText("取消")
                .setSelectOptions(0)
                .build();
    }


    private void initClassInfo() {
        OpenPicker();
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            @Override
            public void IO() {
                String json = EdumsInteractive.getInstance().getAreaTree();
                String newJson = json.intern().substring(1, json.length() - 1);
                Log.i("litao", "newJson=" + newJson);
                AreaTreeEntity areaTreeEntity = null;
                try {
                    areaTreeEntity = JSON.parseObject(newJson, AreaTreeEntity.class);
                } catch (Exception e) {
                    areaTreeEntity = new AreaTreeEntity();
                }
                if (areaTreeEntity.getChildren() == null)
                    return;

                options1Items = new ArrayList<>();
                for (AreaTreeEntity.ChildrenBeanXX childrenBeanX : areaTreeEntity.getChildren()) {
                    if ("公共".equals(childrenBeanX.getServerAddress()))
                        continue;
                    options1Items.add(childrenBeanX.getText());
                }

                options2Items = new ArrayList<>();
                for (AreaTreeEntity.ChildrenBeanXX childrenBeanX : areaTreeEntity.getChildren()) {
                    if ("公共".equals(childrenBeanX.getServerAddress()))
                        continue;
                    List<String> list1 = new ArrayList<>();
                    for (AreaTreeEntity.ChildrenBeanXX.ChildrenBeanX childrenBean : childrenBeanX.getChildren()) {
                        list1.add(childrenBean.getText());
                    }
                    options2Items.add(list1);
                }

                options3Items = new ArrayList<>();
                int A = 0;
                for (AreaTreeEntity.ChildrenBeanXX childrenBeanX : areaTreeEntity.getChildren()) {
                    if ("公共".equals(childrenBeanX.getServerAddress()))
                        continue;
                    List<List<String>> list1 = new ArrayList<>();
                    int B = 0;
                    for (AreaTreeEntity.ChildrenBeanXX.ChildrenBeanX childrenBean : childrenBeanX.getChildren()) {
                        List<String> list2 = new ArrayList<>();
                        int C = 0;
                        if (childrenBean.getChildren() != null && childrenBean.getChildren().size() > 0)
                            for (AreaTreeEntity.ChildrenBeanXX.ChildrenBeanX.ChildrenBean childrenBeanXX : childrenBean.getChildren()) {
                                IdClassId.put(A + "" + B + "" + C, childrenBeanXX.getId());
                                if (childrenBeanXX.getId().equals(SharedPreferencesUtil.getInstance().getUserClass())) {
                                    CurrClassIdOptions1 = A;
                                    CurrClassIdOptions2 = B;
                                    CurrClassIdOptions3 = C;
                                }

                                list2.add(childrenBeanXX.getText());
                                C += 1;
                            }
                        list1.add(list2);
                        B += 1;
                    }
                    options3Items.add(list1);
                    A += 1;
                }
                optionPicker.setPicker(options1Items, options2Items, options3Items);
            }

            @Override
            public void Main() {
                if (classTextView != null)
                    classTextView.setText(options1Items.get(CurrClassIdOptions1)
                            + options2Items.get(CurrClassIdOptions1).get(CurrClassIdOptions2)
                            + options3Items.get(CurrClassIdOptions1).get(CurrClassIdOptions2).get(CurrClassIdOptions3));
            }
        });
    }


    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        if (classTextView != null && options3Items != null && options3Items.size() > options3) {
            String className = options1Items.get(options1)
                    + options2Items.get(options1).get(options2)
                    + options3Items.get(options1).get(options2).get(options3);
            classTextView.setText(className);
            classId = IdClassId.get(options1 + "" + options2 + "" + options3);
            XLog.i("edit", "select classId:" + classId + " " + className);
        }
    }
}