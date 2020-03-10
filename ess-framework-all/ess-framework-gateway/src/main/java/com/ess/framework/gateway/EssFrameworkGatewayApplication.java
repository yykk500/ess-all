package com.ess.framework.gateway;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 233 网关项目启动类
 * 
 * @author Luozelin
 *
 */
@EnableDiscoveryClient
@Configuration
@SpringBootApplication(scanBasePackages = { "com.ess.framework.gateway.listener", "com.ess.framework.gateway.filter",
		"com.ess.framework.gateway.handler" })
public class EssFrameworkGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EssFrameworkGatewayApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> staticResourceRouter() {
		return RouterFunctions.resources("/*.ico", new ClassPathResource("/"));
	}
	

	@RabbitListener(queues = "goods-queue")
	private void receiveMsg(String msg) {
		System.out.println(msg);
	}

}
