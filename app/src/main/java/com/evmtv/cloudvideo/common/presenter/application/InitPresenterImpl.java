package com.evmtv.cloudvideo.common.presenter.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.AnalysisLocalJsonIntentUtil;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SPUtilInter;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.media.MediaStreamConfig;
import com.evmtv.rtc.ERTCSDK;
import com.evmtv.util.view.EvmPlayerConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * 原始navigationInfo.json 不可修改
 * root address 原始版本IP ,开放可以修改,变更数据保存在SharedPreferences中Config文件下
 * com.evmtv.rtc.csInteractive.ServerConfig.getInstance().setRootCpnsAddress("http://" + getRootPnsAddress() + "/cpns_v3");
 * com.evmtv.rtc.csInteractive.ServerConfig.getInstance().setCsmAddress("http://" + getRootUmsAddress() + "/csm_v3");
 * com.evmtv.rtc.csInteractive.ServerConfig.getInstance().setUmsAddress("http://" + getRootPnsAddress() + "/ums_v3");
 */
public class InitPresenterImpl implements InitPresenter {
    private Application application;
    private String TAG = getClass().getName();
    private Map<Object, Object> TemporaryStorageMap = new HashMap<>();


    private static InitPresenterImpl instance;

    public static InitPresenterImpl getInstance(Application application) {
        synchronized (InitPresenterImpl.class) {
            if (instance == null && application != null)
                instance = new InitPresenterImpl(application);
        }
        return instance;
    }

    private SPUtilInter getShared() {
        return SharedPreferencesUtil.getInstance();
    }

    @Override
    public void init() {
        ReadLocalJsonFile.initData(application);
        EvmPlayerConfig.init(application);
        MediaStreamConfig.init(application);
        AppUtils.getInstance(application).init();
        ERTCSDK.init(application, "", "", getRootPnsAddress()
                , getRootUmsAddress()
                , getRootCsmAddress());


    }

    public InitPresenterImpl(Application application) {
        this.application = application;
    }

    @Override
    public Context getMainApplicationContext() {
        return application;
    }

    @Override
    public String getAppPackageName() {
        return application.getApplicationInfo().packageName;
    }

    @Override
    public String getRootPnsAddress() {
        return getAddress(SharedPreferencesText.PNS_ADDRESS, ReadLocalJsonFile.getLocalBean().getAddressPNS());
    }

    @Override
    public String getPnsAddress() {
        return getAddress(SharedPreferencesText.PNS_ADDRESS,com.evmtv.rtc.csInteractive.ServerConfig.getInstance().getPayloadCpnsAddress());
    }

    @Override
    public String getUmsAddress() {
        return getAddress(SharedPreferencesText.UMS_ADDRESS,com.evmtv.rtc.csInteractive.ServerConfig.getInstance().getUmsAddress());
    }

    @Override
    public String getCsmAddress() {
        return getAddress(SharedPreferencesText.CSM_ADDRESS,com.evmtv.rtc.csInteractive.ServerConfig.getInstance().getCsmAddress());
    }

    @Override
    public String getRootUmsAddress() {
//        return getAddress(SharedPreferencesText.UMS_ADDRESS, ReadLocalJsonFile.getLocalBean().getAddressUMS());
        return ReadLocalJsonFile.getLocalBean().getAddressUMS();
    }

    @Override
    public String getRootCsmAddress() {
//        return getAddress(SharedPreferencesText.CSM_ADDRESS, ReadLocalJsonFile.getLocalBean().getAddressCSM());
        return ReadLocalJsonFile.getLocalBean().getAddressCSM();
    }

    @Override
    public String getRootAUMSAddress() {
        return getAddress(SharedPreferencesText.AUMS_ADDRESS, ReadLocalJsonFile.getLocalBean().getAddressAUMS());
    }

    @Override
    public void setRootAUMSAddress(String AUMSAddress) {
        SharedPreferencesUtil.getInstance().saveData(SharedPreferencesText.AUMS_ADDRESS, "");
    }

    @Override
    public String getRootAUMSIAddress() {
        return getAddress(SharedPreferencesText.AUMSI_ADDRESS, ReadLocalJsonFile.getLocalBean().getAddressAUMSI());
    }

    @Override
    public String getRootDMSAddress() {
        return getAddress(SharedPreferencesText.DMS_ADDRESS, ReadLocalJsonFile.getLocalBean().getAddressDMS());
    }

    @Override
    public String getRootH5Port() {
        return getAddress(SharedPreferencesText.H5_Port, ReadLocalJsonFile.getLocalBean().getH5port());
    }

    @Override
    public String getRootVoiceAddress() {
        return ReadLocalJsonFile.getLocalBean().getAddressVOICE();
    }

    @Override
    public String getRootDownLoadAppUrl() {
        return ReadLocalJsonFile.getLocalBean().getDownloadAppUrl();
    }

    @Override
    public String getRootFirAppId() {
        return ReadLocalJsonFile.getLocalBean().getFirAppId();
    }

    @Override
    public String getRootUpdateFunProviderFileAuth() {
        return ReadLocalJsonFile.getLocalBean().getProviderFileAuthorities();
    }

    @Override
    public String getRootXGAccessId() {
        return ReadLocalJsonFile.getLocalBean().getXgAccessId();
    }


    private String getAddress(String SharedPreferencesTextID, String context) {
        if (getShared().contains(SharedPreferencesTextID))
            return (String) getShared().getData(SharedPreferencesTextID, "");
        else
            return context;
    }

    @Override
    public Map<Object, Object> getTemporaryStorageMap() {
        return TemporaryStorageMap;
    }

}
