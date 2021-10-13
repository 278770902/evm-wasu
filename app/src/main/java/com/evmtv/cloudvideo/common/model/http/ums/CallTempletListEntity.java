package com.evmtv.cloudvideo.common.model.http.ums;

import java.util.List;

public class CallTempletListEntity {

    /**
     * total : 1
     * rows : [{"id":1452,"GUID":"45000000001448","terminalGUID":"45000000004695","cameraGUID":"4500000000     3015","cameraName":"个人摄像头","cameraSerialNum":"34020016213097270850"}]
     * isSuccess : 1
     */

    private int total;
    private int isSuccess;
    private List<RowsBean> rows;

    public CallTempletListEntity(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1452
         * GUID : 45000000001448
         * terminalGUID : 45000000004695
         * cameraGUID : 4500000000     3015
         * cameraName : 个人摄像头
         * cameraSerialNum : 34020016213097270850
         */

        private int id;
        private String GUID;
        private String terminalGUID;
        private String cameraGUID;
        private String cameraName;
        private String cameraSerialNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getTerminalGUID() {
            return terminalGUID;
        }

        public void setTerminalGUID(String terminalGUID) {
            this.terminalGUID = terminalGUID;
        }

        public String getCameraGUID() {
            return cameraGUID;
        }

        public void setCameraGUID(String cameraGUID) {
            this.cameraGUID = cameraGUID;
        }

        public String getCameraName() {
            return cameraName;
        }

        public void setCameraName(String cameraName) {
            this.cameraName = cameraName;
        }

        public String getCameraSerialNum() {
            return cameraSerialNum;
        }

        public void setCameraSerialNum(String cameraSerialNum) {
            this.cameraSerialNum = cameraSerialNum;
        }
    }
}
