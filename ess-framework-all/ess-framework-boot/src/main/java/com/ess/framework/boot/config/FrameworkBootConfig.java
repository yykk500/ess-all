package com.ess.framework.boot.config;


import com.ess.framework.boot.gloabl.BootWebConfigurer;
import com.ess.framework.boot.gloabl.SIDReturnValueHandler;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan({"com.ess.framework.boot.gloabl","com.ess.framework.boot.asynctask"})
public class FrameworkBootConfig extends BootWebConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;




    @Bean
    public HandlerMethodReturnValueHandler sidReturnValueHandler() {
        return new SIDReturnValueHandler();
    }
    @PostConstruct
    public void init() {
        System.out.println("添加。。。addReturnValueHandlers");
        final List<HandlerMethodReturnValueHandler> originalHandlers = new ArrayList<>(requestMappingHandlerAdapter.getReturnValueHandlers());
        final int deferredPos = obtainValueHandlerPosition(originalHandlers, DeferredResultMethodReturnValueHandler.class);
// Add our handler directly after the deferred handler.
        originalHandlers.add(deferredPos + 1, sidReturnValueHandler());
        requestMappingHandlerAdapter.setReturnValueHandlers(originalHandlers);
    }

    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }


}
