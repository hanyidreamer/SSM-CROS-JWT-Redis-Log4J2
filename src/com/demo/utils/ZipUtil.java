package com.demo.utils;

import com.demo.common.Global;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


import java.io.*;
import java.util.ArrayList;
import java.util.UUID;


public class ZipUtil {
    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcfile：源文件列表
     * @param zipfile：压缩后的文件
     */
    public static boolean zipFiles(ArrayList<File> srcfile, File zipfile) {
        boolean reFlag = false;
        byte[] buf = new byte[10240];
        ZipOutputStream out = null;
        FileInputStream in = null;
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            out = new ZipOutputStream(new FileOutputStream(zipfile));
            //注意此处编码设置
            out.setEncoding("utf-8");
            for (File aSrcfile : srcfile) {
                in = new FileInputStream(aSrcfile);
                out.putNextEntry(new ZipEntry(aSrcfile.getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
                in = null;

            }
            out.close();
            out = null;
            reFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return reFlag;
    }


    public static File getRes(ArrayList<File> files) throws IOException {
        String filename = UUID.randomUUID().toString().replace("-", "") + ".zip";
        String floder = Global.getConfig("sys.webPath");
        return new File(floder + File.separator + filename);
    }

}
