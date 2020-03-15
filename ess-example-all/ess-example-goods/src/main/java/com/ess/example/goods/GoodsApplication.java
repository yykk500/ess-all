package com.ess.example.goods;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ess.framework.boot.gloabl.BootWebConfigurer;

import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.ess.example.mapper")
@ComponentScan(basePackages = {"com.ess.example.goods.web","com.ess.**.service"})
@EnableSwagger2Doc
public class GoodsApplication extends BootWebConfigurer{

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
