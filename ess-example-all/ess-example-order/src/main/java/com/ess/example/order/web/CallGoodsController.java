package com.ess.example.order.web;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallGoodsController {

//	@Autowired
//	private GoodsFeignClient goodsFeignClient;
//
//	@GetMapping("/call/{goodsId}")
//	public ResponseMap call(@PathVariable("goodsId") Integer goodsId) {
//		ResponseMap responseMap = ResponseMap.successMap();
////		ApiResponse<GoodsDTO> goodsResp = goodsFeignClient.getGoods(goodsId);
//		if(goodsResp.getStatus()) {
//			GoodsDTO goodsDTO = goodsResp.getData();
//			responseMap.setData(goodsDTO);
//			System.out.println(goodsDTO.getGoodsDetail().getClassify());
//		}else {
//			throw new BusinessException(goodsResp.getCode(), goodsResp.getMessage());
//		}
//		return responseMap;
//	}
//
//	@GetMapping("/exists/{goodsId}")
//	public ResponseMap exists(@PathVariable("goodsId") Integer goodsId) {
//		ResponseMap responseMap = ResponseMap.successMap();
//		// 远程获取对象
//		ApiResponse<String> goodsResponse = goodsFeignClient.existGoods(goodsId);
//		if (goodsResponse.getStatus()) {
//			responseMap.attr("existsMsg", goodsResponse.getData());
//		} else {
//			throw new BusinessException(goodsResponse.getCode(), goodsResponse.getMessage());
//		}
//		return responseMap;
//	}
}
