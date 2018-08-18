package com.demo.utils;

/**
 * @author tuzhengsong
 */
public class AppVerUtils {


    public static void main(String[] args) {
        String version1 = "1.1.1";
        String version2 = "1.2.1";

        int result = compareVersion(version1, version2);
        System.out.println(result);
    }
    /**
     * app版本号比较工具
     * @param version1 版本号1
     * @param version2 版本号2
     * @return 比较结果
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }

        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");

        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;

        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }

        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }

            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }
}
