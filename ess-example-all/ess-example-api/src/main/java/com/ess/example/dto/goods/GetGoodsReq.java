package com.ess.example.dto.goods;

import com.ess.framework.api.request.ApiRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(description = "查询商品请求参数")
public class GetGoodsReq extends ApiRequest {

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

}
