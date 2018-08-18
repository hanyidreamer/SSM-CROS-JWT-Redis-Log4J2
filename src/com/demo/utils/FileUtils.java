package com.demo.utils;//
//package com.demo.utils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//
//
///**
// * 文件操作工具类
// */
//
//public class FileUtils {
//    private static Logger logger = LogManager
//            .getLogger("FileUtils");
//    private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd/");
//    private static SimpleDateFormat sdfFile = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    private static ClassLoader getClassLoader() {
//        ClassLoader classLoader = Thread.currentThread()
//                .getContextClassLoader();
//        if (classLoader == null) {
//            classLoader = FileUtils.class.getClassLoader();
//        }
//        return classLoader;
//    }
//
//    public static boolean ensureFileDirs(String filePath) {
//        boolean blReturn = false;
//        if (StringUtils.isBlank(filePath)) {
//            return blReturn;
//        }
//        File file = new File(filePath);
//        return ensureFileDirs(file);
//    }
//
//    private static boolean ensureFileDirs(File file) {
//        boolean blReturn = false;
//        if (file == null) {
//            return blReturn;
//        }
//        try {
//            if (file.exists() && file.isDirectory()) {
//                //
//            } else {
//                file.mkdirs();
//            }
//            blReturn = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return blReturn;
//    }
//}
//
///**
// * 读取静态配置文件的属性 (此配置文件仅 load 一次, 适用于程序内不再修改的配置文件); 如果返回 null, 则读取不成功！
// *
// * @param fileName 文件名 (classes 目录下)
// * @return 读取动态配置文件的属性 (此配置文件可多次读取，适用于配置文件在程序中修改的情形); 如果返回 null, 则读取不成功！
// * @param fileName 文件名 (classpath 路径内)
// * <p>
// * 写配置文件
// * @param properties 新的配置属性
// * @param fileName   文件名 (classes 目录下)
// * <p>
// * 读取配置文件的内容 (此文件仅 load 一次, 适用于程序内不再修改的文件); 如果返回 null, 则读取不成功！
// * @param fileName 文件名 (classes 目录下)
// * @return 把文件读出形成字符串
// * @param fileName 文件名
// * @return 文件内容
// * <p>
// * 把文件读出形成字节流数组
// * @param fileName 带路径的文件名
// * @return 返回生成的字节流数据，不存在文件或者出错则返回 null
// * <p>
// * 把字节流数组存为指定的文件名
// * @param fileName 要存的文件名 (带路径)
// * @param arrByte  字节流数组
// * @return 成功返回 true, 否则返回 false
// * @param date 指定的时间
// * @return 返回 yyyyMMdd / 结构的目录字符串
// * <p>
// * 返回当前时间的字符串用作文件名
// * @param date 指定的时间
// * @return 返回字符串
// * <p>
// * 检查目录是否存在，不存在的如果可能的话就创建此目录 (可为多级目录)
// * @param filePath 目录名
// * @return 操作成功返回 true(即最终是有此目录), 否则返回 false
// * <p>
// * 删除指定的文件
// * @param filePath 文件路径
// * @return 成功删除就返回 true, 否则返回 false
// * <p>
// * 删除文件夹以及文件夹下的子目录与文件
// * @param file
// * <p>
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// * <p>
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// * <p>
// * 将磁盘中的文件读入内存中
// * @param sourceFilePath
// * @return 读取动态配置文件的属性 (此配置文件可多次读取，适用于配置文件在程序中修改的情形); 如果返回 null, 则读取不成功！
// * @param fileName 文件名 (classpath 路径内)
// * <p>
// * 写配置文件
// * @param properties 新的配置属性
// * @param fileName   文件名 (classes 目录下)
// * <p>
// * 读取配置文件的内容 (此文件仅 load 一次, 适用于程序内不再修改的文件); 如果返回 null, 则读取不成功！
// * @param fileName 文件名 (classes 目录下)
// * @return 把文件读出形成字符串
// * @param fileName 文件名
// * @return 文件内容
// * <p>
// * 把文件读出形成字节流数组
// * @param fileName 带路径的文件名
// * @return 返回生成的字节流数据，不存在文件或者出错则返回 null
// * <p>
// * 把字节流数组存为指定的文件名
// * @param fileName 要存的文件名 (带路径)
// * @param arrByte  字节流数组
// * @return 成功返回 true, 否则返回 false
// * @param date 指定的时间
// * @return 返回 yyyyMMdd / 结构的目录字符串
// * <p>
// * 返回当前时间的字符串用作文件名
// * @param date 指定的时间
// * @return 返回字符串
// * <p>
// * 检查目录是否存在，不存在的如果可能的话就创建此目录 (可为多级目录)
// * @param filePath 目录名
// * @return 操作成功返回 true(即最终是有此目录), 否则返回 false
// * <p>
// * 删除指定的文件
// * @param filePath 文件路径
// * @return 成功删除就返回 true, 否则返回 false
// * <p>
// * 删除文件夹以及文件夹下的子目录与文件
// * @param file
// * <p>
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// * <p>
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// * <p>
// * 将磁盘中的文件读入内存中
// * @param sourceFilePath
// * @return 读取动态配置文件的属性 (此配置文件可多次读取，适用于配置文件在程序中修改的情形); 如果返回 null, 则读取不成功！
// * @param fileName 文件名 (classpath 路径内)
// * <p>
// * 写配置文件
// * @param properties 新的配置属性
// * @param fileName   文件名 (classes 目录下)
// * <p>
// * 读取配置文件的内容 (此文件仅 load 一次, 适用于程序内不再修改的文件); 如果返回 null, 则读取不成功！
// * @param fileName 文件名 (classes 目录下)
// * @return 把文件读出形成字符串
// * @param fileName 文件名
// * @return 文件内容
// * <p>
// * 把文件读出形成字节流数组
// * @param fileName 带路径的文件名
// * @return 返回生成的字节流数据，不存在文件或者出错则返回 null
// * <p>
// * 把字节流数组存为指定的文件名
// * @param fileName 要存的文件名 (带路径)
// * @param arrByte  字节流数组
// * @return 成功返回 true, 否则返回 false
// * @param date 指定的时间
// * @return 返回 yyyyMMdd / 结构的目录字符串
// * <p>
// * 返回当前时间的字符串用作文件名
// * @param date 指定的时间
// * @return 返回字符串
// * <p>
// * 检查目录是否存在，不存在的如果可能的话就创建此目录 (可为多级目录)
// * @param filePath 目录名
// * @return 操作成功返回 true(即最终是有此目录), 否则返回 false
// * <p>
// * 删除指定的文件
// * @param filePath 文件路径
// * @return 成功删除就返回 true, 否则返回 false
// * <p>
// * 删除文件夹以及文件夹下的子目录与文件
// * @param file
// * <p>
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// * <p>
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// * <p>
// * 将磁盘中的文件读入内存中
// * @param sourceFilePath
// * @return
// *//*
//
//    public static Properties loadStaticProperties(String fileName) {
//        if (StringUtils.isBlank(fileName)) {
//            return null;
//        }
//        logger.debug("loadStaticProperties()-- start,[" + fileName + "]");
//        Properties properties = null;
//        try {
//            ClassLoader classLoader = getClassLoader();
//            InputStream is = classLoader.getResourceAsStream(fileName);
//            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//
//            Properties temp = new Properties();
//            temp.load(isr);
//            properties = new Properties();
//            properties.putAll(temp);
//            temp.clear();
//            temp = null;
//            is.close();
//            is = null;
//            isr.close();
//            isr = null;
//        } catch (Exception e) {
//            logger.error("loadStaticProperties()[" + fileName + "]:" + e);
//        }
//        return properties;
//    }
//
//
//    private static String decodeFilePath(String root) {
//        String http = "http:";
//        if (root != null && !root.toLowerCase().contains(http)
//                && root.contains("%20")) {
//            root = root.replace("%20", " ");
//        }
//        return root;
//    }
//
//
////    public static boolean createSubFolder(String pathName) {
////        boolean blReturn = false;
////        if (pathName == null || "".equals(pathName.trim())) {
////            return blReturn;
////        }
////        String a = "%20";
////        if (pathName.contains(a)) {
////            pathName = pathName.replace("%20", " ");
////        }
////        pathName = pathName.replaceAll("\\\\", "/");
////        String[] dirs = pathName.split("/");
////        String tempDir = "";
////        for (String dir : dirs) {
////            tempDir = tempDir + "/" + dir;
////            File file = new File(tempDir);
////            if (!file.exists()) {
////                file.mkdirs();
////            }
////        }
////        blReturn = true;
////        return blReturn;
////    }
//
//
//    */
///**
// * 读取动态配置文件的属性 (此配置文件可多次读取，适用于配置文件在程序中修改的情形); 如果返回 null, 则读取不成功！
// *
// * @param fileName 文件名 (classpath 路径内)
// *//*
//
////    public static Properties loadVarProperties(String fileName) {
////        logger.debug("loadVarProperties()-- start,[" + fileName + "]");
////        if (StringUtils.isBlank(fileName)) {
////            return null;
////        }
////        Properties properties = null;
////        try {
////            String root = getConfigRoot();
////            String file = root + fileName;
////            logger.debug("loadVarProperties()-- full path name=[" + file + "]");
////            InputStream is = new FileInputStream(file);
////            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
////            Properties temp = new Properties();
////            temp.load(isr);
////            properties = new Properties();
////            properties.putAll(temp);
////            temp.clear();
////            temp = null;
////            is.close();
////            is = null;
////            isr.close();
////            isr = null;
////        } catch (Exception e) {
////            logger.error("loadVarProperties()[" + fileName + "]:" + e);
////        }
////        return properties;
////    }
//
//    */
///**
// * 写配置文件
// *
// * @param properties 新的配置属性
// * @param fileName   文件名 (classes 目录下)
// *//*
//
////    public static boolean writeConfig(Properties properties, String fileName) {
////        if (properties == null || StringUtils.isBlank(fileName)) {
////            return false;
////        }
////        try {
////            String root = getConfigRoot();
////            String file = root + fileName;
////            logger.debug("writeConfig() -- fileName=[" + file + "]");
////            OutputStream out = new FileOutputStream(file);
////            properties.store(out, "");
////            out.close();
////        } catch (Exception e) {
////            logger.error("writeConfig()[" + fileName + "]:" + e);
////            return false;
////        }
////        return true;
////    }
//    public static String getWebRoot() {
//        String temp = getConfigRoot();
//        String s = "/WEB-INF/classes/";
//        int index = temp.indexOf(s);
//        if (index != -1) {
//            temp = temp.substring(0, index + 1);// 最后带 /
//        }
//        return temp;
//    }
//
//    private static String getConfigRoot() {
//        String root = null;
//        if (isFromJar()) {
//            logger.debug("getConfigRoot() -- is from JAR file.");
//            root = FileUtils.class.getProtectionDomain().getCodeSource()
//                    .getLocation().getPath();
//
//            String lib = "/lib/";
//
//            if (root != null && root.contains(lib)) {
//                int index = root.indexOf(lib);
//                root = root.substring(0, index) + "/classes/";
//            }
//        } else {
//            // for web context
//            try {
//                root = FileUtils.class.getResource("").getPath();
//                logger.debug("getConfigRoot() -- first,root=[" + root + "]");
//                String s = "/WEB-INF/classes/";
//                int index = root.indexOf(s);
//                if (index != -1) {
//                    root = root.substring(0, index) + s;
//                }
//            } catch (Exception e) {
//                logger.warn("getConfigRoot() -- in error:" + e);
//            }
//        }
//
//        root = FileUtils.decodeFilePath(root);
//        return root;
//
//    }
//
//    private static boolean isFromJar() {
//        boolean blReturn = false;
//        String name = "" + FileUtils.class.getResource("FileUtils.class");
//        String jar = ".jar!/";
//        if (name.toLowerCase().contains(jar)) {
//            blReturn = true;
//        }
//        return blReturn;
//    }
//
////    public static String txtFromFile(String fullFileName, String charsetName) {
////        String txt = "";
////        try {
////            File file = new File(fullFileName);
////            InputStream is = new FileInputStream(file);
////            BufferedReader bf = new BufferedReader(new InputStreamReader(is,
////                    charsetName));
////            String line = null;
////            txt = "";
////            while ((line = bf.readLine()) != null) {
////                txt += line;
////            }
////            bf.close();
////            file = null;
////        } catch (Exception e) {
////            logger.error("txtFromFile() -- " + e);
////        }
////        return txt;
////    }
//
//    */
///**
// * 读取配置文件的内容 (此文件仅 load 一次, 适用于程序内不再修改的文件); 如果返回 null, 则读取不成功！
// *
// * @param fileName 文件名 (classes 目录下)
// * @return
// *//*
//
////    public static String readConfigFile(String fileName) {
////        byte[] arrByte = null;
////        String strReturn = null;
////        try {
////            ClassLoader classLoader = getClassLoader();
////            InputStream is = classLoader.getResourceAsStream(fileName);
////            arrByte = new byte[is.available()];
////            is.read(arrByte);
////            is.close();
////        } catch (Exception e) {
////            logger.error("readConfigFile() -- fileName=[" + fileName
////                    + "],exception:" + e);
////            return strReturn;
////        }
////        if (arrByte != null) {
////            try {
////                strReturn = new String(arrByte, "UTF-8");
////            } catch (UnsupportedEncodingException e) {
////            }
////        }
////        return strReturn;
////    }
//
//    */
///**
// * 把文件读出形成字符串
// *
// * @param fileName 文件名
// * @return 文件内容
// *//*
//
////    public static String readFile(String fileName) {
////        String strReturn = null;
////        byte[] arrByte = FileUtils.fileToBytes(fileName);
////        if (arrByte != null) {
////            strReturn = new String(arrByte);
////        }
////        return strReturn;
////    }
//
//    */
///**
// * 把文件读出形成字节流数组
// *
// * @param fileName 带路径的文件名
// * @return 返回生成的字节流数据，不存在文件或者出错则返回 null
// *//*
//
////    public static byte[] fileToBytes(String fileName) {
////        if (fileName == null) {
////            return null;
////        }
////        File file = new File(fileName);
////        if (!file.exists() || !file.isFile()) {
////            return null;
////        }
////        byte[] arrByte = null;
////        try {
////            arrByte = new byte[(int) file.length()];
////            FileInputStream is = new FileInputStream(file);
////            is.read(arrByte);
////            is.close();
////        } catch (Exception e) {
////        }
////        return arrByte;
////    }
//
//    */
///**
// * 把字节流数组存为指定的文件名
// *
// * @param fileName 要存的文件名 (带路径)
// * @param arrByte  字节流数组
// * @return 成功返回 true, 否则返回 false
// *//*
//
////    public static boolean bytesToFile(String fileName, byte[] arrByte) {
////        boolean blReturn = false;
////        if (fileName == null || arrByte == null || arrByte.length == 0) {
////            return blReturn;
////        }
////        File file = new File(fileName);
////        try {
////            FileOutputStream os = new FileOutputStream(file, false);
////            os.write(arrByte);
////            os.close();
////            blReturn = true;
////        } catch (Exception e) {
////        }
////        return blReturn;
////    }
//
//    */
///**
// * @param date 指定的时间
// * @return 返回 yyyyMMdd / 结构的目录字符串
// *//*
//
////    public static String dateDir(Date date) {
////        return sdfYMD.format(date);
////    }
//
//    */
///**
// * 返回当前时间的字符串用作文件名
// *
// * @param date 指定的时间
// * @return 返回字符串
// *//*
//
////    public static String randomFileString(Date date) {
////        String strReturn = null;
////        long times = System.currentTimeMillis();
////        strReturn = sdfFile.format(date);
////        strReturn += ("_" + times);
////        return strReturn;
////    }
//
//    */
///**
// * 检查目录是否存在，不存在的如果可能的话就创建此目录 (可为多级目录)
// *
// * @param filePath 目录名
// * @return 操作成功返回 true(即最终是有此目录), 否则返回 false
// *//*
//
//
//
// */
///**
// * 删除指定的文件
// *
// * @param filePath 文件路径
// * @return 成功删除就返回 true, 否则返回 false
// *//*
//
////    public static boolean deleteFile(String filePath) {
////        boolean blReturn = false;
////        if (filePath == null || "".equals(filePath.trim())) {
////            return blReturn;
////        }
////        try {
////            File file = new File(filePath);
////            if (file.exists() && !file.isDirectory()) {
////                blReturn = file.delete();
////            }
////        } catch (Exception e) {
////        }
////        return blReturn;
////    }
//
//    */
///**
// * 删除文件夹以及文件夹下的子目录与文件
// *
// * @param file
// *//*
//
////    public static boolean deleteAllFile(File file) {
////        boolean blReturn = false;
////        try {
////            if (file.exists()) {
////                if (file.isFile()) {
////                    file.delete();
////                } else if (file.isDirectory()) {
////                    File files[] = file.listFiles();
////                    for (int i = 0; i < files.length; i++) {
////                        FileUtils.deleteAllFile(files[i]);
////                    }
////                }
////                if (file.exists()) {
////                    file.delete();
////                }
////            }
////        } catch (Exception e) {
////        }
////        return blReturn;
////    }
//
//    */
///**
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// *
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// *//*
//
////    public static Map<String, String> getTxtContent(String sourceFolderPath) {
////        if (null == sourceFolderPath || "".equals(sourceFolderPath)) {
////            return null;
////        }
////        Map<String, String> values = new HashMap<String, String>();
////        File sourceFile = new File(sourceFolderPath);
////        File[] textFiles = null;
////        try {
////            if (null != sourceFile) {
////                textFiles = sourceFile.listFiles();
////            }
////            for (int i = 0; textFiles != null && i < textFiles.length; i++) {
////                if (textFiles[i].isFile()
////                        && textFiles[i].getName().endsWith(".txt")) {
////                    String name = textFiles[i].getName();
////                    String content = getContentFromFile(textFiles[i]
////                            .getCanonicalPath());
////                    values.put(name, content);
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return values;
////    }
//
//    */
///**
// * 将磁盘上的文件夹中所有的 txt 文件读出来，并且把文件名和文件内容封装到一个 map 中
// *
// * @param sourceFolderPath 文件夹的路径
// * @return Map <名称，内容>
// *//*
//
////    public static List<Map<String, String>> getTxtContentForIndex(
////            String sourceFolderPath) {
////        if (null == sourceFolderPath || "".equals(sourceFolderPath)) {
////            return null;
////        }
////        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
////        Map<String, String> map = null;
////        File sourceFile = new File(sourceFolderPath);
////        File[] textFiles = null;
////        try {
////            if (null != sourceFile) {
////                textFiles = sourceFile.listFiles();
////            }
////            for (int i = 0; textFiles != null && i < textFiles.length; i++) {
////                if (textFiles[i].isFile()
////                        && textFiles[i].getName().endsWith(".txt")) {
////                    String name = textFiles[i].getName();
////                    String content = getContentFromFile(textFiles[i]
////                            .getCanonicalPath());
////                    // System.out.println(name+"&&&&&&&&"+content);
////                    map = new HashMap<String, String>();
////                    map.put("name", name);
////                    map.put("content", content);
////                }
////                list.add(map);
////                map = null;
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return list;
////    }
//
//    */
///**
// * 将磁盘中的文件读入内存中
// *
// * @param sourceFilePath
// * @return
// *//*
//
////    public static String getContentFromFile(String sourceFilePath) {
////        String result = "";
////        File inputFile = null;
////        FileReader fr = null;
////        BufferedReader br = null;
////        try {
////            inputFile = new File(sourceFilePath);
////            if (null != inputFile && inputFile.exists()) {
////                fr = new FileReader(inputFile);
////                // br = new BufferedReader(fr);
////                br = new BufferedReader(new InputStreamReader(
////                        new FileInputStream(sourceFilePath), "GBK"));
////                String tempString = "";
////                while ((tempString = br.readLine()) != null) {
////                    result += tempString + "\r\n";
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                if (null != br) {
////                    br.close();
////                }
////                if (null != fr) {
////                    fr.close();
////                }
////            } catch (Exception e2) {
////                e2.printStackTrace();
////            }
////        }
////        return result;
////    }
//
////    public static String[] getMapKey(Map<String, Float> map) {
////        String[] getFieldName = null;
////        try {
////            Set<String> set = map.keySet();
////            Iterator<String> iter = set.iterator();
////            List<String> fields = new ArrayList<String>();
////            while (iter.hasNext()) {
////                fields.add(iter.next());
////            }
////            getFieldName = new String[fields.size()];
////            for (int i = 0; i < fields.size(); i++) {
////                getFieldName[i] = fields.get(i);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return getFieldName;
////    }
//
//
//    // 判断文件的编码格式
////    public static String getFileCharset(File file) {
////        String charset = null;
////        FileInputStream fis = null;
////        BufferedInputStream bin = null;
////        try {
////            fis = new FileInputStream(file);
////            bin = new BufferedInputStream(fis);
////            int p = (bin.read() << 8) + bin.read();
////
////            switch (p) {
////                case 0xefbb:
////                    charset = "UTF-8";
////                    break;
////                case 0xfffe:
////                    charset = "Unicode";
////                    break;
////                case 0xfeff:
////                    charset = "UTF-16BE";
////                    break;
////                default:
////                    charset = "GBK";
////            }
////            bin.close();
////            fis.close();
////        } catch (FileNotFoundException e) {
////        } catch (IOException e) {
////        } finally {
////            if (bin != null) {
////                try {
////                    bin.close();
////                } catch (Exception e) {
////                }
////            }
////            if (fis != null) {
////                try {
////                    fis.close();
////                } catch (Exception e) {
////                }
////            }
////        }
////        return charset;
////    }
//}
//*/
