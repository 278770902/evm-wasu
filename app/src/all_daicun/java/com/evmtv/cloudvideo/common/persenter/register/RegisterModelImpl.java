package com.evmtv.cloudvideo.common.persenter.register;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.evmtv.cloudvideo.common.http.edums.AreaTreeEntity;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.http.edums.SendMsgEntity;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterModelImpl implements RegisterModel, OnOptionsSelectListener {

    private Activity activity;
    private String classId = "";
    List<String> options1Items;
    private TextView classTextView;
    List<List<String>> options2Items;
    private OptionsPickerView optionPicker;
    List<List<List<String>>> options3Items;
    private Map<Integer, String> IdClassId = new HashMap<>();

    public RegisterModelImpl(Activity activity) {
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
        optionPicker.show();
    }

    @Override
    public String getClassId() {
        return classId;
    }

    private void OpenPicker() {
        optionPicker = new OptionsPickerBuilder(activity, this::onOptionsSelect)
                .setSubmitText("确定")
                .setTitleText("选择班级")
                .setCancelText("取消")
                .setSelectOptions(0)
                .build();
    }

    @Override
    public String getSessionid() {
        return SendSMSUtils.getInstance().getSessionId();
    }

    @Override
    public void senMsg(String phone, Button butGetReturnViewID) {
        SendSMSUtils.getInstance().senMsgCode(phone, butGetReturnViewID, false);
    }

    @Override
    public void register(String name, String sex
            , String age, String phone
            , String password, String pwd2
            , String classid, String identifyCode
            , String sessionid) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String json = EdumsInteractive.getInstance().register(name, sex, age, phone, password, pwd2, classid, identifyCode, sessionid);
                SendMsgEntity sendMsgEntity;
                try {
                    sendMsgEntity = JSON.parseObject(json, SendMsgEntity.class);
                } catch (Exception e) {
                    sendMsgEntity = new SendMsgEntity(-1, Text.JsonParseErrorText);
                }

                SendMsgEntity finalSendMsgEntity = sendMsgEntity;
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (finalSendMsgEntity.getCode() == 200) {
                            ToastUtil.setToast("注册成功");
                            activity.finish();
                        } else {
                            ToastUtil.setToast("注册失败:" + finalSendMsgEntity.getMsg());
                        }
                    }
                });

            }
        });
    }

    private void initClassInfo() {
        OpenPicker();
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String json = EdumsInteractive.getInstance().getAreaTree();
                String newJson = json.intern().substring(1, json.length() - 1);
                Log.i("litao", "newJson=" + newJson);
                AreaTreeEntity areaTreeEntity;
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
                for (AreaTreeEntity.ChildrenBeanXX childrenBeanX : areaTreeEntity.getChildren()) {
                    if ("公共".equals(childrenBeanX.getServerAddress()))
                        continue;
                    List<List<String>> list1 = new ArrayList<>();
                    for (AreaTreeEntity.ChildrenBeanXX.ChildrenBeanX childrenBean : childrenBeanX.getChildren()) {
                        List<String> list2 = new ArrayList<>();
                        if (childrenBean.getChildren() != null && childrenBean.getChildren().size() > 0)
                            for (AreaTreeEntity.ChildrenBeanXX.ChildrenBeanX.ChildrenBean childrenBeanXX : childrenBean.getChildren()) {
                                IdClassId.put(list2.size(), childrenBeanXX.getId());
                                list2.add(childrenBeanXX.getText());
                            }
                        list1.add(list2);
                    }
                    options3Items.add(list1);
                }
                optionPicker.setPicker(options1Items, options2Items, options3Items);
            }
        });
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        if (classTextView != null && options3Items != null && options3Items.size() > options3) {
            classTextView.setText(options3Items.get(options1).get(options2).get(options3));
            classId = IdClassId.get(options3);
        }
    }
}