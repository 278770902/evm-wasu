package com.evmtv.cloudvideo.common.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.evmtv.cloudvideo.common.R;

import java.lang.reflect.Field;


public class ResConversionUtil {

    public static int getDrawableOrMipmapId(String var) {
        int result = getDrawableId(var);
        if (result != 0)
            return result;
        return getMipmapId(var);
    }

    private static int getDrawableId(String var) {
        try {
            Field field = R.drawable.class.getField(var);
            return field.getInt(new R.drawable());
        } catch (Exception e) {
            return 0;
        }
    }

    private static int getMipmapId(String var) {
        try {
            Field field = R.mipmap.class.getField(var);
            return field.getInt(new R.mipmap());
        } catch (Exception e) {
            return 0;
        }
    }

    public static Fragment getFragmentId(Activity activity, String var) {
        try {
            return Fragment.instantiate(activity, var);
        } catch (Exception e) {
            return null;
        }
    }

}
