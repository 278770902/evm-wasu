package com.evmtv.cloudvideo.common.view.SharedPreferences;

import java.util.Map;

public interface SPUtilInter {
    /**
     * 保存数据到文件
     */
    void saveData(String key, Object data);

    /**
     * 从文件中读取数据
     */
    Object getData(String key, Object defValue);

    /**
     * 清除所有数据
     */
    void clear();

    /**
     * 移除某个key值已经对应的值
     */
    void remove(String key);

    /**
     * 查询某个key是否已经存在
     */
    boolean contains(String key);

    /**
     * 返回所有的键值对
     */
    Map<String, ?> getAll();

    /**
     * @param isDefValue 默认值是否使用公众帐号
     * @return
     */
    String getUserGuid(boolean isDefValue);

    String getUserName();

    String getUserSex();

    String getUserAge();

    /**
     * @return 班级Id
     */
    String getUserClass();

    String getUserLoginName();

    String getUserLoginPassWorld();

    String getUserIcon();

    String getSessionID();

    Integer getCKState();

    Boolean getFirstOpen();

    void saveFirstOpen(Boolean value);
}
