package com.ess.framework.boot.gloabl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ess.framework.commons.exception.BusinessException;
import com.ess.framework.commons.response.ResponseMap;

/**
 * 全局异常统一处理类
 * @author Luozelin
 *
 */
@ControllerAdvice
public class GloablExceptionHandler {

	/** Logger used by this class. Available to subclasses. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 系统异常拦截处理
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value=NoHandlerFoundException.class)
	public ResponseMap exception(HttpServletRequest request,NoHandlerFoundException e) {
		logger.error("拦截系统异常-404:",e);
		ResponseMap reponseMap=  ResponseMap.failMap();
		reponseMap.setCode(404);
		reponseMap.setMessage("请求的路径不存在，请确认是否路径是否正确.");
		return reponseMap;
	}
	
	/**
	 * 系统异常拦截处理
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public ResponseMap exception(HttpServletRequest request,Exception e) {
		logger.error("拦截系统异常-500:",e);
		ResponseMap reponseMap=  ResponseMap.failMap();
		return reponseMap;
	}
	
	/**
	 * 异常拦截处理
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value=BusinessException.class)
	public ResponseMap busiexception(BusinessException exception) {
		logger.error("拦截业务异常-业务异常:",exception);
		return ResponseMap.failMap(exception);
	}
	
	

	/**
	 * 异常拦截处理
	 * @return
	 */
//	@ResponseBody
//	@ExceptionHandler(value=AuthException.class)
//	public ResponseData  busiexception(AuthException exception) {
//		Map<String,Object> resultMap = ResultMapHelper.failMap(exception);
//		resultMap.put("isLogin", false);
//		return resultMap;
//	}
}
