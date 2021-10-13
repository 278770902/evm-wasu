package com.evmtv.cloudvideo.common.presenter.setting;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.adapter.MainSettingRecyclerAdapter;
import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.model.local.MySettingInfoEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.utils.JSONLocalObject;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;
import com.evmtv.cloudvideo.common.utils.fir.utils.GetAppInfo;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import java.util.Collections;

public class BaseSettingPresenterImpl implements SettingPresenter {
    protected Context activity;
    protected MySettingInfoEntity mySettingInfoEntity;
    protected OnSettingItemSelectedListener listener;
    protected OnSettingTwoItemSelectedListener listenerTwo;
    protected String backgroundIcon;

    public BaseSettingPresenterImpl(Context activity, NavigationBean.MainNavigationBean.NavigationContentBean beanItem
            , OnSettingItemSelectedListener listener
            , OnSettingTwoItemSelectedListener listenerTwo) {
        mySettingInfoEntity = JSONLocalObject.parseObject(beanItem.getContent().getValue(), MySettingInfoEntity.class);
        sort();
        this.activity = activity;
        this.listener = listener;
        this.listenerTwo = listenerTwo;
    }

    @Override
    public String getBackgroundIcon() {
        return mySettingInfoEntity.getBackgroundIcon();
    }


    @Override
    public String getUserState() {
        return "";
    }

    @Override
    public void startEditPersonalActivity() {
        Intent intent = new Intent();
        intent.setAction(mySettingInfoEntity.getEditPersonalAction());
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        activity.startActivity(intent);
    }

    protected void sort() {
        if (mySettingInfoEntity != null) {
            if (mySettingInfoEntity.getLevelOne() != null)
                Collections.sort(mySettingInfoEntity.getLevelOne());
            if (mySettingInfoEntity.getLevelTwo() != null)
                Collections.sort(mySettingInfoEntity.getLevelTwo());
        }
    }


    @Override
    public void UpdatePersonalInfo(MessageRefreshListener messageRefreshListener) {
        AppExecutors.getInstance().networkIOToMain(activity,new OrderMethodInter() {

            @Override
            public void IO() {
                String tel = SharedPreferencesUtil.getInstance().getUserLoginName();
                String ssoToken = SharedPreferencesUtil.getInstance().getSessionID();
                /*String entityJson = EdumsInteractive.getInstance().findPhoneIfExist(tel, tel, ssoToken);
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
                            .saveData(SharedPreferencesText.LOGIN_CPN_USER_AGE, entity.getRows().getAge());
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
                }*/
            }

            @Override
            public void Main() {
                messageRefreshListener.MessageRefresh();
            }
        });
    }

    @Override
    public void InitDisplay(Object TabLayout, Object viewObject) {
        if (TabLayout instanceof TabLayout) {
            initBottomNavigationView((TabLayout) TabLayout);
        }

        if (viewObject instanceof android.support.v7.widget.RecyclerView) {
            RecyclerView view = (RecyclerView) viewObject;
            view.setLayoutManager(new LinearLayoutManager(activity));
            MainSettingRecyclerAdapter adapter = new MainSettingRecyclerAdapter(activity, mySettingInfoEntity, listenerTwo);
            adapter.addFoot(R.layout.item_main_setting_adapter_foot);
            view.setAdapter(adapter);
        }

        if (viewObject instanceof ListView) {
            ListView view = (ListView) viewObject;
            view.setAdapter(new MainSettingListViewAdapter(activity, mySettingInfoEntity, listenerTwo));
            View footerView = LayoutInflater.from(activity).inflate(R.layout.item_main_setting_adapter_foot, null);
            footerView.findViewById(R.id.textUserRule).setOnClickListener(new OnFootClickListener(mySettingInfoEntity.getUserAgreement(), activity));
            footerView.findViewById(R.id.textHideRule).setOnClickListener(new OnFootClickListener(mySettingInfoEntity.getPrivacyPolicy(), activity));
            ((TextView)footerView.findViewById(R.id.tvFiveVersion)).setText(GetAppInfo.getAppVersionName(activity));
            view.addFooterView(footerView);
        }
    }

    private void initBottomNavigationView(TabLayout MainBottomNavigationViewID) {
        if (mySettingInfoEntity == null)
            return;
        if (mySettingInfoEntity.getLevelOne() == null)
            return;
        for (MySettingInfoEntity.LevelOneBean bean : mySettingInfoEntity.getLevelOne()) {
            TabLayout.Tab tab = MainBottomNavigationViewID.newTab();
            tab.setText(bean.getName());
            tab.setTag(bean);
            tab.setIcon(ResConversionUtil.getDrawableOrMipmapId(bean.getIcon()));
            MainBottomNavigationViewID.addTab(tab);
        }
        MainBottomNavigationViewID.addOnTabSelectedListener(new OnMainSettingSelectedListener(listener));
    }
}
