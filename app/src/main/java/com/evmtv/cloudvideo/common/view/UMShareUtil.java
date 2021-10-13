package com.evmtv.cloudvideo.common.view;

import android.app.Activity;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

public class UMShareUtil {

    private UMShareListener umShareListener;

    public void ShareWebAction(final Activity activity, final UMWeb web) {
        new ShareAction(activity)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            if (share_media == SHARE_MEDIA.QQ) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.QQ)
                                        .withMedia(web)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装QQ,再进行分享操作!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.WEIXIN)) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN)
                                        .withMedia(web)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装微信,再进行分享操作!", Toast.LENGTH_SHORT).show();

                            }
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.QQ)) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.QZONE)
                                        .withMedia(web)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装QQ,再进行分享操作!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.WEIXIN)) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                        .withMedia(web)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装微信,再进行分享操作!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }).open();
    }

    public void ShareImageAction(final Activity activity, final UMImage image,final String text){
        new ShareAction(activity)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.QQ)){
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.QQ)
                                        .withText(text)
                                        .withMedia(image)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装QQ,再进行分享操作!", Toast.LENGTH_SHORT).show();
                            }

                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.WEIXIN)) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN)
                                        .withText(text)
                                        .withMedia(image)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装微信,再进行分享操作!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.QQ)) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.QZONE)
                                        .withText(text)
                                        .withMedia(image)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装QQ,再进行分享操作!", Toast.LENGTH_SHORT).show();

                            }
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            if(UMShareAPI.get(activity).isInstall(activity,SHARE_MEDIA.WEIXIN)) {
                                new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                        .withText(text)
                                        .withMedia(image)
                                        .setCallback(umShareListener)
                                        .share();
                            }else{
                                Toast.makeText(activity, "请先安装微信,再进行分享操作!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).open();
    }
}
