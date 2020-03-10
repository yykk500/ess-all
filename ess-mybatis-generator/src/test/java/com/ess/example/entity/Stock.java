package com.ess.example.entity;

import com.ess.framework.entity.AbstractEntity;
import javax.persistence.*;

@Table(name = "stock")
public class Stock extends AbstractEntity {
    /**
     * 库存ID
     */
    @Id
    @Column(name = "stock_id")
    @GeneratedValue(generator = "JDBC")
    private Long stockId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 库存数量
     */
    @Column(name = "stock_qty")
    private Integer stockQty;

    /**
     * 获取库存ID
     *
     * @return stock_id - 库存ID
     */
    public Long getStockId() {
        return stockId;
    }

    /**
     * 设置库存ID
     *
     * @param stockId 库存ID
     */
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取库存数量
     *
     * @return stock_qty - 库存数量
     */
    public Integer getStockQty() {
        return stockQty;
    }

    /**
     * 设置库存数量
     *
     * @param stockQty 库存数量
     */
    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }
}