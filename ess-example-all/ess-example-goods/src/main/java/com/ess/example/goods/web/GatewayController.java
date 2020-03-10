package com.ess.example.goods.web;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ess.example.api.GatewayFeignClient;
import com.ess.framework.api.response.ApiResponse;
import com.ess.framework.boot.gloabl.AbstractController;

@RestController
public class GatewayController extends AbstractController implements GatewayFeignClient{

	@Override
	public ApiResponse<String> checkAuth() {
		return null;
	}

	@Override
	public ApiResponse<String> header(@RequestHeader("request-x-id") String requestXid,@RequestHeader("request-x-name") String requestXname) {
		ApiResponse<String> response = ApiResponse.successResp("gateway filter addRequstHeader success..");
//		response.attr("requestXid", requestXid);
//		response.attr("requestXname", requestXname);
		return response;
	}

}
