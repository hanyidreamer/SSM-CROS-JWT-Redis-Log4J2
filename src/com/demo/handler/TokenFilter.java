package com.demo.handler;

import java.io.*;
import java.text.ParseException;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.demo.common.Global;
import com.demo.jwt.jwt.Jwt;
import com.demo.jwt.jwt.TokenState;
import com.demo.redis.core.RedisClient;
import com.demo.redis.core.RedisClientFactory;
import org.springframework.http.HttpHeaders;

/**
 * @author tuzhengsong
 */
public class TokenFilter implements Filter {
    private static final String STATE = "state";
    //查看tokenBackList是都存在，要做，不存在的话要新建

    private static final String TOKEN_BACK_LIST = "tokenBackList";
    private RedisClient redisClient = RedisClientFactory.getClient();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);


        String token = null;
        if (request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            token = request.getHeader(HttpHeaders.AUTHORIZATION);
        }

        String reguri = Global.getConfig("reguri");
        String loginuri = Global.getConfig("loginuri");
        String verqruri = Global.getConfig("verqruri");
        String downuri = Global.getConfig("downuri");
        String indexuri = Global.getConfig("indexuri");
        String erroruri = Global.getConfig("erroruri");
        String resuri = Global.getConfig("resuri");
        boolean regFlag = request.getRequestURI().equals(reguri);
        boolean loginFlag = request.getRequestURI().equals(loginuri);
        boolean verQrFlag = request.getRequestURI().equals(verqruri);
        boolean downFlag = request.getRequestURI().equals(downuri);
        boolean indexFlag = request.getRequestURI().equals(indexuri);
        boolean errorFlag = request.getRequestURI().equals(erroruri);
        boolean staresFlag = request.getRequestURI().startsWith(resuri);

        if (indexFlag) {
            //首先校验token是否有值，和是否是登陆，都不是，进入欢饮页面
            String index = Global.getConfig("index");
            request.getRequestDispatcher(index).forward(request, response);
            return;
        } else if (errorFlag) {
            String error = Global.getConfig("error");
            request.getRequestDispatcher(error).forward(request, response);
            return;
        } else if (loginFlag) {
            //说明是首次登陆，没有token
            String login = Global.getConfig("loginuri").substring("/demo".length());
            request.getRequestDispatcher(login).forward(request, response);
            return;
        } else if (regFlag) {
            //如果是注册
            String reg = Global.getConfig("reguri").substring("/demo".length());
            request.getRequestDispatcher(reg).forward(request, response);
            return;
        } else if (verQrFlag) {
            //如果是注册
            String verqr = Global.getConfig("verqruri").substring("/demo".length());
            request.getRequestDispatcher(verqr).forward(request, response);
            return;
        } else if (downFlag) {
            String down = Global.getConfig("downuri").substring("/demo".length());
            request.getRequestDispatcher(down).forward(request, response);
            return;
        } else if (staresFlag) {
            filterChain.doFilter(request, response);
            return;
        } else {
            Map<String, Object> resultMap;
            try {

                if (token != null) {
                    resultMap = Jwt.validToken(token);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is null");
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                //如果不在宽限期，直接踢出
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is fail");
                return;
            }
            boolean tokenFlag = resultMap.get("state").equals(TokenState.VALID.toString());

            if (tokenFlag) {
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(String.valueOf(resultMap.get("data")));

                //查看是否需要刷新token
                long ext = jsonObject.getLong("ext");
                //查看是否在刷新期内
                boolean refFlag = (ext - System.currentTimeMillis()) <= Integer.parseInt(Global.getConfig("tokenref"));

                if (refFlag) {
                    //查看旧token是否存在
                    boolean isOldTokenExist = redisClient.hasKey(token);
                    if (isOldTokenExist) {
                        String existReToken = redisClient.get(token);
                        response.setHeader("Authorization", existReToken);
                    } else {
                        //如果旧token不存在，说明要存储刷新的token
                        String uid = jsonObject.getString("uid");
                        String refreshToken = Jwt.createToken(uid);
                        redisClient.setnx(token, refreshToken);
                        //指定时间生效
                        redisClient.pexpireAt(token, ext + Integer.parseInt(Global.getConfig("allowance")));
                        response.setHeader("Authorization", refreshToken);
                    }
                }
            } else {
                //失效
                if (resultMap.get(STATE).equals(TokenState.EXPIRED.toString())) {
                    //查看token是都在黑名单中
                    boolean isInTokenBackList = redisClient.sismember(TOKEN_BACK_LIST, token);
                    if (isInTokenBackList) {
                        if (redisClient.hasKey(token)) {
                            System.out.println("进入宽限期");
                            String existReToken = redisClient.get(token);
                            response.setHeader("Authorization", existReToken);
                        } else {
                            //如果不在宽限期，直接踢出
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is fail");
                            return;
                        }
                    } else {
                        //如果不在黑名单，说明旧token要加入黑名单
                        redisClient.sadd(TOKEN_BACK_LIST, token);
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is fail");
                        return;
                    }
                } else {
                    //如是是无效的token
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is fail");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {
    }
}
