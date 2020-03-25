package com.ess.example.order.web;

import com.ess.example.api.GoodsFeignClient;
import com.ess.example.dto.goods.GetGoodsReq;
import com.ess.example.dto.goods.GoodsDto;
import com.ess.framework.api.response.ApiResponse;
import com.ess.framework.boot.gloabl.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class OrderController extends AbstractController {

	@Autowired
	private GoodsFeignClient goodsFeignClient;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/order/create")
	public ApiResponse create(GetGoodsReq goodsReq) {
//		GetGoodsReq goodsReq = new GetGoodsReq();
		ApiResponse<GoodsDto> response = goodsFeignClient.getGoods(goodsReq);
		return response;
	}
}
