package com.demo.handler;


import com.demo.common.Global;
import com.demo.utils.NetworkUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tuzhengsong
 */
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {


        String localhost = Global.getConfig("localhost");
        String ip;
        try {
            ip = NetworkUtils.getIpAddress(request);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //判断是本服务器访问还是外部访问
        if (ip.equals(localhost)) {
            if (request.getServerName().equals(localhost)) {
                if (request.getHeader(HttpHeaders.ORIGIN) != null) {
                    //跨域白名单
                    String allow = Global.getConfig("cros.allow");
                    response.addHeader("Access-Control-Allow-Origin", allow);
                    response.addHeader("Access-Control-Allow-Methods", "GET,POST, PATCH, PUT");
                    response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
                    response.addHeader("Access-Control-Max-Age", "3600");
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            //不是本服务器访问，放行
            return true;
        }
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
    }

}
