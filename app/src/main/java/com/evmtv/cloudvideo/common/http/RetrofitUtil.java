package com.evmtv.cloudvideo.common.http;


import android.util.Log;

import com.evmtv.cloudvideo.common.BuildConfig;
import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.http.cpns.CpnsApiService;
import com.evmtv.cloudvideo.common.http.csm.CsmApiService;
import com.evmtv.cloudvideo.common.http.ums.UmsApiService;
import com.evmtv.cloudvideo.common.presenter.application.InitPresenterImpl;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil {
    protected Retrofit umsRetrofit, csmRetrofit, pnsRetrofit;
    protected Retrofit umsRootRetrofit, pnsRootRetrofit, csmRootRetrofit;
    private String TAG = getClass().getName();
    private OkHttpClient httpClientBuilder;

    private static RetrofitUtil instance;

    public static RetrofitUtil getInstance() {
        synchronized (RetrofitUtil.class) {
            if (instance == null)
                instance = new RetrofitUtil();
        }
        return instance;
    }

    public RetrofitUtil() {
        if (MainApplication.initContext == null) {
            MainApplication.initContext = new InitPresenterImpl(MainApplication.application);
            MainApplication.initContext.init();
        }
        setBUGLog();
        UMS();
        RootUms();
        PNS();
        RootPNS();
        CMS();
        RootCMS();
    }

    private void setBUGLog() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(3000, TimeUnit.SECONDS);
//        if (BuildConfig.DEBUG) {
        String boundary = "--------" + new Date().getTime();
        LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .addHeader("Charset", "UTF-8")
                .addHeader("Accept-Charset", "UTF-8")
                .addHeader("Content-Type", "multipart/form-data; boundary=" + boundary)
//                .addHeader("Cookie", )
                .request("Request")
                .response("Response")
                .build();

        httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        this.httpClientBuilder = httpClientBuilder.build();
//        }
    }

    private void UMS() {
        Log.i(TAG, MainApplication.initContext.getUmsAddress());
        umsRetrofit = new Retrofit.Builder() // 设置数据适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(dealBaseUrl(MainApplication.initContext.getUmsAddress()))
                .build();
    }

    private void CMS() {
        Log.i(TAG, dealBaseUrl(MainApplication.initContext.getCsmAddress()));
        csmRetrofit = new Retrofit.Builder() // 设置数据适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(dealBaseUrl(MainApplication.initContext.getCsmAddress()))
                .build();
    }

    private void RootUms() {
        umsRootRetrofit = new Retrofit.Builder() // 设置数据适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(dealBaseUrl(MainApplication.initContext.getRootUmsAddress()))
                .build();
    }

    private void PNS() {
        String basUrl = MainApplication.initContext.getPnsAddress();
        if (basUrl == null || basUrl.isEmpty())
            basUrl = MainApplication.initContext.getRootPnsAddress();
        pnsRetrofit = new Retrofit.Builder() // 设置数据适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(dealBaseUrl(basUrl))
                .build();
    }

    private void RootPNS() {
        pnsRootRetrofit = new Retrofit.Builder() // 设置数据适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(dealBaseUrl(MainApplication.initContext.getRootPnsAddress())).build();
    }


    private void RootCMS() {
        csmRootRetrofit = new Retrofit.Builder() // 设置数据适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(dealBaseUrl(MainApplication.initContext.getRootCsmAddress()))
                .build();
    }

    public String dealBaseUrl(String url) {
        if (url == null || url.intern().length() <= 0)
            return "";
        if (!url.intern().endsWith("/"))
            return url + "/";
        return url;
    }

    public UmsApiService umsCall() {
        return umsRetrofit.create(UmsApiService.class);
    }

    public RootUmsApiService rootUmsCall() {
        return umsRootRetrofit.create(RootUmsApiService.class);
    }

    public RootPnsApiService rootPnsCall() {
        return pnsRootRetrofit.create(RootPnsApiService.class);
    }

    public CpnsApiService pnsCall() {
        return pnsRetrofit.create(CpnsApiService.class);
    }

    public RootCsmApiService rootCsmCall() {
        return csmRootRetrofit.create(RootCsmApiService.class);
    }

    public CsmApiService csmCall() {
        return csmRetrofit.create(CsmApiService.class);
    }

}
