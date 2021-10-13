package com.evmtv.cloudvideo.common.presenter.web;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.evmtv.cloudvideo.common.presenter.web.js.CloudVideoJavascriptInterface;

import android.widget.LinearLayout;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.AnalysisLocalJsonIntentUtil;
import com.evmtv.cloudvideo.common.utils.AndroidBug5497Workaround;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;

import java.util.HashMap;
import java.util.Map;


public class EWebViewPresenterImpl implements EWebViewPresenter {

    private static String title;
    private Activity activity;
    private String TAG = getClass().getName();
    private AgentWeb mAgentWeb;

    public EWebViewPresenterImpl(Activity activity, String titleName) {
        this.activity = activity;
        this.title = titleName;
    }

    public boolean isNUll() {
        return mAgentWeb == null ? true : false;
    }

    @Override
    public void onBack() {
        WebView webView = mAgentWeb.getWebCreator().getWebView();
        if (webView.getUrl().contains("evm") || webView.getUrl().contains("android_asset")) {
            String url = "(typeof window.systemEvent != 'function') ? true : window.systemEvent({'which': 13, 'keyCode': 13})";
            webView.evaluateJavascript(url, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    if (value != null && (value.equals("false") || value.equals("0"))) {
                        XLog.i(TAG, "back key function return " + value);
                    } else {
                        XLog.i(TAG, "go back because back key function return " + value);
                        mAgentWeb.back();
                    }
                }
            });
        } else {
            mAgentWeb.back();
        }
    }

    @Override
    public void attributeSetting(View loadView) {
        Log.i(TAG, "init");
        //WebView载入该url地址的页面并显示。
        mAgentWeb = AgentWeb.with(activity)//
                .setAgentWebParent((ViewGroup) loadView, -1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb的父控件。
                .setCustomIndicator(new CoolIndicatorLayout(activity))
//                .useDefaultIndicator(activity.getResources().getColor(R.color.title_background), 3)//设置进度条颜色与高度，-1为默认值，高度为2，单位为dp// 。
                .setWebViewClient(new AgentWebViewClient())
                .setWebChromeClient(new AgentWebChromeClient())
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1) //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .interceptUnkownUrl() //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .createAgentWeb()//创建AgentWeb。
                .ready()//设置 WebSettings。
                .get();

        AgentWebConfig.debug();
        AndroidBug5497Workaround.assistActivity(activity);
        mAgentWeb.getWebCreator().getWebView().setOverScrollMode(WebView.OVER_SCROLL_NEVER);
//		mAgentWeb.getWebCreator().getWebView().setOnLongClickListener();
        mAgentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().getMediaPlaybackRequiresUserGesture();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mAgentWeb.getWebCreator().getWebView().getSettings().setMediaPlaybackRequiresUserGesture(false);
        mAgentWeb.getWebCreator().getWebView().setLayerType(View.DRAWING_CACHE_QUALITY_AUTO, null);
        //优先使用网络
//      LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
//      LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        mAgentWeb.getWebCreator().getWebView().getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //将图片调整到适合webview的大小
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        //支持内容重新布局
        mAgentWeb.getWebCreator().getWebView().getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持自动加载图片
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadsImagesAutomatically(true);
        //当webview调用requestFocus时为webview设置节点
        mAgentWeb.getWebCreator().getWebView().getSettings().setNeedInitialFocus(true);
        //自适应屏幕
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadWithOverviewMode(true);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 javascript 来操作这些数据。）
        mAgentWeb.getWebCreator().getWebView().getSettings().setDomStorageEnabled(true);

        mAgentWeb.getWebCreator().getWebView().getSettings().setAppCachePath(activity.getCacheDir().getAbsolutePath());
        mAgentWeb.getWebCreator().getWebView().getSettings().setAppCacheEnabled(true);

        mAgentWeb.getWebCreator().getWebView().getSettings().setGeolocationEnabled(true);
        //支持缩放
        mAgentWeb.getWebCreator().getWebView().getSettings().setBuiltInZoomControls(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setSupportZoom(true);
        //允许webview对文件的操作
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccess(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowUniversalAccessFromFileURLs(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAgentWeb.getWebCreator().getWebView().getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
//        mAgentWebMaps.put(title, mAgentWeb);
//        mAgentWeb.clearWebCache();
        addJsInterfaceHolder();
    }


    private void addJsInterfaceHolder() {
        if (mAgentWeb == null)
            return;
        mAgentWeb.getJsInterfaceHolder().addJavaObject("cloudVideo", new CloudVideoJavascriptInterface(activity, mAgentWeb));
//        mAgentWeb.getJsInterfaceHolder().addJavaObject("ELive", new EWebViewLiveJavascriptInterface(activity));
    }


    @Override
    public void loadPage(String url) {
        if (url.isEmpty())
            return;
        if (url.startsWith("www")) {
            url = "https://" + url;
        }
        Log.i(TAG, "old curr=" + mAgentWeb.getWebCreator().getWebView().getUrl());
        if (url.equals(mAgentWeb.getWebCreator().getWebView().getUrl()))
            return;
//        mAgentWebMaps.get(title).getWebCreator().getWebView().loadUrl(url);
        mAgentWeb.getUrlLoader().loadUrl(url);

    }


    public void reload() {
        mAgentWeb.getUrlLoader().reload();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
    }


}
