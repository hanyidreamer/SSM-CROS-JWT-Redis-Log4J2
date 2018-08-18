package com.demo.utils;

import com.alibaba.fastjson.JSON;
import com.demo.jwt.jwt.Jwt;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

/**
 * The type Token utils.
 *
 * @author tuzhengsong
 */
public class TokenUtils {
    /**
     * 从HttpServletRequest中提取token
     * 从token中提取uid
     *
     * @param request the request
     * @return the uid
     */
    public static int getUid(HttpServletRequest request) {
        String token = null;
        if (request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            token = request.getHeader(HttpHeaders.AUTHORIZATION);
        }
        Map<String, Object> resultMap;
        try {
            resultMap = Jwt.validToken(token);
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(String.valueOf(resultMap.get("data")));
            String uidStr = jsonObject.getString("uid");
            return Integer.parseInt(uidStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
