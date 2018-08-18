package com.demo.common;

import com.demo.utils.PropertiesLoader;
import org.springframework.web.socket.WebSocketSession;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;


/**
 * 全局静态对象类
 *
 * @author tuzhengsong
 */
public class Global {
    /**
     * 单例设计模板（此处无任何意义）
     */
    private Global() {
    }

    private static Global instance = null;

    public synchronized static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 全局gson对象
     */
    public static final Gson GSON = new Gson();

    /**
     * socket在线人员
     */
    public static final Map<Integer, WebSocketSession> USER_SOCKET_SESSION_MAP = new HashMap<>();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");


    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = propertiesLoader.getProperty(key);
            map.put(key, value != null ? value : "");
        }
        try {

            if (value != null) {
                return new String(value.getBytes("iso8859-1"), "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
