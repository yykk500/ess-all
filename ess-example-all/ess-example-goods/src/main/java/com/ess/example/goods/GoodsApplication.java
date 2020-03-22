package com.ess.example.goods;


import com.ess.framework.boot.config.FrameworkBootConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ess.framework.boot.gloabl.BootWebConfigurer;

import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;

@EnableDiscoveryClient
@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
@MapperScan(basePackages = "com.ess.example.mapper")
@ComponentScan(basePackages = {"com.ess.example.goods.web","com.ess.**.service","com.ess.example.goods.schedule","com.ess.example.goods.aop"})
@EnableScheduling
public class GoodsApplication extends FrameworkBootConfig {

	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}
	 
	@Bean
	public Queue queue() {
		return new Queue("goods-queue",true);
	}
	
	@RabbitListener(queues = "goods-queue")
	private void receiveMsg(String msg) {
		System.out.println(msg);
	}
	
}
