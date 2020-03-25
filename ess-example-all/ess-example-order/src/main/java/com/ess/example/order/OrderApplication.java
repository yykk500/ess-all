package com.ess.example.order;

import com.ess.framework.boot.config.FrameworkBootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.ess.framework.boot.gloabl.BootWebConfigurer;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ess.example.api")
@ComponentScan(basePackages = {"com.ess.example.order.web","com.ess.example.api.fallback"})
public class OrderApplication extends FrameworkBootConfig {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
