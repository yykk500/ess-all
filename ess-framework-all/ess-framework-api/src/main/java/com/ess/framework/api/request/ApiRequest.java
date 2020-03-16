package com.ess.framework.api.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

/**
 * Api请求参数基类
 * 
 * @author Luozelin
 *
 */
@Setter
@Getter
public abstract class ApiRequest {
	
	/**
	 * 请求sid，每个请求为唯一标识码
	 */
	@ApiParam("sid请求唯一id")
	private String sid;

	/**
	 * 请求token 用于接口鉴权验证
	 */
	@ApiParam("token鉴权")
	private String token;
}
