package com.evmtv.cloudvideo.common.model.http;

public class bindSTBUserEntity {

    /**
     * status : 1
     */

    private int status;

    public bindSTBUserEntity() {
    }

    public bindSTBUserEntity(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
