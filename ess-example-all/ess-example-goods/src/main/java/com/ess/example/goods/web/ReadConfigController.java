package com.ess.example.goods.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.ess.framework.boot.gloabl.AbstractController;
import com.ess.framework.commons.response.ResponseMap;

@RestController
@RefreshScope
public class ReadConfigController extends AbstractController {

	@Value("${jdbc.url:zzz}")
	private String jdbcUrl;
	
	@Value("${spring.rabbitmq.addresses:def-rabbitmq-url}")
	private String mqUrl;
	
	@Value("${cache.url}")
	private String cacheUrl;
	/**
	 * 
	 * @return
	 */
	@SentinelResource(value = "read-acos")
	@GetMapping("/read/nacos")
	public ResponseMap create() {
		ResponseMap responseMap =	ResponseMap.successMap();
		responseMap.put("jdbc.url", jdbcUrl);
		responseMap.put("mq.url", mqUrl);
		responseMap.put("cache.url", cacheUrl);
		return responseMap;
	}
	
	/**
	 * 
	 * @return
	 */
	@SentinelResource(value = "header")
	@GetMapping("/header")
	public ResponseMap header() {
		ResponseMap responseMap =	ResponseMap.successMap();
		responseMap.put("jdbc.url", jdbcUrl);
		responseMap.put("mq.url", mqUrl);
		responseMap.put("cache.url", cacheUrl);
		return responseMap;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/trace")
	public ResponseMap trace(HttpServletRequest request) {
		ResponseMap responseMap =	ResponseMap.successMap();
		String traceId =  request.getHeader("X-B3-TraceId");
		String spanId =  request.getHeader("X-B3-SpanId");
		String parentSpanId =  request.getHeader("X-B3-ParentSpanId");
		String sampled =  request.getHeader("X-B3-Sampled");
		String spanName =  request.getHeader("X-Span-Name");
		responseMap.attr("traceId", traceId);
		responseMap.attr("spanId", spanId);
		responseMap.attr("parentSpanId", parentSpanId);
		responseMap.attr("sampled", sampled);
		responseMap.attr("spanName", spanName);
		logger.info("trece:{}",JSON.toJSONString(responseMap));
		return responseMap;
	}
}
