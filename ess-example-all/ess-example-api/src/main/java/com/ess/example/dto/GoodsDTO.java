package com.ess.example.dto;

/***
 * 数据传输对象
 * @author Luozelin
 *
 */
public class GoodsDTO {
	
	private  Long goodsId;
	
	private String goodsName;

	private GoodsDetailDTO goodsDetail;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public GoodsDetailDTO getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(GoodsDetailDTO goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	
}
