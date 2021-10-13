package com.evmtv.cloudvideo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.utils.SystemUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import org.junit.Test;

public class JavaTest {


    public static void main(String[] args) {
        Test();
    }
    public static void Test(){
//        String json = "{\"code\":200,\"msg\":\"请求已经成功处理\",\"total\":null,\"rows\":\"{\\\"phone\\\":\\\"13072125607\\\",\\\"card\\\":[\\\"123456\\\",null],\\\"overday\\\":\\\"0\\\"}\"}";
        String json = "{\"code\":200,\"msg\":\"请求已经成功处理\",\"total\":null,\"rows\":\"{\\\"phone\\\":\\\"13072125607\\\",\\\"card\\\":[null,null],\\\"overday\\\":\\\"���ܿ�����Ϊ��\\\"}\"}";
        System.out.println("json=" + json);
        JSONObject jsonObject = JSON.parseObject(json);
        if (!jsonObject.containsKey("code")) {
            System.out.println("不包含code");
            return;
        }
        int result = jsonObject.getInteger("code");
        if (result != 200) {
            System.out.println("code!=200");
            return;
        }
        System.out.println("code==200");
        String value = json.substring(json.indexOf("[")+1, json.indexOf("]"));
        System.out.println("value=" + value);
        value=value.replaceAll("\"","");
        System.out.println("value=" + value);
        value=value.replaceAll("\\\\","");
        System.out.println("value=" + value);
        String[] key=value.split(",");
        System.out.println("key[0]:"+key[0]+"    key[1]:"+key[1]);

    }


    public static void Test2(){

    }
}
