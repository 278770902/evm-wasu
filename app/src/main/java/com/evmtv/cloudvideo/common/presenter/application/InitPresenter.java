package com.evmtv.cloudvideo.common.presenter.application;

import android.app.Application;
import android.content.Context;

import java.util.Map;

public interface InitPresenter {

    void init();

    Context getMainApplicationContext();

    String getAppPackageName();

    String getRootPnsAddress();

    String getPnsAddress();

    String getUmsAddress();

    String getCsmAddress();

    String getRootUmsAddress();

    String getRootAUMSAddress();

    void setRootAUMSAddress(String AUMSAddress);

    String getRootAUMSIAddress();

    String getRootDMSAddress();

    String getRootH5Port();

    String getRootVoiceAddress();

    String getRootDownLoadAppUrl();

    String getRootFirAppId();

    String getRootUpdateFunProviderFileAuth();

    String getRootXGAccessId();

    Map getTemporaryStorageMap();

    String getRootCsmAddress();

}
