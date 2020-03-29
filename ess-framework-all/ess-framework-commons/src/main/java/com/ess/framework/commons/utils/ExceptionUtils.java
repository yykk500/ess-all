package com.ess.framework.commons.utils;

import com.ess.framework.commons.exception.BusinessException;
import com.ess.framework.commons.exception.ValidException;

/**
 * 异常工具类
 */
public class ExceptionUtils {
    /**
     * 禁止new 对象
     */
    private ExceptionUtils(){
    }

    /**
     * 抛出异常 BusinessException
     * @param code
     * @param message
     */
    public static void throwBusiness(int code ,String message){
        throw new BusinessException(code,message);
    }

    /**
     * 抛出异常 BusinessException
     * @param message
     */
    public static void throwBusiness(String message){
        throw new BusinessException(message);
    }


    /**
     * 抛出异常 ValidException
     * @param code
     * @param message
     */
    public static void throwValid(int code ,String message){
        throw new ValidException(code,message);
    }

    /**
     * 抛出异常 ValidException
     * @param message
     */
    public static void throwValid(String message){
        throw new ValidException(message);
    }
}
