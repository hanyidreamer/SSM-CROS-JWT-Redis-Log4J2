package com.demo.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 全局异常处理类
 *
 * @author tuzhengsong
 */
public class MyExceptionHandler extends SimpleMappingExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        // TODO uploadify有时会触发这个异常，待解决
        if (exception instanceof IOException) {
            return new ModelAndView();
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if (body == null) {
            logger.error("************全局异常处理器捕捉到异常: " + exception.toString());
            return new ModelAndView("redirect:WEB-INF/view/error.jsp");
        }
        // 设置状态码,注意这里不能设置成500，设成500JQuery不会出错误提示
        // 并且不会有任何反应
        response.setStatus(HttpStatus.OK.value());
        // 设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 避免乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw, true));
            PrintWriter writer = response.getWriter();
            logger.error("************全局异常处理器捕捉到异常:" + sw.toString());
            Map<String, Object> errorJson = new HashMap<String, Object>();
            errorJson.put("code", 40001);
            errorJson.put("msg", "系统繁忙，请稍后重试");
            errorJson.put("data", null);
            String str = JSONObject.toJSONString(errorJson, SerializerFeature.WriteMapNullValue);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            logger.error("************输出json出错" + "************");
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
