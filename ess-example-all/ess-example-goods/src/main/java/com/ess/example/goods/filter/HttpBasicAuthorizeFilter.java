package com.ess.example.goods.filter;

import com.ess.framework.commons.exception.BusinessException;
import com.ess.framework.commons.jwt.JWTUtils;

import javax.servlet.*;
import java.io.IOException;

public class HttpBasicAuthorizeFilter implements Filter {
    private JWTUtils jwtUtils = JWTUtils.getInstance();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
//            String token =
//            jwtUtils.checkToken();
            chain.doFilter(request,response);
        }catch (BusinessException e){

        }catch (Exception e){

        }
    }
}
