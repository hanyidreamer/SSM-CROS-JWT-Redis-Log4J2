package com.demo.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密工具类
 *
 * @author chensi
 * @version 2016-6-1 下午3:23:40
 */
public class MD5Util {

    /**
     * 全局数组
     **/
    private final static String[] STR_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * 返回形式为数字跟字符串
     *
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return STR_DIGITS[iD1] + STR_DIGITS[iD2];
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * MD5加密
     *
     * @param str 待加密的字符串
     * @return
     */
    public static String getMD5Code(String str) {
        String result = null;
        try {
            result = str;
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byteToString(md.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * MD5加密
     *
     * @param str       待加密的字符串
     * @param lowerCase 大小写
     * @return 加密之后的值
     */
    public static String getmd5code(String str, boolean lowerCase) {
        String result = null;
        try {
            result = str;
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byteToString(md.digest(str.getBytes()));
            if (lowerCase) {
                result = result.toLowerCase();
            }
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static byte[] createChecksum(String filename) throws Exception {
        //将流类型字符串转换为String类型字符串
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            //从文件读到buffer，最多装满buffer
            numRead = fis.read(buffer);
            if (numRead > 0) {
                //用读到的字节进行MD5的计算，第二个参数是偏移量
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        StringBuilder result = new StringBuilder();

        for (byte aB : b) {
            //加0x100是因为有的b[i]的十六进制只有1位
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
