package com.evmtv.cloudvideo.common.model;

public class SendMessageEntity<T> {
    private int SEND_MAIN_FRAGMENT_ENTITY = 0x10000;
    private int SEND_EDIT_PERSONAL = SEND_MAIN_FRAGMENT_ENTITY + 1;

    public enum MsgId {
        SEND_MAIN_FRAGMENT_ENTITY, SEND_EDIT_PERSONAL, SEND_LOG_OUT,HIDE_BOTTOM_TAB,SHOW_BOTTOM_TAB
    }

    private MsgId msgId;

    private T data;

    public MsgId getMsgId() {
        return msgId;
    }

    public SendMessageEntity setMsgId(MsgId msgId) {
        this.msgId = msgId;
        return this;
    }

    public T getData() {
        return data;
    }

    public SendMessageEntity setData(T data) {
        this.data = data;
        return this;
    }
}
