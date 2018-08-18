package com.demo.redis.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件操作工具类
 * @author tuzhengsong
 */
public class FileUtil {
    private static Logger logger = LogManager
            .getLogger("FileUtils");
    private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd/");
    private static SimpleDateFormat sdfFile = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        if (classLoader == null) {
            classLoader = FileUtil.class.getClassLoader();
        }
        return classLoader;
    }

    /**
     * 读取静态配置文件的属性 (此配置文件仅 load 一次, 适用于程序内不再修改的配置文件); 如果返回 null, 则读取不成功！
     *
     * @param fileName 文件名 (classes 目录下)
     * @return Properties
     */
    public static Properties loadStaticProperties(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        logger.debug("loadStaticProperties()-- start,[" + fileName + "]");
        Properties properties = null;
        try {
            ClassLoader classLoader = getClassLoader();
            InputStream is = classLoader.getResourceAsStream(fileName);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");

            Properties temp = new Properties();
            temp.load(isr);
            properties = new Properties();
            properties.putAll(temp);
            temp.clear();
            temp = null;
            is.close();
            is = null;
            isr.close();
            isr = null;
        } catch (Exception e) {
            logger.error("loadStaticProperties()[" + fileName + "]:" + e);
        }
        return properties;
    }


    private static String decodeFilePath(String root) {
        String http = "http:";
        if (root != null && !root.toLowerCase().contains(http)
                && root.contains("%20")) {
            root = root.replace("%20", " ");
        }
        return root;
    }


    public static String getWebRoot() {
        String temp = getConfigRoot();
        String s = "/WEB-INF/classes/";
        int index = temp.indexOf(s);
        if (index != -1) {
            temp = temp.substring(0, index + 1);// 最后带 /
        }
        return temp;
    }

    private static String getConfigRoot() {
        String root = null;
        if (isFromJar()) {
            logger.debug("getConfigRoot() -- is from JAR file.");
            root = FileUtil.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath();

            String lib = "/lib/";

            if (root != null && root.contains(lib)) {
                int index = root.indexOf(lib);
                root = root.substring(0, index) + "/classes/";
            }
        } else {
            // for web context
            try {
                root = FileUtil.class.getResource("").getPath();
                logger.debug("getConfigRoot() -- first,root=[" + root + "]");
                String s = "/WEB-INF/classes/";
                int index = root.indexOf(s);
                if (index != -1) {
                    root = root.substring(0, index) + s;
                }
            } catch (Exception e) {
                logger.warn("getConfigRoot() -- in error:" + e);
            }
        }

        root = FileUtil.decodeFilePath(root);
        return root;

    }

    private static boolean isFromJar() {
        boolean blReturn = false;
        String name = "" + FileUtil.class.getResource("FileUtils.class");
        String jar = ".jar!/";
        if (name.toLowerCase().contains(jar)) {
            blReturn = true;
        }
        return blReturn;
    }
}
