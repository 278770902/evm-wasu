package com.evmtv.cloudvideo.common.presenter.web.js;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.UnitConversion;
import com.evmtv.cloudvideo.common.view.LoginActivity;
import com.evmtv.cloudvideo.common.view.PlayVideoActivity;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.action.ActionName;
import com.evmtv.cloudvideo.common.view.dialog.LogOutDialog;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.rtc.BaseResult;
import com.evmtv.rtc.csInteractive.csm.CSMInteractive;
import com.evmtv.util.http.AsyncInvokeAdapter;
import com.just.agentweb.AgentWeb;

import org.greenrobot.eventbus.EventBus;

import main.MainActivity;

public class CloudVideoJavascriptInterface {

    private Activity activity;
    private AgentWeb agentWeb;
    private String TAG = getClass().getName();
    public static final int startShortVideoRecordActivityRequestCode = 1100;
    public static final int startShortVideoRecordActivityResultCode = 1101;

    public CloudVideoJavascriptInterface(Activity activity, AgentWeb agentWeb) {
        this.activity = activity;
        this.agentWeb = agentWeb;
    }


    @JavascriptInterface
    public void StartMeetingActivity(String title) {
        Log.i(TAG, "start MeetingMainActivity");
        Intent intent = new Intent();
        intent.setAction("com.evmtv.cloudvideo.common.MeetingMainActivity");
        intent.setPackage(MainApplication.initContext.getAppPackageName());
//        intent.putExtra(INTENT_TITLE_KEY, title);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void StartMeetingActivity() {
        Log.i(TAG, "start MeetingMainActivity");
        Intent intent = new Intent();
        intent.setAction("com.evmtv.cloudvideo.common.MeetingMainActivity");
        intent.setPackage(MainApplication.initContext.getAppPackageName());
//        intent.putExtra(INTENT_TITLE_KEY, "视频会议");
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void StartMeetingActivity(String title, String tab1, String tab2) {
        Log.i(TAG, "start MeetingMainActivity");
        Intent intent = new Intent();
        intent.setAction("com.evmtv.cloudvideo.common.MeetingMainActivity");
        intent.setPackage(MainApplication.initContext.getAppPackageName());
//        intent.putExtra(INTENT_TITLE_KEY, title);
//        intent.putExtra(INTENT_TAB1_KEY, tab1);
//        intent.putExtra(INTENT_TAB2_KEY, tab2);
        activity.startActivity(intent);
    }

    //value= 1.195.31.50:8554
    @JavascriptInterface
    public String getUmsUrlAddress() {
        return com.evmtv.rtc.csInteractive.ServerConfig.getInstance().getUmsAddress();
    }

    @JavascriptInterface
    public void gotoMySetting() {
    }

    @JavascriptInterface
    public void OpenAgentWeb(String url) {

    }

    @JavascriptInterface
    public boolean startShortVideoRecordActivity(String userId, String userType, String userPassword, int maxVideoDuration, String videotype
            , String columnId, String websiteid, String publishpower, String addrConfig, String araeId) {
        return true;
    }


    @JavascriptInterface
    public Boolean startShortVideoRecordActivity(String userId, String userType, String userPassword, int maxVideoDuration, String videotype
            , String columnId, String websiteid, String publishpower, String addrConfig, String araeId, String serviceTypeId, String subServiceTypeId) {
        return true;
    }

    @JavascriptInterface
    public Boolean startShortVideoRecordActivity(String userId, String userType, String userPassword, int maxVideoDuration) {
        return true;
    }

    //启动评论回复框
    @JavascriptInterface
    public void InputTextMsgDialog() {
        InputTextMsgDialog inputTextMsgDialog = new InputTextMsgDialog(activity, R.style.dialog_center);
        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
//                String url = "(typeof window.systemEvent != 'function') ? true : window.systemEvent({'which': 3000, 'keyCode': '" + msg + "'})";
                if (agentWeb != null)
                    agentWeb.getJsAccessEntrace().quickCallJs("window.systemEvent", "{'which': 3000, 'keyCode': '" + msg + "'}");

            }
        });
        inputTextMsgDialog.show();
    }

    @JavascriptInterface
    public int getTitleBarHigh() {
        return UnitConversion.getInstance().dip2px(activity, activity.getResources().getDimension(R.dimen.TitleHighDP));
    }

    @JavascriptInterface
    public String getPnsAddress() {
        return com.evmtv.rtc.csInteractive.ServerConfig.getInstance().getRootCpnsAddress();
    }

    @JavascriptInterface
    public String getH5PortAddress() {
        return MainApplication.initContext.getRootH5Port();
    }

    @JavascriptInterface
    public String getUserId() {
        String GUID = SharedPreferencesUtil.getInstance().getUserGuid(false);
        XLog.i(TAG, "User id=" + GUID);
        return GUID;
    }

    @JavascriptInterface
    public String getUserName() {
        return SharedPreferencesUtil.getInstance().getUserName();
    }

    @JavascriptInterface
    public String getSessionID() {
        return SharedPreferencesUtil.getInstance().getSessionID();
    }


    @JavascriptInterface
    public String getLoginMobile() {
        return (String) SharedPreferencesUtil.getInstance()
                .getUserLoginName();
    }

    //TODO 准备废弃，没有意义
    @JavascriptInterface
    public String getDevUUID() {
        return "";
    }

    @JavascriptInterface
    public String getAumsUser() {
        return MainApplication.initContext.getRootAUMSAddress();
    }

    @JavascriptInterface
    public void setAumsUser(String aumsUser) {
        MainApplication.initContext.setRootAUMSAddress(aumsUser);
    }

    @JavascriptInterface
    public void setString(String key, String value) {
        if ("storage_current_server_address".equals(key)) {
            MainApplication.initContext.getTemporaryStorageMap().put(key, value);
            return;
        }
        SharedPreferencesUtil.getInstance().saveData(key, value);
    }

    @JavascriptInterface
    public String getString(String key, String defValue) {
        if ("storage_current_server_address".equals(key)) {
            if (!MainApplication.initContext.getTemporaryStorageMap().containsKey(key))
                return defValue;
            return (String) MainApplication.initContext.getTemporaryStorageMap().get(key);
        }
        return (String) SharedPreferencesUtil.getInstance().getData(key, defValue);
    }

    @JavascriptInterface
    public void gotoScanAct() {
        Intent intent = new Intent();
        intent.setAction(ActionName.ACTIVITY_ACTION_SCAN);
        intent.setPackage(MainApplication.initContext.getAppPackageName());
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void closeAct() {
        activity.finish();
        Log.i("EWebView", "closeAct");
    }

    @JavascriptInterface
    public void gotoVideoPlaybAct(String url) {
        Intent intent = new Intent(activity, PlayVideoActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void gotoVideoPlaybAct(String url, String playName, String playNameID, String playColumn, String playColumnID, String stbNumber) {
        Intent intent = new Intent(activity, PlayVideoActivity.class);
        intent.putExtra("url", url);
        Bundle bundle = new Bundle();
        bundle.putString("playName", playName);
        bundle.putString("playNameID", playNameID);
        bundle.putString("playColumn", playColumn);
        bundle.putString("playColumnID", playColumnID);
        bundle.putString("stbNumber", stbNumber);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    @JavascriptInterface
    public void gotoLoginAct() {
        new LogOutDialog(activity).show();
    }

    @JavascriptInterface
    public void gotoDirectLogin() {
        AppUtils.startLogin(activity);
    }


    @JavascriptInterface
    public void hideTab() {
        EventBus.getDefault().post(new SendMessageEntity<>()
                .setMsgId(SendMessageEntity.MsgId.HIDE_BOTTOM_TAB));
    }

    @JavascriptInterface
    public void showTab() {
        EventBus.getDefault().post(new SendMessageEntity<>()
                .setMsgId(SendMessageEntity.MsgId.SHOW_BOTTOM_TAB));
    }

/*
    @JavascriptInterface
    public void setAreaId(String areaId) {
        AppConfig.getInstance(null).setAreaId(areaId);
    }

    @JavascriptInterface
    public void setClipboard(String clipboard) {
        AppConfig.getInstance(null).setClipboard(clipboard);
    }

    @JavascriptInterface
    public String getClipboard() {
        return AppConfig.getInstance(null).getClipboard();
    }

    @JavascriptInterface
    public void gotoFimaly() {
        EventBus.getDefault().post(new Message<>().setMsg_id(Message.MsgId.POINT_MAIN_UN));

        Intent intent = new Intent(activity,
                SCFimalyCallActivity.class);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void gotoCall() {
        //打电话
        Intent intent = new Intent(activity,
                SCCallActivity.class);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void gotoHouse() {
        EventBus.getDefault().post(new Message<>().setMsg_id(Message.MsgId.POINT_MAIN_UN));
        if (MainApp.mPackageNanme.equals(AppConfig.APP_TAG_JILIN)) {
//            if (activity instanceof JLMainAppActivity) {
//                ((JLMainAppActivity) activity).setChioceItem(-2);
//            }
            Intent intent = new Intent(activity, LookHouseFamilyActivity.class);
            activity.startActivity(intent);

//            Intent intent = new Intent(activity, PublicMonitorActivity.class);
//            Intent intent = new Intent(activity, LookHouseFamily.class);
//            activity.startActivity(intent);
        } else {
            Intent intent = new Intent(activity, FimalyMonitorActivity.class);
            activity.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void gotoWXLaunchMiniJLShop() {
        String appId = "wx1052e4d6c8306d4e"; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(activity, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_67b05e6368fd"; // 填小程序原始id
//        req.path = "pages/index/index";                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// 可选打开 开发版，体验版和正式版
        api.sendReq(req);
    }

    //  http://123.160.94.231:9555/aums/video/20190717123029302.mp4
    @JavascriptInterface
    public void gotoPlayVideo(String playurl, int screen) {
        Log.i("playvideo", "playvideo");
        Intent intent = new Intent(activity,
                PlayVideoActivity.class);//FimalyMonitorActivity
        intent.putExtra("playurl", playurl);
        intent.putExtra("useType", "");
        intent.putExtra("screen", screen);//0 竖屏 1横屏
        Log.e(TAG, "playurl=" + playurl + " screen=" + screen);
        activity.startActivity(intent);
        *//*Intent videoCallIntent = new Intent(activity, LiveVideoPlayerActivity.class);
        videoCallIntent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_VIDEO_DEVICE, "");
        videoCallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        videoCallIntent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_PLAY_URL, "http://123.160.94.231:9555/aums/video/20190717123029302.mp4");
        activity.startActivity(videoCallIntent);*//*
    }

    *//**
     * @param url  加密播放链接 否 通过平台接⼝口获取的加密播放链接
     * @param cid  栏⽬目ID
     * @param vid  视频ID
     * @param name 视频名称
     *//*
    @JavascriptInterface
    public void playHuaSuVideo(String url, String cid, String vid, String name) {
        WasuVodController.getInstance(activity).initConfig();
        Log.e(TAG, "url=" + url + " cid=" + cid + " vid=" + vid + "  name=" + name);
        String playUrl = WasuVodController.getInstance(activity).create(url, cid, vid, name, "",
//        WasuVodController.VODFORMAT.M3U8);
                WasuVodController.VODFORMAT.M3U8);
        Intent intent = new Intent(activity,
                PlayVideoActivity.class);
        intent.putExtra("playurl", playUrl);
        intent.putExtra("useType", "");
        intent.putExtra("screen", 1);//0 竖屏 1横屏
        Log.e(TAG, "playurl=" + playUrl);
//        Intent intent = new Intent(activity, LiveVideoPlayerActivity.class);
//        intent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_PLAY_URL, playUrl);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void gotoPlayVideo(String playurl, int screen, String EntrancePage) {
        Intent intent = new Intent(activity,
                PlayVideoActivity.class);//FimalyMonitorActivity
        intent.putExtra("playurl", playurl);
        intent.putExtra("useType", EntrancePage);
        intent.putExtra("screen", screen);//0 竖屏 1横屏
        Log.e(TAG, "playurl=" + playurl + " screen=" + screen + "useType=" + EntrancePage);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void hideTab() {
        EventBus.getDefault().post(new Message<>().setMsg_id(Message.MsgId.HIDE_BOTTOM_TAB));
    }

    @JavascriptInterface
    public void showTab() {
        EventBus.getDefault().post(new Message<>().setMsg_id(Message.MsgId.SHOW_BOTTOM_TAB));
    }

    @JavascriptInterface
    public void closeAct() {
        activity.finish();
        Log.i("EWebView", "closeAct");
    }



    *//**
     * 视频通话
     *//*
    @JavascriptInterface
    public void gotoVideoAct() {
        Intent intent = new Intent(activity,
                VideoTopMainActivity.class);
        activity.startActivity(intent);
    }

    public static final int GotoLoginActRequestCode = 0x121;
    public static final int GotoLoginActResultCode = GotoLoginActRequestCode + 1;

    @JavascriptInterface
    public void gotoLoginAct() {
        if (new PackageConfig(MainApp.mPackageNanme).getYoueryuanVersion()) {
            AppConfig mAppConfig = AppConfig.getInstance(activity);
            String user = mAppConfig.getUserLoginName();
            String pass = mAppConfig.getUserLoginPassword();
            if (user == null || user.isEmpty() || pass == null || pass.isEmpty()) {
                ConfirmDialog2 confirmDialog = new ConfirmDialog2(activity,
                        "您还没有登录，请先进行登录", "登录",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mainIntent = new Intent(activity, LoginActivity.class);
                                mainIntent.putExtra("IntentType", "gotoLoginAct");
                                activity.startActivityForResult(mainIntent, GotoLoginActRequestCode);
                            }
                        }, "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                        AndroidBug54971Workaround.hideBottomUIMenu(VideoMainFragment.this.getActivity());
                            }
                        });
                confirmDialog.show();
                return;
            } else {
                activity.startActivity(new Intent(activity, KindergartenMyActivity.class));
            }
            return;
        }


        Intent intent = new Intent(activity,
                LoginActivity.class);
        intent.putExtra("IntentType", "gotoLoginAct");
        activity.startActivityForResult(intent, GotoLoginActRequestCode);
    }

    @JavascriptInterface
    public void callPhone(String phone) {
        //创建打电话的意图
        Intent intent = new Intent();
        //设置拨打电话的动作
        intent.setAction(Intent.ACTION_DIAL);
        //设置拨打电话的号码
        intent.setData(Uri.parse("tel:" + phone));
        //开启打电话的意图
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void toShare(String content) {

        *//**
     * 执行分享
     *
     * @param content
     * @version 更新时间：2014-2-12 下午1:31:44
     *//*
        // 获取图片所在位置的uri
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent.putExtra(Intent.EXTRA_TITLE, "图片标题");
//        shareIntent.putExtra("Kdescription", "微信分享信息");
        shareIntent.putExtra(Intent.EXTRA_ASSIST_CONTEXT, "图片标题");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, content);
        // 自定义选择框的标题
        // startActivity(Intent.createChooser(shareIntent, "邀请好友"));
        // 系统默认标题
        activity.startActivity(shareIntent);
    }

    @JavascriptInterface
    public void gotoUMShare(String url, String title, String desc, String logo) {
        Log.i("share", "url:" + url + "、title：" + title + "、desc:" + desc + "、logo" + logo);
        UMImage image;
        if (logo != null && logo.length() > 0) {
            image = new UMImage(activity, new UMShareUtil().returnBitmap(logo));//分享图标
        } else {
//            image = new UMImage(activity, R.mipmap.icon_xc);//分享图标
            image = new UMImage(activity, R.mipmap.icon_1);//分享图标
        }
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(desc);//描述
        new UMShareUtil().ShareWebAction(activity, web);
    }

    @JavascriptInterface
    public void gotoSecWebAct(String url) {
        Intent intent = new Intent(activity, SCSecWebActivity.class);
        intent.putExtra("securl", url);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void gotoVideoPlaybAct(String url) {
//        Intent intent = new Intent(activity, LiveVideoPlayerActivity.class);
//        intent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_PLAY_URL, url);
//        activity.startActivity(intent);
        Intent intent = new Intent(activity, VideoPlayerActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }


    @JavascriptInterface
    public void gotoSelect9PhotoActivity() {
        Log.i("CloudVideo", "--gotoSelect9PhotoActivity--");

        Intent intent = new Intent(activity, Select9PhotoActivity.class);
        activity.startActivityForResult(intent, Select9PhotoActivityrequestCode);
    }


    @JavascriptInterface
    public void gotoVideoPlaybAct(String url, String title) {
        Intent intent = new Intent(activity, LiveVideoPlayerActivity.class);
        intent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_PLAY_URL, url);
        intent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_FLAG, title);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void startVideoCall(final String otherUserId) {
        Log.i("CloudVideo", "--startVideoCall--");
        AsyncInvokeAdapter.invoke(new AsyncInvokeAdapter.CodeBlock() {
            @Override
            public BaseResult run() {
                return CSMInteractive.getInstance().startVideoCall(
                        AppConfig.getInstance(null).getUserGUID(), otherUserId, "1", null);
            }
        }, null, null);
    }

    @JavascriptInterface
    public void gotoWXLaunchMiniProgram() {
        String appId = "wxa13d0fe73d4c1bca"; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(activity, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_760aea4e30e3"; // 填小程序原始id
//        req.path = "pages/index/index";                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
        api.sendReq(req);
    }



    @JavascriptInterface
    public String getLongitude() {
        return AppConfig.getInstance(null).getLongitude();
    }

    @JavascriptInterface
    public String getLatitude() {
        return AppConfig.getInstance(null).getLatitude();
    }

    @JavascriptInterface
    public String getUserIcon() {
        return AppConfig.getInstance(null).getUserIcon();
    }

    *//**
     * 打开摄像头
     *//*
    @JavascriptInterface
    public void showCamera() {
        Log.e("CloudVideo", "------showCamera------");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 2);
    }*/

}
