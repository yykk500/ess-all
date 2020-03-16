package com.ess.example.api;

import java.util.List;

import com.ess.example.dto.goods.GetGoodsReq;
import com.ess.example.dto.goods.GoodsListReq;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ess.example.api.fallback.GoodsFeignClientFallback;
import com.ess.example.dto.goods.GoodsDetailDto;
import com.ess.example.dto.goods.GoodsDto;
import com.ess.framework.api.ApiFeignClient;
import com.ess.framework.api.response.ApiResponse;

@FeignClient(name = "example-goods",fallback = GoodsFeignClientFallback.class)
public interface GoodsFeignClient extends ApiFeignClient {

	/**
	 * 查询商品信息
	 * @return
	 */
	@GetMapping(value = "/getGoods",produces = APPLICATION_JSON )
	public ApiResponse<GoodsDto> getGoods(GetGoodsReq goodsReq);

	/**
	 * 查询商品信息
	 * @return
	 */
	@GetMapping(value = "/getGoodsInfo",produces = APPLICATION_JSON )
	public ApiResponse<GoodsDetailDto> getGoodsInfo(GetGoodsReq goodsReq);

	/**
	 * 查询商品信息
	 * @return
	 */
	@GetMapping(value = "/goodsList",produces = APPLICATION_JSON )
	public ApiResponse<List<GoodsDto>> goodsList(GoodsListReq goodsListReq);
}
