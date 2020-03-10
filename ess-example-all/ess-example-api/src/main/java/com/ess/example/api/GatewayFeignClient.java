package com.ess.example.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ess.framework.api.ApiFeignClient;
import com.ess.framework.api.response.ApiResponse;

@FeignClient(name = "example-goods")
public interface GatewayFeignClient extends ApiFeignClient {

	/**
	 * @return
	 */
	@GetMapping("/checkAuth")
	public ApiResponse<String> checkAuth();
	
	/**
	 *  Return
	 * @return
	 */
	@GetMapping("/filter/header")
	public ApiResponse<String> header(@RequestHeader(value = "request-x-id",required = false) String requestXid,@RequestHeader(value = "request-x-name",required = false) String requestXName);
}
