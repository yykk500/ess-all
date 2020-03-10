package com.ess.framework.api.request;

/**
 * Api请求参数基类
 * 
 * @author Luozelin
 *
 */
public abstract class ApiRequest {
	
	/**
	 * 请求sid，每个请求为唯一标识码
	 */
	private String sid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
