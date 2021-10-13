package com.evmtv.cloudvideo.common.view.video;

public interface VideoPlayPresenter {
    /**
     * @param phoneDuration 播放时间 s
     * @param videoDuration 视频时长 s
     * @param playPercent   视频播放百分比
     */
    void end(int phoneDuration, int videoDuration, int playPercent);
}
