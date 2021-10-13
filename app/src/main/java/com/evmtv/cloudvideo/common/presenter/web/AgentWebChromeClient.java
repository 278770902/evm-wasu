package com.evmtv.cloudvideo.common.presenter.web;

import android.util.Log;
import android.webkit.ConsoleMessage;

import com.just.agentweb.WebChromeClient;

public class AgentWebChromeClient extends WebChromeClient {
    private String TAG = getClass().getName();


    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.i("EWebViewConsole", consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
