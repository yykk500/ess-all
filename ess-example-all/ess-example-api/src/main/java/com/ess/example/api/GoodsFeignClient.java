package com.ess.example.api;

import com.ess.example.dto.goods.GetGoodsReq;
import com.ess.example.dto.goods.GoodsListReq;
import com.ess.framework.api.response.ApiPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ess.example.dto.goods.GoodsDetailDto;
import com.ess.example.dto.goods.GoodsDto;
import com.ess.framework.api.ApiFeignClient;
import com.ess.framework.api.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "example-goods")
public interface GoodsFeignClient extends ApiFeignClient {

	/**
	 * 查询商品信息
	 * @return
	 */
	@PostMapping(value = "/getGoods",produces = APPLICATION_JSON )
	public ApiResponse<GoodsDto> getGoods(@RequestBody GetGoodsReq goodsReq);

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
	public ApiPageResponse<GoodsDto> goodsList(GoodsListReq goodsListReq);


	/**
	 * 测速接口
	 * @return
	 */
	@GetMapping(value = "/cesu",produces = APPLICATION_JSON )
	public ApiResponse<String> cesu(GoodsListReq goodsListReq);
}
