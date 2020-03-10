package com.ess.example.dto;

public class GoodsDetailDTO {

	/**
	 * 分类
	 */
	private String classify;
	
	/**
	 * 描述
	 */
	private String desc;
	
	
	
	public GoodsDetailDTO() {
	}

	public GoodsDetailDTO(String classify, String desc) {
		this.classify = classify;
		this.desc = desc;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
