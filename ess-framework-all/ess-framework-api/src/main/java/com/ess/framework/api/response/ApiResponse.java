package com.ess.framework.api.response;

/**
 * 返回消息的实体类
 * 
 * @author Luozelin
 *
 */
public class ApiResponse<T> {

	private Boolean status; // 响应状态是否成功 code=时等于true，非200时等于false

	private Integer code;// 响应码：200等于成功，其他等于失败。

	private String message;// 响应消息

	private T data; // 响应data，都和对象都可以。
	
	private static final int CODE_SUCCESS = 200; // 响应状态是否成功 code=时等于true，非200时等于false

	public static final int CODE_DEFALUT_FAIL = 500; // 默认错误编码500

	public final static String MESSAGE_SUCCESS = "操作成功.";

	public final static String MESSAGE_FAIL = "对不起，系统异常.请联系管理员.";

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
	 * @param message @return status=200,message=${message}
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		if(code == CODE_SUCCESS) {
			this.setStatus(true);
		}else {
			this.setStatus(false);
		}
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
