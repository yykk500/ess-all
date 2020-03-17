package com.ess.framework.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回消息响应Model")
public class AbstractResponse {

    @ApiModelProperty(value = "响应码",notes ="响应码：200等于成功，其他等于失败" )
    private Integer code;// 响应码：200等于成功，其他等于失败

    @ApiModelProperty(value = "响应状态",notes ="是否成功 code=200时等于true，非200时等于false" )
    private Boolean status; // 响应状态

    @ApiModelProperty(value = "响应消息",notes ="响应消息：操作成功" )
    private String message;// 响应消息


    public static final int CODE_SUCCESS = 200; // 响应状态是否成功 code=时等于true，非200时等于false

    public static final int CODE_DEFALUT_FAIL = 500; // 默认错误编码500

    public final static String MESSAGE_SUCCESS = "操作成功.";

    public final static String MESSAGE_FAIL = "对不起，系统异常.请联系管理员.";


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
}
