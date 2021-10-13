package com.evmtv.cloudvideo.common.utils;

import com.evmtv.cloudvideo.common.view.tool.XLog;

public class StateMachine {
    private int activeActivityCount = 0;
    private static StateMachine instance;
    private String TAG = "StateMachine";

    public void reset() {
        synchronized (StateMachine.class) {
            StateMachine m = new StateMachine();
            m.activeActivityCount = instance.activeActivityCount;
            instance = m;
        }
    }

    public static StateMachine getInstance() {
        synchronized (StateMachine.class) {
            if (instance == null)
                instance = new StateMachine();
        }

        return instance;
    }

    public void addActiveActivity() {
        activeActivityCount++;
        XLog.i(TAG, "enter foreground");
    }

    public void removeActiveActivity() {
        activeActivityCount--;
        if (activeActivityCount == 0)
            XLog.i(TAG, "enter background");
    }

    public boolean isBackground() {
        return activeActivityCount == 0;
    }
}
