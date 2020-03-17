package com.ess.framework.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

/**
 * 分页返回实体类
 *
 * @author Luozelin
 *
 */
@ApiModel
public class ApiPageResponse<T> extends AbstractResponse{

    @ApiModelProperty(value = "响应data",notes ="返回List集合对象" )
    private List<T> data;

    @ApiModelProperty(value = "总记录数量",notes ="总记录数量" )
    private Long total;

    @ApiModelProperty(value = "总页数",notes ="总页数" )
    private Integer pages;

    public ApiPageResponse() {
    }

    /**
     * 构造方法
     *
     * @param code
     * @param message
     */
    public ApiPageResponse(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    /**
     * 响应成功的Response
     *
     * @return status=200,message= 操作成功
     */
    public static <T> ApiPageResponse<T> successResp() {
        return successResp(MESSAGE_SUCCESS);
    }

    /**
     * 响应成功的Response
     *
     * @return status=200,message=${message}
     */
    public static <T> ApiPageResponse<T> successResp(String message) {
        return new ApiPageResponse<T>(CODE_SUCCESS, message);
    }

    /**
     * 响应成功的Response，并设置data
     * @param <T>
     * @param data
     * @return status=200,message=${message}
     * @return
     */
    public static <T> ApiPageResponse<T> successResp(List<T> data) {
        return successResp(data,MESSAGE_SUCCESS);
    }

    /**
     * 响应成功的Response，并设置data和message
     * @param <T>
     * @param dataList
     * @param message @return status=200,message=${message}
     * @return
     */
    public static <T> ApiPageResponse<T> successResp(List<T> dataList,String message) {
        ApiPageResponse<T> response =  new ApiPageResponse<T>(CODE_SUCCESS, message);
        response.setData(dataList);
        return response;
    }

    /**
     * 响应失败的Response
     *
     * @return status=500,message=对不起，系统异常.请联系管理员.
     */
    public static <T> ApiPageResponse<T> failResp() {
        return failResp(CODE_DEFALUT_FAIL, MESSAGE_FAIL);
    }

    /**
     * 响应失败的Response
     *
     * @return status=500,message=${message}
     */
    public static <T> ApiPageResponse<T> failResp(String message) {
        return failResp(CODE_DEFALUT_FAIL, message);
    }

    /**
     * 响应失败的Response
     *
     * @return status=${code},message=${message}
     */
    public static <T> ApiPageResponse<T> failResp(int code, String message) {
        return new ApiPageResponse<T>(code, message);
    }

    /**
     * 设置分页信息
     * @param total 总记录数
     * @param pages 总页数
     * @return
     */
    public ApiPageResponse<T> setPageInfo(Long total, Integer pages) {
        this.total = total;
        this.pages = pages;
        return this;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
