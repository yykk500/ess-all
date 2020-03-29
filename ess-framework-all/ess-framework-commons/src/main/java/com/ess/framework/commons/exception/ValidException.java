package com.ess.framework.commons.exception;

/**
 * 验证异常类.
 */
public class ValidException extends BusinessException{

    public ValidException(String message) {
        super(message);
    }

    public ValidException(int code, String message) {
        super(code, message);
    }
}
