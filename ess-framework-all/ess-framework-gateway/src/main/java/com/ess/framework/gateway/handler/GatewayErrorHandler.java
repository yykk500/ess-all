package com.ess.framework.gateway.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ess.framework.commons.response.ResponseMap;

/**
 * 网关异常统一处理。
 * 
 * @author Luozelin
 *
 */
public class GatewayErrorHandler extends DefaultErrorWebExceptionHandler {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public GatewayErrorHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}

	@Override
	protected int getHttpStatus(Map<String, Object> errorAttributes) {
		int statusCode = (Integer) errorAttributes.get("code");
		return statusCode;
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	private static final String DEFAULT_BLOCK_MSG_PREFIX = "Blocked by Sentinel: ";

	@Override
	protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		int code = 500;
		String msg = "请求服务异常,请稍后再试.";
		try {
			Throwable ex = super.getError(request);
			
			logger.info("网关 是否sentinel 异常 :{}",BlockException.isBlockException(ex));
			logger.info("网关异常 :",ex);
			if(ex instanceof NotFoundException) {
				code = 404;
				msg = "请求路径不存在,请检查地址是否在正确.";
			}
			  // This exception handler only handles rejection by Sentinel.
	        if (!BlockException.isBlockException(ex)) {
	        	return ResponseMap.failMap(code,msg);
	        }
	        // 处理Sentinel异常
	        code = HttpStatus.TOO_MANY_REQUESTS.value();
	        msg = DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName();
			return ResponseMap.failMap(code,msg);
		}finally {
		}
	}

}
