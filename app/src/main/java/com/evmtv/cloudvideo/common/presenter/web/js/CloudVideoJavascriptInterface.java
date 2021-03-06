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
//        intent.putExtra(INTENT_TITLE_KEY, "????????????");
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

    //?????????????????????
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

    //TODO ???????????????????????????
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
        //?????????
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
        String appId = "wx1052e4d6c8306d4e"; // ?????????AppId
        IWXAPI api = WXAPIFactory.createWXAPI(activity, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_67b05e6368fd"; // ??????????????????id
//        req.path = "pages/index/index";                  //???????????????????????????????????????????????????????????????????????????
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// ???????????? ?????????????????????????????????
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
        intent.putExtra("screen", screen);//0 ?????? 1??????
        Log.e(TAG, "playurl=" + playurl + " screen=" + screen);
        activity.startActivity(intent);
        *//*Intent videoCallIntent = new Intent(activity, LiveVideoPlayerActivity.class);
        videoCallIntent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_VIDEO_DEVICE, "");
        videoCallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        videoCallIntent.putExtra(LiveVideoPlayerActivity.INTENT_KEY_PLAY_URL, "http://123.160.94.231:9555/aums/video/20190717123029302.mp4");
        activity.startActivity(videoCallIntent);*//*
    }

    *//**
     * @param url  ?????????????????? ??? ????????????????????????????????????????????????
     * @param cid  ?????????ID
     * @param vid  ??????ID
     * @param name ????????????
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
        intent.putExtra("screen", 1);//0 ?????? 1??????
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
        intent.putExtra("screen", screen);//0 ?????? 1??????
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
     * ????????????
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
                        "???????????????????????????????????????", "??????",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mainIntent = new Intent(activity, LoginActivity.class);
                                mainIntent.putExtra("IntentType", "gotoLoginAct");
                                activity.startActivityForResult(mainIntent, GotoLoginActRequestCode);
                            }
                        }, "??????",
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
        //????????????????????????
        Intent intent = new Intent();
        //???????????????????????????
        intent.setAction(Intent.ACTION_DIAL);
        //???????????????????????????
        intent.setData(Uri.parse("tel:" + phone));
        //????????????????????????
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void toShare(String content) {

        *//**
     * ????????????
     *
     * @param content
     * @version ???????????????2014-2-12 ??????1:31:44
     *//*
        // ???????????????????????????uri
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent.putExtra(Intent.EXTRA_TITLE, "????????????");
//        shareIntent.putExtra("Kdescription", "??????????????????");
        shareIntent.putExtra(Intent.EXTRA_ASSIST_CONTEXT, "????????????");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, content);
        // ???????????????????????????
        // startActivity(Intent.createChooser(shareIntent, "????????????"));
        // ??????????????????
        activity.startActivity(shareIntent);
    }

    @JavascriptInterface
    public void gotoUMShare(String url, String title, String desc, String logo) {
        Log.i("share", "url:" + url + "???title???" + title + "???desc:" + desc + "???logo" + logo);
        UMImage image;
        if (logo != null && logo.length() > 0) {
            image = new UMImage(activity, new UMShareUtil().returnBitmap(logo));//????????????
        } else {
//            image = new UMImage(activity, R.mipmap.icon_xc);//????????????
            image = new UMImage(activity, R.mipmap.icon_1);//????????????
        }
        final UMWeb web = new UMWeb(url); //???????????? ??????????????????????????????http??????
        web.setTitle(title);//??????
        web.setThumb(image);  //?????????
        web.setDescription(desc);//??????
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
        String appId = "wxa13d0fe73d4c1bca"; // ?????????AppId
        IWXAPI api = WXAPIFactory.createWXAPI(activity, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_760aea4e30e3"; // ??????????????????id
//        req.path = "pages/index/index";                  //???????????????????????????????????????????????????????????????????????????
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// ???????????? ?????????????????????????????????
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
     * ???????????????
     *//*
    @JavascriptInterface
    public void showCamera() {
        Log.e("CloudVideo", "------showCamera------");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 2);
    }*/

}
