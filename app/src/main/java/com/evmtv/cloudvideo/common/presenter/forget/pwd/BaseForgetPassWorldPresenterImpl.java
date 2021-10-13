package com.evmtv.cloudvideo.common.presenter.forget.pwd;

import android.app.Activity;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.cpns.CpnsInteractive;
import com.evmtv.cloudvideo.common.model.http.cpns.StateIntEntity;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.SendSMSUtils;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.csInteractive.cpns.CPNSInteractive;
import com.evmtv.rtc.csInteractive.cpns.entity.ResetPasswordResult;

public class BaseForgetPassWorldPresenterImpl implements ForgetPassWorldPresenter {

    protected Activity activity;

    public BaseForgetPassWorldPresenterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void senMsgCode(String phone, Button butGetReturnViewID) {
        SendSMSUtils.getInstance().senMsgCode(phone, butGetReturnViewID, true);
    }

    @Override
    public void sendForgetPassWorldHttp(String tel, String code, String pwd1, String pwd2) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {
            ResetPasswordResult result;

            @Override
            public void IO() {
//                String json = CpnsInteractive.getInstance().resetPassword(pwd2, tel, SendSMSUtils.getInstance().getSessionId(), code);
                result = CPNSInteractive.getInstance().resetPassword(tel, pwd2, SendSMSUtils.getInstance().getSessionId(), code);
            }

            @Override
            public void Main() {
                if (result.getResult() == BaseResult.SUCCESS) {
                    ToastUtil.setToast("修改成功");
                    activity.finish();
                    return;
                }
                ToastUtil.setToast("用户重置失败");
            }
        });

    }
}
