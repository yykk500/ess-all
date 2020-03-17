package com.ess.framework.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回消息的实体类
 * 
 * @author Luozelin
 *
 */
@ApiModel("返回消息响应Model")
public class ApiResponse<T> extends AbstractResponse {

	@ApiModelProperty(value = "响应data",notes ="响应data" )
	private T data; // 响应data，都和对象都可以。

	public ApiResponse() {
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 * @param message
	 */
	public ApiResponse(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	/**
	 * 响应成功的Response
	 * 
	 * @return status=200,message= 操作成功
	 */
	public static <T> ApiResponse<T> successResp() {
		return successResp(MESSAGE_SUCCESS);
	}

	/**
	 * 响应成功的Response
	 * 
	 * @return status=200,message=${message}
	 */
	public static <T> ApiResponse<T> successResp(String message) {
		return new ApiResponse<T>(CODE_SUCCESS, message);
	}
	
	/**
	 * 响应成功的Response，并设置data
	 * @param <T>
	 * @param data
	 * @return status=200,message=${message}
	 * @return
	 */
	public static <T> ApiResponse<T> successResp(T data) {
		return successResp(data,MESSAGE_SUCCESS);
	}
	
	/**
	 * 响应成功的Response，并设置data和message
	 * @param <T>
	 * @param data
	 * @param message @return status=200,message=${message}
	 * @return
	 */
	public static <T> ApiResponse<T> successResp(T data,String message) {
		ApiResponse<T> response =  new ApiResponse<T>(CODE_SUCCESS, message);
		response.setData(data);
		return response;
	}

	/**
	 * 响应失败的Response
	 * 
	 * @return status=500,message=对不起，系统异常.请联系管理员.
	 */
	public static <T> ApiResponse<T> failResp() {
		return failResp(CODE_DEFALUT_FAIL, MESSAGE_FAIL);
	}

	/**
	 * 响应失败的Response
	 * 
	 * @return status=500,message=${message}
	 */
	public static <T> ApiResponse<T> failResp(String message) {
		return failResp(CODE_DEFALUT_FAIL, message);
	}

	/**
	 * 响应失败的Response
	 * 
	 * @return status=${code},message=${message}
	 */
	public static <T> ApiResponse<T> failResp(int code, String message) {
		return new ApiResponse<T>(code, message);
	}



	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
