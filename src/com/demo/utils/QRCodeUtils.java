package com.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import it.sauronsoftware.base64.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * @author tuzhengsong
 */
public class QRCodeUtils {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private QRCodeUtils() {
    }

    /**
     * 根据内容生成二维码
     *
     * @param text 放入二维码的内容
     * @return 二维码的bsae64编码
     */
    public static JSONObject writeQrCodeContent(String text) {
        // 二维码图片宽度
        int width = 300;
        // 二维码图片高度
        int height = 300;
        // 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "GB2312");

        BitMatrix bitMatrix;
        JSONObject resJson = new JSONObject();

        String qrstr;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = toBufferedImage(bitMatrix);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "png", os);
            } catch (IOException e) {
                resJson.put("result", 30001);
                e.printStackTrace();
            }
            qrstr = new String(Base64.encode(os.toByteArray()));
            resJson.put("result", 1);
            resJson.put("qrstr", qrstr);
        } catch (WriterException e) {
            resJson.put("result", 30001);
            e.printStackTrace();
        }
        return resJson;
    }


    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }


//    /**
//     * 流方式解码
//     *
//     * @param input 图片数据流
//     * @return 二维码中的内容
//     */
//    public static String resolve(InputStream input) {
//
//        String charset = "utf-8";
//        BufferedImage image;
//        com.google.zxing.Result result;
//        try {
//            if (input == null) {
//                throw new Exception(" input is null");
//            }
//            image = ImageIO.read(input);
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            // 解码设置编码方式为：utf-8，
//            Hashtable<DecodeHintType, String> hints = new Hashtable<>();
//            hints.put(DecodeHintType.CHARACTER_SET, charset);
//            result = new MultiFormatReader().decode(bitmap, hints);
//            return result.getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * base64转inputStream
//     * @param base64 字符串
//     * @return
//     */
//    private static InputStream baseToInputStream(String base64){
//        ByteArrayInputStream stream = null;
//        try {
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] bytes1 = decoder.decodeBuffer(base64);
//            stream = new ByteArrayInputStream(bytes1);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return stream;
//    }

}
