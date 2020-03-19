package com.ess.example.dto.goods;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.lang.Long;
import java.lang.String;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Long;

/**
 * GoodsDto
 *
 * @author dto-generator
 */
@Setter
@Getter
public class GoodsDto {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 商品价格
     */
    @JSONField(name = "price_xx",deserialize = true)
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createDt;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 版本号
     */
    private Long version;


}




