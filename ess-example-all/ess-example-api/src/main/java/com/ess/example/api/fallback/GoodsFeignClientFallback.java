package com.ess.example.api.fallback;

import java.util.List;

import com.ess.example.dto.goods.GetGoodsReq;
import com.ess.example.dto.goods.GoodsListReq;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.ess.example.api.GoodsFeignClient;
import com.ess.example.dto.goods.GoodsDetailDto;
import com.ess.example.dto.goods.GoodsDto;
import com.ess.framework.api.response.ApiResponse;

@Component
public class GoodsFeignClientFallback implements GoodsFeignClient {


	@Override
	public ApiResponse<GoodsDto> getGoods(@RequestParam GetGoodsReq goodsReq) {
		return  ApiResponse.failResp("远程服务异常...熔断错误...");
	}

	@Override
	public ApiResponse<GoodsDetailDto> getGoodsInfo(@RequestParam GetGoodsReq goodsReq) {
		return  ApiResponse.failResp("远程服务异常...熔断错误...");
	}

	@Override
	public ApiResponse<List<GoodsDto>> goodsList(@RequestParam GoodsListReq goodsListReq) {
		return  ApiResponse.failResp("远程服务异常...熔断错误...");
	}
}
