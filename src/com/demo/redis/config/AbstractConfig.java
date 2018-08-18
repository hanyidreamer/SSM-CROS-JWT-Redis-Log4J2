package com.demo.redis.config;

import com.demo.redis.util.FileUtil;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * 基础配置加载抽象类
 * @author tuzhengsong
 */
public abstract class AbstractConfig {
    private static Logger logger = LogManager.getLogger("AbstractConfig");


    private Properties configProperties = null;

    synchronized boolean reloadConfig() {
        logger.debug("Base config reloadConfig().");
        if (configProperties != null) {
            configProperties.clear();
        }
        String file = getConfigFile();
        configProperties = FileUtil.loadStaticProperties(file);
        return configProperties != null;
    }

    String getProperty(String key) {
        if (configProperties == null) {
            return null;
        }
        return configProperties.getProperty(key);
    }

    public String getProperty(String key, String defaultV) {
        String strValue = defaultV;
        String temp = getProperty(key);
        if (StringUtils.isNotBlank(temp)) {
            strValue = temp;
        }
        return strValue;
    }

    int getPropertyToInt(String key, int defaultV) {
        int intValue = defaultV;
        String temp = getProperty(key);
        try {
            intValue = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return intValue;
    }

    /**
     * 获取配置文件
     *
     * @return 配置文件对象
     */
    public abstract String getConfigFile();
}