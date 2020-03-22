package com.ess.framework.boot.interceptor;

import com.ess.framework.commons.exception.BusinessException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SID 拦截器 ，验证SID是否为空
 */
public class SIDInterceptor implements HandlerInterceptor{

    private final static String SID="sid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sid = getSID(request);
        if(!StringUtils.hasText(sid)){
            throw new BusinessException(501,"SID参数不能为空.");
        }
        return true;
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
        sid = request.getHeader(SID);
        if(StringUtils.hasText(sid)){
            return sid;
        }
        return sid;
    }
}
