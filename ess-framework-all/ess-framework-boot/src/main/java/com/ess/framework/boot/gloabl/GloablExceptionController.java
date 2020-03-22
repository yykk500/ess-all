package com.ess.framework.boot.gloabl;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

import com.ess.framework.api.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ess.framework.commons.exception.BusinessException;

/**
 * 全局异常统一处理类
 * @author Luozelin
 *
 */
@ControllerAdvice
public class GloablExceptionController {

	private final static String SID="sid";

	private final static int NOT_FOUNT_CODE= 404;

	private final static String NOT_FOUNT_MSG= "请求的路径不存在，请确认是否路径是否正确.";

	/** Logger used by this class. Available to subclasses. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 系统异常拦截处理
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value=NoHandlerFoundException.class)
	public ApiResponse<?> exception(HttpServletRequest request, NoHandlerFoundException e) {
		logger.error("拦截系统异常-404:",e);
		return returnApiResponse(request,NOT_FOUNT_CODE,NOT_FOUNT_MSG);
	}
	
	/**
	 * 系统异常拦截处理
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public ApiResponse<?> exception(HttpServletRequest request,Exception e) {
		logger.error("拦截系统异常-500:",e);
		return returnApiResponse(request,ApiResponse.CODE_DEFALUT_FAIL,ApiResponse.MESSAGE_FAIL);
	}
	
	/**
	 * 异常拦截处理
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value=BusinessException.class)
	public ApiResponse<?> busiexception(HttpServletRequest request,BusinessException exception) {
		logger.error("拦截业务异常-业务异常:",exception);
		return returnApiResponse(request,exception.getCode(),exception.getMessage());
	}

	/**
	 * 统一返回响应，处理SID字段
	 * @param request
	 * @param code
	 * @param message
	 * @return
	 */
	public ApiResponse<?> returnApiResponse(HttpServletRequest request,Integer code , String message){
		ApiResponse apiResponse =  ApiResponse.failResp(code,message);
		String sid = getSID(request);
		if(StringUtils.hasText(sid)){
			apiResponse.setSid(sid);
		}
		return apiResponse;
	}

	/**
	 * 获取SID请求参数
	 * @param request
	 * @return
	 */
	private static String getSID(HttpServletRequest request){
		String sid = request.getParameter(SID);
		if(StringUtils.hasText(sid)){
			return sid;
		}
		sid = request.getHeader(SID);
		if(StringUtils.hasText(sid)){
			return sid;
		}
		return sid;
	}
}
