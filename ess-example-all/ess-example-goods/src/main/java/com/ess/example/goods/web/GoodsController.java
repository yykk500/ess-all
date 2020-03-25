package com.ess.example.goods.web;

import java.util.ArrayList;
import java.util.List;

import com.ess.example.dto.goods.GetGoodsReq;
import com.ess.example.dto.goods.GoodsListReq;
import com.ess.framework.api.response.ApiPageResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
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

@RestController
@RefreshScope
@Api(tags = "商品接口")
public class GoodsController extends AbstractController implements GoodsFeignClient {

	@Autowired
	private GoodsService goodsService;

	@Override
	@ApiOperation(value = "查询商品接口")
	public ApiResponse<GoodsDto> getGoods(@ApiParam @RequestBody GetGoodsReq getGoodsReq) {
		// 根据Id查询商品信息
		Goods goods = goodsService.selectByPrimaryKey(getGoodsReq.getGoodsId());
		try {
			// 休眠5秒
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// PO与Dto数据对象转换
		GoodsDto goodsDto = new GoodsDto();
		BeanUtils.copyProperties(goods, goodsDto);
		return ApiResponse.successResp(goodsDto, "+" + Math.random());
	}

	@Override
	@ApiOperation(value = "获取商品详细信息")
	public ApiResponse<GoodsDetailDto> getGoodsInfo(@ApiParam GetGoodsReq getGoodsReq) {
		Goods goods = goodsService.selectByPrimaryKey(getGoodsReq.getGoodsId());
		GoodsDetailDto goodsDetailDto = new GoodsDetailDto();
		BeanUtils.copyProperties(goods, goodsDetailDto);
		goodsDetailDto.setStockQty(10);
		return ApiResponse.successResp(goodsDetailDto);
	}

	@Override
	@ApiOperation(value = "分页查询商品列表")
	public ApiPageResponse<GoodsDto> goodsList(@ApiParam GoodsListReq goodsListReq) {
		// 分页查询
		PageHelper.startPage(goodsListReq.getPageNo(), goodsListReq.getPageSize());
		// 构建查询条件
		Example example = Example.builder(Goods.class).build();
		Example.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(goodsListReq.getGoodsName())){
			criteria.andLike("goodsName", "%"+goodsListReq.getGoodsName() + "%");
		}
		List<GoodsDto> goodsList = new ArrayList<>();
		List<Goods> list = goodsService.selectByExample(example);
		PageInfo pageInfo = new PageInfo(list);
		for(Goods tempGoods : list){
			GoodsDto goodsDto = new GoodsDto();
			BeanUtils.copyProperties(tempGoods,goodsDto);
			goodsList.add(goodsDto);
		}
		ApiPageResponse<GoodsDto> response = ApiPageResponse.successResp(goodsList);
		// 设置分页数据：总记录数和分页数量
		response.setPageInfo(pageInfo.getTotal(),pageInfo.getPages());
		return response;
	}

}
