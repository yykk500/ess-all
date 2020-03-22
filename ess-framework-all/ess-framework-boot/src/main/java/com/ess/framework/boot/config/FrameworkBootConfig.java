package com.ess.framework.boot.config;


import com.ess.framework.api.response.ApiResponse;
import com.ess.framework.boot.gloabl.BootWebConfigurer;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@EnableSwagger2Doc
@ComponentScan({"com.ess.framework.boot.gloabl","com.ess.framework.boot.asynctask"})
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class FrameworkBootConfig extends BootWebConfigurer {

}
