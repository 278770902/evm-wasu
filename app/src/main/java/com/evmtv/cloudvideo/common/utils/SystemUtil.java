package com.evmtv.cloudvideo.common.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class SystemUtil {
    private static SystemUtil instance;
    private Context context;

    public static SystemUtil getInstance(Context context) {
        synchronized (SystemUtil.class) {
            if (instance == null && context != null)
                instance = new SystemUtil(context);
        }
        return instance;
    }

    public SystemUtil(Context context) {
        this.context = context;
    }

    @RequiresPermission(android.Manifest.permission.VIBRATE)
    public void vibrate(long milliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }


    public static boolean onKeyDown(Context context, int keyCode, KeyEvent event) {
        AudioManager audio = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                audio.adjustStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                audio.adjustStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                return true;
            default:
                break;
        }
        return false;
    }

    public static int getStreamVolume(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    private static int musicVolume = 0;

    public static void silentSwitchOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            int Volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (musicVolume != 0 && Volume == 0)
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume, 0);
        }
        Log.i("BuildUtils", "musicVolume=" + musicVolume);
    }

    public static void silentSwitchOff(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            int Volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (Volume != 0)
                musicVolume = Volume;
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            Log.d("SilentListenerService", "RINGING 取消静音");
        }
    }

    public static void hideSoftInputFromWindow(Context context, View view) {
        InputMethodManager manager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public boolean isActionSupport(String action) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(action);
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfos.size() > 0) {
            return true;
        }
        return false;
    }
}
