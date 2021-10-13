package com.evmtv.cloudvideo.common.persenter.login;

import com.evmtv.cloudvideo.common.http.RetrofitUtil;
import com.evmtv.cloudvideo.common.http.edums.EdumsApiService;

public class RetrofitUtilExclusive extends RetrofitUtil {

    private static RetrofitUtilExclusive instance;

    public static RetrofitUtilExclusive getInstance() {
        synchronized (RetrofitUtilExclusive.class) {
            if (instance == null)
                instance = new RetrofitUtilExclusive();
        }
        return instance;
    }



    public EdumsApiService edumsCall() {
        return pnsRootRetrofit.create(EdumsApiService.class);
    }

}
