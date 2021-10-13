package com.evmtv.cloudvideo.common.model.local;

import java.util.List;

public class MonitorControlEntity {


    private List<ControlBean> control;

    public List<ControlBean> getControl() {
        return control;
    }

    public void setControl(List<ControlBean> control) {
        this.control = control;
    }

    public static class ControlBean {
        /**
         * name : 旋转摄像头
         * icon : selector_kanjiabao_control
         * type : control
         * layout : item_monitor_control_circle
         * use : load layout
         */

        private String name;
        private String icon;
        private String type;
        private String layout;
        private String use;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLayout() {
            return layout;
        }

        public void setLayout(String layout) {
            this.layout = layout;
        }

        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }
    }
}
