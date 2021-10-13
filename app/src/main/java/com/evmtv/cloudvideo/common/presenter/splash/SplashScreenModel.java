package com.evmtv.cloudvideo.common.presenter.splash;

public interface SplashScreenModel {

    /**
     * 领导页运行启动模式
     * A:启动到login页面
     * B:启动到main页面
     * C:启动到web页面
     */
    int ModeA = 0;
    int ModeB = ModeA + 1;
    int ModeC = ModeB + 1;
    int ModeD = ModeC + 1;

    int StartUpMode();

}
