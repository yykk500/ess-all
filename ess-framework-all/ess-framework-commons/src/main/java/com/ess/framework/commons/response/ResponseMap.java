//package com.ess.framework.commons.response;
//
//import java.util.HashMap;
//
//import com.ess.framework.commons.exception.BusinessException;
//
///**
// * 返回消息的实体类
// * @author Luozelin
// *
// */
//public class ResponseMapx extends HashMap<String, Object> {
//
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private static final String  STATUS = "status"; // 响应状态是否成功 code=时等于true，非200时等于false
//
//	private static final String  CODE = "code"; // 响应码：200等于成功，其他等于失败。
//
//	private static final String  MESSAGE="message"; // 响应消息
//
//	private static final String  DATA="data"; // 响应data，都和对象都可以。
//
//	private static final int  CODE_SUCCESS= 200; // 响应状态是否成功 code=时等于true，非200时等于false
//
//	public static final int  CODE_DEFALUT_FAIL= 500; // 默认错误编码500
//
//	public final static String MESSAGE_SUCCESS = "操作成功.";
//
//	public final static String MESSAGE_FAIL = "对不起，系统异常.请联系管理员.";
//
//	public ResponseMap() {
//	}
//	/**
//	 * 构造方法
//	 * @param code
//	 * @param message
//	 */
//	public ResponseMap(int code, String message) {
//		this.setCode(code);
//		this.setMessage(message);
//	}
//
//	/**
//	 *  响应成功的Map
//	 * @return   status=200,message= 操作成功
//	 */
//	public static ResponseMap successMap() {
//		return	 successMap(MESSAGE_SUCCESS);
//	}
//
//	/**
//	 *  响应成功的Map
//	 * @return  status=200,message=${message}
//	 */
//	public static ResponseMap successMap(String message) {
//		return	 new ResponseMap(CODE_SUCCESS, message);
//	}
//
//	/**
//	 *  响应失败的Map
//	 * @return  status=500,message=对不起，系统异常.请联系管理员.
//	 */
//	public static ResponseMap failMap() {
//		return	 failMap(CODE_DEFALUT_FAIL, MESSAGE_FAIL);
//	}
//
//	/**
//	 *  响应失败的Map
//	 * @return  status=500,message=${message}
//	 */
//	public static ResponseMap failMap(String message) {
//		return	 failMap(CODE_DEFALUT_FAIL, message);
//	}
//
//
//	/**
//	 *  响应失败的Map
//	 * @return status=${code},message=${message}
//	 */
//	public static ResponseMap failMap(int code,String message) {
//		return	 new ResponseMap(code, message);
//	}
//
//	/**
//	 *
//	 * 获取BusinessException异常的Map
//	 *
//	 * @return status=e.code,message=e.message
//	 */
//	public static ResponseMap failMap(BusinessException e) {
//		return failMap(e.getCode(), e.getMessage());
//	}
//
//	/**
//	 *  设置属性
//	 * @param key
//	 * @param data
//	 */
//	public ResponseMap attr(String key,Object value) {
//		this.put(key, value);
//		return this;
//	}
//
//	/**
//	 * 删除属性
//	 * @param key
//	 * @param data
//	 */
//	public ResponseMap removeAttr(String key,Object value) {
//		this.remove(key, value);
//		return this;
//	}
//
//	/*******************  set / get 方法****************/
//	public Boolean getStatus() {
//		return (Boolean)this.get(STATUS);
//	}
//
//	private void setStatus(Boolean status) {
//		this.put(STATUS,status);
//	}
//
//	public int getCode() {
//		return (int)this.get(CODE);
//	}
//
//	public void setCode(int code) {
//		if(code == CODE_SUCCESS) {
//			this.setStatus(true);
//		}else {
//			this.setStatus(false);
//		}
//		this.put(CODE, code);
//	}
//
//	public String getMessage() {
//		return (String)this.get(MESSAGE);
//	}
//
//	public void setMessage(String message) {
//		 this.put(MESSAGE, message);
//	}
//
//	@SuppressWarnings("unchecked")
//	public  <T> T getData(Class<T> clazz) {
//		return  (T)this.get(DATA);
//	}
//
//	public ResponseMap setData(Object data) {
//		this.put(DATA, data);
//		return this;
//	}
//}
