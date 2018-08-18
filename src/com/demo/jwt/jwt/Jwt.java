package com.demo.jwt.jwt;

import com.demo.common.Global;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static com.demo.common.Constants.SECRET;


/**
 * @author tuzhengsong
 */
public class Jwt {

    private static final String EXT = "ext";

    private static final JWSHeader HEADER = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);


    public static Map<String, Object> validToken(String token) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>(2);
        try {

            JWSObject jwsObject;
            jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", TokenState.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                if (jsonOBj.containsKey(EXT)) {
                    long extTime = Long.valueOf(jsonOBj.get("ext").toString());
                    long curTime = System.currentTimeMillis();
                    // 过期了
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", TokenState.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonOBj);

            } else {
                // 校验失败
                resultMap.put("state", TokenState.INVALID.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // token格式不合法导致的异常
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }

    public static String createToken(String uid) {
        String tokenString = null;

        Map<String, Object> payload = new HashMap<>(3);

        int ext = Integer.parseInt(Global.getConfig("tokenext"));
        // 用户id
        payload.put("uid", uid);
        // 生成时间:当前
        payload.put("iat", System.currentTimeMillis());
        // 过期时间1小时
        payload.put("ext", System.currentTimeMillis() + ext);
        //  刷新时间


        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(HEADER, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString = jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }
}
