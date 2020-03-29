package com.ess.framework.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 验证工具类
 */
public class ValidUtils {

    /**
     * 禁止new 对象
     */
    private ValidUtils(){

    }

    /**
     * 验证参数不能为空。
     * @param obect
     * @param message
     */
    public static void isNull(Object obect,String message){
        if(obect == null){
            ExceptionUtils.throwValid(message);
        }
    }


    /**
     * 验证参数为空-抛出异常
     * @param value
     * @param message
     */
    public static void isBlank(String value,String message){
        if(StringUtils.isBlank(value)){
            ExceptionUtils.throwValid(message);
        }
    }


    /**
     * 验证参数不为空-抛出异常
     * @param value
     * @param message
     */
    public static void isNotBlank(String value,String message){
        if(StringUtils.isNotBlank(value)){
            ExceptionUtils.throwValid(message);
        }
    }
}
