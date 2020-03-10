package com.ess.example.goods.web;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import com.ess.example.api.GoodsFeignClient;
import com.ess.example.dto.goods.GoodsDetailDto;
import com.ess.example.dto.goods.GoodsDto;
import com.ess.example.entity.Goods;
import com.ess.expamle.service.GoodsService;
import com.ess.framework.api.response.ApiResponse;
import com.ess.framework.boot.gloabl.AbstractController;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

@RestController
@RefreshScope
public class GoodsController extends AbstractController implements GoodsFeignClient {

	@Autowired
	private GoodsService goodsService;

	@Override
	public ApiResponse<GoodsDto> getGoods(Long goodsId) {
		// 根据Id查询商品信息
		Goods goods = goodsService.selectByPrimaryKey(goodsId);
		// PO与Dto数据对象转换
		GoodsDto goodsDto = new GoodsDto();
		BeanUtils.copyProperties(goods, goodsDto);
		return ApiResponse.successResp(goodsDto, "+" + Math.random());
	}

	@Override
	public ApiResponse<String> existGoods(Long goodsId) {
		return null;
	}

	@Override
	public ApiResponse<GoodsDetailDto> getGoodsInfo(Long goodsId) {
		Goods goods = goodsService.selectByPrimaryKey(goodsId);
		GoodsDetailDto goodsDetailDto = new GoodsDetailDto();
		BeanUtils.copyProperties(goods, goodsDetailDto);
		goodsDetailDto.setStockQty(10);
		return ApiResponse.successResp(goodsDetailDto);
	}

	@Override
	public ApiResponse<List<GoodsDto>> goodsList() {
		List<GoodsDto> goodsList = new ArrayList<>();
		PageHelper.startPage(1, 2);
		Example example = Example.builder(Goods.class)
//				.setDistinct(true).select("goodsId", "goodsDesc")
				.where(Sqls.custom().andEqualTo("goodsId", 1))
				.orderByAsc("goodsId")
				.orderByDesc("goodsName")
				.build();
		List<Goods> list = goodsService.selectByExample(example);
		PageInfo pageInfo = new PageInfo(list);
		for(Goods tempGoods : list){
			GoodsDto goodsDto = new GoodsDto();
			BeanUtils.copyProperties(tempGoods,goodsDto);
			goodsList.add(goodsDto);
		}
		pageInfo.setList(goodsList);
		return ApiResponse.successResp(goodsList);
	}

}
