package com.ess.framework.api.request;

import io.netty.util.internal.UnstableApi;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiPageRequest extends ApiRequest {

    /**
     * 第几页
     */
    @ApiParam("分页参数：第几页")
    private Integer pageNo = 1;

    /**
     * 每页显示数量
     */
    @ApiParam("分页参数：每页显示条数")
    private Integer pageSize = 10;



}
