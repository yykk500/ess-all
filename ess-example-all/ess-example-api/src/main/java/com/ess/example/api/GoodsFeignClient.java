package com.ess.example.api;

import java.util.List;

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
	public ApiResponse<GoodsDto> getGoods(@RequestParam("goodsId") Long goodsId);
	
	
	
	/**
	 * 查询商品信息
	 * @return
	 */
	@GetMapping(value = "/getGoodsInfo",produces = APPLICATION_JSON )
	public ApiResponse<GoodsDetailDto> getGoodsInfo(@RequestParam("goodsId") Long goodsId);
	
	/**
	 *  Return
	 * @return
	 */
	@GetMapping(value = "/existGoods",produces = APPLICATION_JSON)
	public ApiResponse<String> existGoods(@RequestParam("goodsId") Long goodsId);
	
	
	/**
	 * 查询商品信息
	 * @return
	 */
	@GetMapping(value = "/goodsList",produces = APPLICATION_JSON )
	public ApiResponse<List<GoodsDto>> goodsList();
}
