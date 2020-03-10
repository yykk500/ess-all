package com.ess.framework.gateway.handler;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

@Configuration
@EnableConfigurationProperties({ ServerProperties.class, ResourceProperties.class })
public class GatewayErrorHandlerConfig {

	private final ServerProperties serverProperties;

	private final ApplicationContext applicationContext;

	private final ResourceProperties resourceProperties;

	private final List<ViewResolver> viewResolvers;

	private final ServerCodecConfigurer serverCodecConfigurer;

	public GatewayErrorHandlerConfig(ServerProperties serverProperties, ResourceProperties resourceProperties,
			ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer,
			ApplicationContext applicationContext) {
		this.serverProperties = serverProperties;
		this.applicationContext = applicationContext;
		this.resourceProperties = resourceProperties;
		this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
		this.serverCodecConfigurer = serverCodecConfigurer;
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes) {
		GatewayErrorHandler exceptionHandler = new GatewayErrorHandler(errorAttributes, this.resourceProperties,
				this.serverProperties.getError(), this.applicationContext);
		exceptionHandler.setViewResolvers(this.viewResolvers);
		exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
		exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
		return exceptionHandler;
	}

	
//
//	@Bean
//	@Order(-1)
//	public GlobalFilter firstFilter() {
//		return (exchange, chain) -> {
//			logger.info("first pre filter...");
//			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//				logger.info("third post filter...");
//			}));
//		};
//	}
//	
//	
//	@Bean
//	@Order(0)
//	public GlobalFilter secondFilter() {
//		return (exchange, chain) -> {
//			logger.info("second pre filter...");
//			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//				logger.info("second  post filter...");
//			}));
//		};
//	}
//	
//	@Bean
//	@Order(3)
//	public GlobalFilter thirdFilter() {
//		return (exchange, chain) -> {
//			logger.info("thrid pre filter...");
//			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//				logger.info("first  post filter...");
//			}));
//		};
//	}
}
