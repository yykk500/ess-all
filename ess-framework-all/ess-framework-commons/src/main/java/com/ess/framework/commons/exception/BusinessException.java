/*
 * Copyright (c) 2014-2018 Swingsoft Co.Ltd. All rights reserved.
 */

package com.ess.framework.commons.exception;

/**
 * 业务异常类
 * 
 * @author Swing
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误编码
	 */
	private int code = 500;
	

	public BusinessException(String message) {
		super(message);
	}

	
	public BusinessException(int code,String message) {
		super(message);
		this.code = code;
	}
	

	public int getCode() {
		return code;
	}

}
