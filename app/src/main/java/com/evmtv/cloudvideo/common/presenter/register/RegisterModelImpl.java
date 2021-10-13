package com.evmtv.cloudvideo.common.presenter.register;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.cpns.CpnsInteractive;
import com.evmtv.cloudvideo.common.model.http.cpns.StateIntEntity;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.csInteractive.cpns.CPNSInteractive;
import com.evmtv.rtc.csInteractive.cpns.entity.RegisterUserResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterModelImpl implements RegisterModel {

    private Activity activity;

    public RegisterModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void InitDisplay() {
    }


    @Override
    public String getSessionId() {
        return SendSMSUtils.getInstance().getSessionId();
    }

    @Override
    public void senMsg(String phone, Button butGetReturnViewID) {
        SendSMSUtils.getInstance().senMsgCode(phone, butGetReturnViewID, false);
    }

    @Override
    public void register(String userName
            , String TEL, String password
            , String uuid, String identifyCode
            , String regionId) {

        AppExecutors.getInstance().networkIOToMain(activity,new OrderMethodInter() {
            StateIntEntity sendMsgEntity;
            RegisterUserResult result;

            @Override
            public void IO() {
                result = CPNSInteractive.getInstance().registerUser(userName, TEL, password, uuid, identifyCode, regionId);
            }

            @Override
            public void Main() {
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (result.getResult() == BaseResult.SUCCESS) {
                            ToastUtil.setToast("注册成功");
                            activity.finish();
                        } else {
                            ToastUtil.setToast("注册失败");
                        }
                    }
                });
            }
        });
    }
}