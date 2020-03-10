package com.ess.example.dto.stock;

import lombok.Getter;
import lombok.Setter;

import java.lang.Long;
import java.lang.Long;
import java.lang.Integer;
import java.lang.Long;

/**
* StockDto
*
* @author dto-generator
*/
@Setter
@Getter
public class StockDto {

    /**
    * 库存ID
    */
    private Long stockId;

    /**
    * 商品id
    */
    private Long goodsId;

    /**
    * 库存数量
    */
    private Integer stockQty;

    /**
    * 版本
    */
    private Long version;


}




