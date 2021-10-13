package com.evmtv.cloudvideo.common.persenter.forget.pwd;


import android.app.Activity;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.http.edums.SendMsgEntity;
import com.evmtv.cloudvideo.common.presenter.forget.pwd.BaseForgetPassWorldPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.forget.pwd.ForgetPassWorldPresenter;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;

public class ForgetPassWorldPresenterImpl extends BaseForgetPassWorldPresenterImpl {
    public ForgetPassWorldPresenterImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void senMsgCode(String phone, Button butGetReturnViewID) {
        SendSMSUtils.getInstance().senMsgCode(phone, butGetReturnViewID, true);
    }

    @Override
    public void sendForgetPassWorldHttp(String tel, String code, String pwd1, String pwd2) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                String json = EdumsInteractive.getInstance().updatePwdByPhone(tel, pwd1, pwd2, code, SendSMSUtils.getInstance().getSessionId(),tel,ssoToken);
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
                            ToastUtil.setToast("修改成功");
                            activity.finish();
                            return;
                        }

                        ToastUtil.setToast("修改失败:" + finalSendMsgEntity.getMsg());
                    }
                });
            }
        });
    }
}
