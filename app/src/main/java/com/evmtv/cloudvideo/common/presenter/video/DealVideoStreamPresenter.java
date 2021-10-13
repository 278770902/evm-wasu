package com.evmtv.cloudvideo.common.presenter.video;

public interface DealVideoStreamPresenter {

    void ipcStopPlay();

    void FastForward(int valueTime);

    void GoBack(int valueTime);
}
