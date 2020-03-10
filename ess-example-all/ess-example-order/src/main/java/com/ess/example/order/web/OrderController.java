package com.ess.example.order.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ess.framework.boot.gloabl.AbstractController;
import com.ess.framework.commons.response.ResponseMap;

@RestController
@RefreshScope
public class OrderController extends AbstractController {

	 @Value("${hello.name:lisi}")
	private String helloName;
	 
//	 @Autowired
//	 private RestTemplate restTemplate;
	/**
	 * 
	 * @return
	 */
	@GetMapping("/order/create")
	public ResponseMap create() {
		ResponseMap responseMap =	ResponseMap.successMap("创建订单成功.");
//		responseMap.attr("helloName", helloName); 
//		ResponseMap responseMap = restTemplate.getForObject("http://EXAMPLE-GOODS/goods/get/1001", ResponseMap.class);
		responseMap.attr("helloName", helloName);
//		GoodsDTO goodsDTO =  responseMap.getData(GoodsDTO.class);
//		logger.info(JSON.toJSONString(goodsDTO));
		return responseMap;
	}
}
