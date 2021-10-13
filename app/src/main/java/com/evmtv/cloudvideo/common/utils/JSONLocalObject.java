package com.evmtv.cloudvideo.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.MainApplication;

public class JSONLocalObject {

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (text.endsWith(".json")) {
            text = ReadLocalJsonFile.read(MainApplication.initContext.getMainApplicationContext(), text);
        }
        return JSONObject.parseObject(text, clazz);
    }


    public static boolean isJSON(String str) {
        boolean result;
        try {
            Object obj = JSON.parse(str);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}
