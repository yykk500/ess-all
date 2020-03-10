package com.ess.example.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.lang.Long;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.lang.Long;

/**
* OrderDto
*
* @author dto-generator
*/
@Setter
@Getter
public class OrderDto {

    /**
    * 订单id
    */
    private Long orderId;

    /**
    * 订单编号唯一
    */
    private String orderNo;

    /**
    * 商品id
    */
    private Long goodsId;

    /**
    * 购买数量
    */
    private Integer buyQty;

    /**
    * 商品单价
    */
    private BigDecimal price;

    /**
    * 总金额
    */
    private BigDecimal totalAmout;

    /**
    * 版本号
    */
    private Long version;


}




