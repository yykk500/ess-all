package com.ess.example.api.fallback;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.ess.example.api.GoodsFeignClient;
import com.ess.example.dto.goods.GoodsDetailDto;
import com.ess.example.dto.goods.GoodsDto;
import com.ess.framework.api.response.ApiResponse;

@Component
public class GoodsFeignClientFallback implements GoodsFeignClient {
	
	@Override
	public ApiResponse<GoodsDto> getGoods(@RequestParam Long goodsId) {
		return ApiResponse.failResp("远程服务异常...熔断错误...");
	}

	@Override
	public ApiResponse<String> existGoods(@RequestParam Long goodsId) {
		return ApiResponse.failResp("远程服务异常...熔断错误...");
	}

	@Override
	public ApiResponse<GoodsDetailDto> getGoodsInfo(Long goodsId) {
		return ApiResponse.failResp("远程服务异常...熔断错误...");
	}

	@Override
	public ApiResponse<List<GoodsDto>> goodsList() {
		return  ApiResponse.failResp("远程服务异常...熔断错误...");
	}
}
