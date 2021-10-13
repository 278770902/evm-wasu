package com.evmtv.cloudvideo.common.persenter.setting;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.edums.EdumsInteractive;
import com.evmtv.cloudvideo.common.http.edums.FindPhoneIfExistEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.setting.BaseSettingPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.setting.MessageRefreshListener;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingItemSelectedListener;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingTwoItemSelectedListener;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

public class SettingPresenterImpl extends BaseSettingPresenterImpl {

    public SettingPresenterImpl(Context activity, NavigationBean.MainNavigationBean.NavigationContentBean beanItem, OnSettingItemSelectedListener listener, OnSettingTwoItemSelectedListener listenerTwo) {
        super(activity, beanItem, listener, listenerTwo);
    }

    //0：未审核
    //1：审核通过
    //2：审核未通过
    //审核中（未审核状态），不显示或者已审核（审核通过状态），未过审核（审核不通过状态）
    public String getUserState() {
        int state = SharedPreferencesUtil.getInstance().getCKState();
        switch (state) {
            case -100:
                return "";
            case 0:
                return "审核中";
            case 1:
                return "审核通过";
            case 2:
                return "未过审核";
        }
        return "未知状态";
    }

    @Override
    public void UpdatePersonalInfo(MessageRefreshListener messageRefreshListener) {
        AppExecutors.getInstance().networkIOToMain(new OrderMethodInter() {

            @Override
            public void IO() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                String entityJson = EdumsInteractive.getInstance().findPhoneIfExist(tel, tel, ssoToken);
                try {
                    FindPhoneIfExistEntity entity = JSON.parseObject(entityJson, FindPhoneIfExistEntity.class);
                    if (entity.getRows() == null)
                        return;

                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_GUID, entity.getRows().getId() + "");
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_NAME, entity.getRows().getPhone());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_PASS_WORLD, entity.getRows().getPassword());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_NAME, entity.getRows().getName());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_SEX, entity.getRows().getSex());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_AGE, " " + entity.getRows().getAge());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_CLASS, entity.getRows().getAreaid());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_UMS_USER_TEL, entity.getRows().getPhone());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_SESSION_ID, entity.getRows().getSessionid());
                    SharedPreferencesUtil.getInstance()
                            .saveData(SharedPreferencesText.LOGIN_CK_STATE, entity.getRows().getCkstate());
                } catch (Exception e) {
                    XLog.e(getClass().getName(), e.getMessage());
                }

            }

            @Override
            public void Main() {
                messageRefreshListener.MessageRefresh();
            }
        });
    }

}
