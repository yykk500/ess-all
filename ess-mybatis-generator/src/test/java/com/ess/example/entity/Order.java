package com.ess.example.entity;

import com.ess.framework.entity.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order")
public class Order extends AbstractEntity {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    @GeneratedValue(generator = "JDBC")
    private Long orderId;

    /**
     * 订单编号唯一
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 购买数量
     */
    @Column(name = "buy_qty")
    private Integer buyQty;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 总金额
     */
    @Column(name = "total_amout")
    private BigDecimal totalAmout;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单编号唯一
     *
     * @return order_no - 订单编号唯一
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号唯一
     *
     * @param orderNo 订单编号唯一
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * 获取购买数量
     *
     * @return buy_qty - 购买数量
     */
    public Integer getBuyQty() {
        return buyQty;
    }

    /**
     * 设置购买数量
     *
     * @param buyQty 购买数量
     */
    public void setBuyQty(Integer buyQty) {
        this.buyQty = buyQty;
    }

    /**
     * 获取商品单价
     *
     * @return price - 商品单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品单价
     *
     * @param price 商品单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取总金额
     *
     * @return total_amout - 总金额
     */
    public BigDecimal getTotalAmout() {
        return totalAmout;
    }

    /**
     * 设置总金额
     *
     * @param totalAmout 总金额
     */
    public void setTotalAmout(BigDecimal totalAmout) {
        this.totalAmout = totalAmout;
    }
}