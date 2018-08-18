package com.demo.jwt.wustrive.aesrsa.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;



/**
 * The type Aes.
 *
 * @author tuzhengsong
 */
public class AES {

    private static final int KEYLENGTH = 16;
    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return  byte [ ]
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        CheckUtils.notEmpty(data, "data");
        CheckUtils.notEmpty(key, "key");
        if (key.length != KEYLENGTH) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.AES_ALGORITHM);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, seckey);
            // 加密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return byte [ ]
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        CheckUtils.notEmpty(data, "data");
        CheckUtils.notEmpty(key, "key");
        int length = 16;

        if (key.length != length) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.AES_ALGORITHM);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, seckey);
            // 加密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }


    /**
     * Encrypt to base 64 string.
     *
     * @param data the data
     * @param key  the key
     * @return the string
     */
    public static String encryptToBase64(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING), key.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING));
            return new String(Base64.encode(valueByte));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encrypt fail!", e);
        }

    }

    /**
     * Decrypt from base 64 string.
     *
     * @param data the data
     * @param key  the key
     * @return the string
     */
    public static String decryptFromBase64(String data, String key) {
        try {
            byte[] originalData = Base64.decode(data.getBytes());
            byte[] valueByte = decrypt(originalData, key.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING));
            return new String(valueByte, ConfigureEncryptAndDecrypt.CHAR_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    /**
     * Encrypt with key base 64 string.
     *
     * @param data the data
     * @param key  the key
     * @return the string
     */
    public static String encryptWithKeyBase64(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING), Base64.decode(key.getBytes()));
            return new String(Base64.encode(valueByte));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * Decrypt with key base 64 string.
     *
     * @param data the data
     * @param key  the key
     * @return the string
     */
    public static String decryptWithKeyBase64(String data, String key) {
        try {
            byte[] originalData = Base64.decode(data.getBytes());
            byte[] valueByte = decrypt(originalData, Base64.decode(key.getBytes()));
            return new String(valueByte, ConfigureEncryptAndDecrypt.CHAR_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    /**
     * Genarate random key byte [ ].
     *
     * @return the byte [ ]
     */
    public static byte[] genarateRandomKey() {
        KeyGenerator keygen;
        try {
            keygen = KeyGenerator.getInstance(ConfigureEncryptAndDecrypt.AES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(" genarateRandomKey fail!", e);
        }
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        Key key = keygen.generateKey();
        return key.getEncoded();
    }

    /**
     * Genarate random key with base 64 string.
     *
     * @return the string
     */
    public static String genarateRandomKeyWithBase64() {
        return new String(Base64.encode(genarateRandomKey()));
    }

}
