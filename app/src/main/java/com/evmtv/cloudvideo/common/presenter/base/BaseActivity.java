package com.evmtv.cloudvideo.common.presenter.base;


import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.service.ErtcSdkTool;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.StateMachine;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.utils.view.ViewBindUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.dialog.TipsDialog;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.rtc.ERTCCommand;
import com.evmtv.rtc.ERTCEventMonitor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class BaseActivity extends AppCompatActivity implements ERTCEventMonitor.CommonEventListener {
    public NavigationBean.MainNavigationBean.NavigationContentBean beanItem;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewBindUtil.bindViews(this, getWindow().getDecorView());
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            Log.i(getClass().getName(), "该类未使用EventBus");
        }
    }

    @Override
    protected void onStart() {
        StateMachine.getInstance().addActiveActivity();
//        AppUtils.startAppNoticeService(this);
        super.onStart();
        if (ERTCEventMonitor.getInstance() != null) {
            ERTCEventMonitor.getInstance().setCommonEventListener(this);
//            ErtcSdkTool.getInstance(this).startGetNotification();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    protected void onStop() {
        StateMachine.getInstance().removeActiveActivity();
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(SendMessageEntity msg) {
        if (msg.getMsgId() == SendMessageEntity.MsgId.SEND_LOG_OUT) {
            TipsDialogLogin();
        }
    }

    private void TipsDialogLogin() {
        Dialog dialog = new TipsDialog(this, "您的账号已在其他设备登录，请重新登录！", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.startLogin(BaseActivity.this);
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onAccountLoginOnOtherDevice() {
        String GUID = SharedPreferencesUtil.getInstance().getUserGuid(false);
        if (GUID == null || GUID.intern().length() == 0)
            return;
//        SharedPreferencesUtil.getInstance().saveData(SharedPreferencesText.LOGIN_CPN_USER_GUID, "");
//        XLog.e(getClass().getName(), "start login");
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                TipsDialogLogin();
            }
        });
    }
}

