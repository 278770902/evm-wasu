package com.evmtv.cloudvideo.common.persenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;

import org.greenrobot.eventbus.EventBus;

public class LoginOutTool {

    public static Boolean SendLoginOut(String json) {
        try {
            JSONObject object = JSON.parseObject(json);
            String code = object.containsKey("code") ? object.getString("code") : "-1";
            String msg = object.containsKey("msg") ? object.getString("msg") : "";
            if (code.equals("505") || msg.equals("登录失效，请重新登录!")) {
                EventBus.getDefault().postSticky(new SendMessageEntity<>()
                        .setMsgId(SendMessageEntity.MsgId.SEND_LOG_OUT));
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
