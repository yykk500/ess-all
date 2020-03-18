package com.ess.framework.boot.interceptor;

import com.ess.framework.api.response.AbstractResponse;
import com.ess.framework.api.response.ApiPageResponse;
import com.ess.framework.api.response.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SIDInterceptor implements HandlerInterceptor{

    private final static String SID="sid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1...."+request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String sid = getSID(request);
        if(!StringUtils.hasText(sid)){
            return;
        }
        HandlerMethod  handlerMethod = (HandlerMethod)handler;
        Object resultObj = handlerMethod.getMethod().getReturnType();
        Object resultType = handlerMethod.getReturnType();
        Object genericReturnType = handlerMethod.getMethod().getGenericReturnType();
        Object annotatedReturnType = handlerMethod.getMethod().getAnnotatedReturnType();
        System.out.println(resultObj);
        if(resultObj instanceof AbstractResponse){
            AbstractResponse response1 = (AbstractResponse)resultObj;
            response1.setSid("SSS");
        }
        System.out.println("2...."+request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String sid = getSID(request);
        if(!StringUtils.hasText(sid)){
            return;
        }
        HandlerMethod  handlerMethod = (HandlerMethod)handler;
        Object resultObj = handlerMethod.getMethod().getReturnType();
        Object resultType = handlerMethod.getReturnType();
        Object genericReturnType = handlerMethod.getMethod().getGenericReturnType();
        Object annotatedReturnType = handlerMethod.getMethod().getAnnotatedReturnType();
        System.out.println(resultObj);

        if(resultObj instanceof AbstractResponse || resultObj.getClass().isAssignableFrom(AbstractResponse.class)){
            AbstractResponse response1 = (AbstractResponse)resultObj;
            response1.setSid(sid);
        }
        System.out.println("3...."+request.getRequestURI());
    }


    /**
     * 获取SID请求参数
     * @param request
     * @return
     */
    private static String getSID(HttpServletRequest request){
        String sid = request.getParameter(SID);
        if(StringUtils.hasText(sid)){
            return sid;
        }
        sid =request.getHeader(SID);
        if(StringUtils.hasText(sid)){
            return sid;
        }
        return sid;
    }

    public static void main(String[] args) {
        ApiPageResponse resultObj = ApiPageResponse.failResp();
        System.out.println(resultObj instanceof AbstractResponse);
        System.out.println(resultObj.getClass().isAssignableFrom(AbstractResponse.class));
    }
}
