package com.ess.example.goods.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 *
 */
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpBasicAuthorizeFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
}
