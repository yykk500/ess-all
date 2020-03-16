package com.ess.example.dto.goods;

import com.ess.framework.api.request.ApiPageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value="分页商品查询条件",description="分页商品列表请求参数" )
public class GoodsListReq extends ApiPageRequest {

    @ApiModelProperty(value = "商品名称")
    private String goodsName;
}
