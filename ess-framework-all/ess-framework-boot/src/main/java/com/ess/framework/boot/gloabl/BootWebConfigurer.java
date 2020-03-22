package com.ess.framework.boot.gloabl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ess.framework.boot.config.FrameworkBootConfig;
import com.ess.framework.boot.handler.SIDReturnValueHandler;
import com.ess.framework.boot.interceptor.ParamReturnInterceptor;
import com.ess.framework.boot.interceptor.SIDInterceptor;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Luozelin
 *
 */
@Configuration
public abstract class BootWebConfigurer implements WebMvcConfigurer {

    /** Logger used by this class. Available to subclasses. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private static final List<String> STATIC_RESOURCE = Arrays.asList("/","/**/*.html","/**/*.js","/**/*.ico","/**/*.jpg","/webjars/**","/cors","/swagger**");
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new SIDInterceptor()).addPathPatterns("/**").excludePathPatterns(STATIC_RESOURCE);
       registry.addInterceptor(new ParamReturnInterceptor()).addPathPatterns("/**").excludePathPatterns(STATIC_RESOURCE);
    }

    /**
     * 初始化SIDReturnValueHandler  对所有的响应返回SID字段
     */
    @PostConstruct
    public void initSidHandler() {
        logger.info("添加：addReturnValueHandlers");
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


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("静态资源初始化addResourceHandlers。");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/favicon.ico");
        registry.addResourceHandler("app.html").addResourceLocations("classpath:/");
        // Swagger-ui配置
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }





    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 移除Jackson消息处理器
        removeJacksonMessageConverters(converters);

        logger.info("添加Fastjson消息转换器。");
        //  1.定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter= new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat );
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        converters.add(fastConverter);
    }

    /**
     * 移除Jackson
     * @param converters
     */
    public void removeJacksonMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator convertersIter= converters.iterator();
        while (convertersIter.hasNext()){
            HttpMessageConverter item = (HttpMessageConverter)convertersIter.next();
            if(item instanceof MappingJackson2CborHttpMessageConverter){
                logger.info("删除MappingJackson2CborHttpMessageConverter。");
                converters.remove(item);
            }else if(item instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter ) {
                logger.info("删除MappingJackson2HttpMessageConverter消息转换器。");
                converters.remove(item);
            }
        }
    }

    /**
     * FastJson支持的MediaType
     * @return
     */
	private static  List<MediaType> getSupportedMediaTypes(){
		List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        return supportedMediaTypes;
	}

}
