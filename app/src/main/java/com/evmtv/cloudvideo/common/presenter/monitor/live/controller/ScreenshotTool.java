package com.evmtv.cloudvideo.common.presenter.monitor.live.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.util.view.EvmPlayerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotTool {
    private static Bitmap ScreenShotBitmap;
    //系统保存截图的路径
    public static final String SCREENCAPTURE_PATH = "ScreenCapture" + File.separator + "Screenshots" + File.separator;
    public static final String SCREENSHOT_NAME = "Screenshot";

    private static ScreenshotTool instance;

    public static ScreenshotTool getInstance() {
        synchronized (ScreenshotTool.class) {
            if (instance == null)
                instance = new ScreenshotTool();
        }
        return instance;
    }

    public void startScreenshot(EvmPlayerView playerView, Context context
            , String text, ImageView ivLookBackCaptureImageViewID, boolean isSave
            , boolean isSound) {
        //ToastUtils.showLong("手机版本低于7.0,不具备截图功能");
        SurfaceView surfaceView = playerView.getSurfaceView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                ScreenShotBitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
                PixelCopy.request(surfaceView, ScreenShotBitmap, new PixelCopy.OnPixelCopyFinishedListener() {
                    @Override
                    public void onPixelCopyFinished(int copyResult) {
                        if (isSound) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.snapshot);
                            mediaPlayer.start();
                        }
                        saveBitmapFile(context, text, ScreenShotBitmap, isSave);
                        if (isSound)
                            saveAnimation(context, ScreenShotBitmap, ivLookBackCaptureImageViewID);
                    }
                }, new Handler(Looper.getMainLooper()));
            } catch (IllegalArgumentException e) {
                Log.i("ScreenshotUtils", "---" + e.getMessage());
            }
        } else {
            String path = getScreenShotsName(text);
            try {
                playerView.takeSnapshot(path);
                if (isSound) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.snapshot);
                    mediaPlayer.start();
                }
                if (new File(path).exists() && isSave) {
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean isExist = new File(path).exists();
                        Log.d("ScreenshotUtils", "startScreenshot isExist: " + isExist);
                        if (isExist) {
                            if (isSound)
                                ToastUtil.setToast("截图成功！");
                            SharedPreferencesUtil.getInstance().saveData(text, path);
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path))));
                        }
                    }
                }, 500);
            } catch (Exception e) {
                Log.i("ScreenshotUtils", e.getMessage());
            }
        }
    }

    private String saveBitmapFile(Context context, String text, Bitmap bitmap, Boolean isSave) {
        File fileImage = null;
        fileImage = new File(getScreenShotsName(text));
        try {
            if (!fileImage.exists()) {
                fileImage.createNewFile();
            }
            FileOutputStream out = null;
            out = new FileOutputStream(fileImage);
            if (out != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //发送广播给相册--更新相册图片
                Log.i("litao", "fileImage:file://" + fileImage.getPath());
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileImage.getPath())));
                if (isSave) {
                    SharedPreferencesUtil.getInstance().saveData(text, fileImage.getPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileImage.getPath();
    }


    private void saveAnimation(Context context, Bitmap bitmap, ImageView view) {
        ToastUtil.setToast("截图已经成功保存到相册");
        if (view == null) return;
        //直接设置动画效果
        AnimationSet animationSet = new AnimationSet(true);
        view.setImageBitmap(bitmap);
        view.bringToFront();
        view.setVisibility(View.VISIBLE);
        animationSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim.popview_in_amin));
        view.setAnimation(animationSet);
        animationSet.getAnimations().get(0).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private String getScreenShotsName(String devicesID) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String date = simpleDateFormat.format(new Date());
        StringBuffer stringBuffer = new StringBuffer(getScreenShots());
        stringBuffer.append(SCREENSHOT_NAME);
        stringBuffer.append("_");
        stringBuffer.append(devicesID);
        stringBuffer.append("_");
        stringBuffer.append(date);
        stringBuffer.append(".png");
        return stringBuffer.toString();
    }

    private String getScreenShots() {
        StringBuffer stringBuffer = new StringBuffer(getAppPath());
        stringBuffer.append(File.separator);
        stringBuffer.append(SCREENCAPTURE_PATH);
        File file = new File(stringBuffer.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return stringBuffer.toString();
    }

    private String getAppPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
}
