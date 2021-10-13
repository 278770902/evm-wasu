package com.evmtv.cloudvideo.common.view.SharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;

import java.util.Map;

public class SharedPreferencesUtil implements SPUtilInter {
    private static final String FILE_NAME = "Config";
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SPUtilInter mSharedPreferencesUtil;

    @SuppressLint("CommitPrefEdits")
    SharedPreferencesUtil() {
        mPreferences = MainApplication.initContext.getMainApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static SPUtilInter getInstance() {
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = new SharedPreferencesUtil();
        }
        return mSharedPreferencesUtil;
    }

    /**
     * 保存数据到文件
     */
    public void saveData(String key, Object data) {
        if (key == null)
            return;
        String type = data.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            mEditor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            mEditor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            mEditor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            mEditor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            mEditor.putLong(key, (Long) data);
        }

        SharedPreferencesCompat.apply(mEditor);
    }

    /**
     * 从文件中读取数据
     */
    public Object getData(String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return mPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return mPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return mPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return mPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return mPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        mEditor.clear();
        SharedPreferencesCompat.apply(mEditor);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        mEditor.remove(key);
        SharedPreferencesCompat.apply(mEditor);
    }

    /**
     * 查询某个key是否已经存在
     */
    public boolean contains(String key) {
        return mPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return mPreferences.getAll();
    }


    @Override
    public String getUserGuid(boolean isDefValue) {
        if (isDefValue)
            return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_GUID, ReadLocalJsonFile.getPublicAccountGuid());
        else
            return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_GUID, "");
    }

    public String getUserName() {
        return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_NAME, " ");
    }

    public String getUserSex() {
        return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_SEX, " ");
    }

    public String getUserAge() {
        return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_AGE, " ");
    }

    public String getUserClass() {
        return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_CLASS, " ");
    }

    public String getUserLoginName() {
        return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_NAME, " ");
    }

    public String getUserLoginPassWorld() {
        return (String) getData(SharedPreferencesText.LOGIN_CPN_USER_LOGIN_PASS_WORLD, " ");
    }

    public String getUserIcon() {
        return (String) getData(SharedPreferencesText.LOGIN_UMS_USER_ICON, " ");
    }

    public String getSessionID() {
        return (String) getData(SharedPreferencesText.LOGIN_SESSION_ID, " ");
    }

    public Integer getCKState() {
        return (Integer) getData(SharedPreferencesText.LOGIN_CK_STATE, -100);
    }

    public Boolean getFirstOpen() {
        return (Boolean) getData(SharedPreferencesText.APP_FIRST_OPEN, true);
    }

    public void saveFirstOpen(Boolean value) {
        saveData(SharedPreferencesText.APP_FIRST_OPEN, value);
    }


}
