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
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan({"com.ess.framework.boot.gloabl","com.ess.framework.boot.asynctask"})
public class FrameworkBootConfig extends BootWebConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        System.out.println("添加。。。addReturnValueHandlers");
        final List<HandlerMethodReturnValueHandler> originalHandlers = new ArrayList<>(requestMappingHandlerAdapter.getReturnValueHandlers());
        final int deferredPos = obtainValueHandlerPosition(originalHandlers, DeferredResultMethodReturnValueHandler.class);
        SIDReturnValueHandler decorator = null;
        for(HandlerMethodReturnValueHandler handler : originalHandlers){
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                decorator = new SIDReturnValueHandler((RequestResponseBodyMethodProcessor) handler);
                break;
            }
        }
        originalHandlers.add(deferredPos + 1, decorator);
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
