package com.ess.framework.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;
import com.ess.framework.commons.response.ResponseMap;

import reactor.core.publisher.Mono;

/**
 * 黑名单验证.
 * @author Luozelin
 *
 */
@Component
public class BlackIPCheckedFilter implements GlobalFilter, Ordered {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public int getOrder() {
		return 4;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String clientIP = getIPAddress(exchange.getRequest());
		logger.info("IP黑名单验证 当前客户端请求IP:{}",clientIP);
		if(!checkIP(clientIP)) {
			logger.info("IP黑名单非法用户-当前客户端请求IP:{}",clientIP);
			ServerHttpResponse response = exchange.getResponse();
			// 构造返回错误信息
			ResponseMap responseMap = ResponseMap.failMap(HttpStatus.UNAUTHORIZED.value(),"IP禁止访问.");
			
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
			DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(responseMap));
			return exchange.getResponse().writeWith(Mono.just(buffer));
		}
		return chain.filter(exchange);
	}
	
	private static boolean checkIP(String clientIP) {
		return true;
	}

	
	private static String getIPAddress(ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		String ip = null;
		// X-Forwarded-For：Squid 服务代理
		String ipAddresses = headers.getFirst("X-Forwarded-For");
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// Proxy-Client-IP：apache 服务代理
			ipAddresses = headers.getFirst("Proxy-Client-IP");
		}
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// WL-Proxy-Client-IP：weblogic 服务代理
			ipAddresses = headers.getFirst("WL-Proxy-Client-IP");
		}
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// HTTP_CLIENT_IP：有些代理服务器
			ipAddresses = headers.getFirst("HTTP_CLIENT_IP");
		}
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// X-Real-IP：nginx服务代理
			ipAddresses = headers.getFirst("X-Real-IP");
		}

		// 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
		if (ipAddresses != null && ipAddresses.length() != 0) {
			ip = ipAddresses.split(",")[0];
		}

		// 还是不能获取到，最后再通过request.getRemoteAddr();获取
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//			ip = request.getRemoteAddress()headers;
//		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			return "127.0.0.1";
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}
