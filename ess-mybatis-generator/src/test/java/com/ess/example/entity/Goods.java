package com.ess.example.entity;

import com.ess.framework.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "goods")
public class Goods extends AbstractEntity {
    /**
     * 商品id
     */
    @Id
    @Column(name = "goods_id")
    @GeneratedValue(generator = "JDBC")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品描述
     */
    @Column(name = "goods_desc")
    private String goodsDesc;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    @Column(name = "create_dt")
    private Date createDt;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

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
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品描述
     *
     * @return goods_desc - 商品描述
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * 设置商品描述
     *
     * @param goodsDesc 商品描述
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取创建时间
     *
     * @return create_dt - 创建时间
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * 设置创建时间
     *
     * @param createDt 创建时间
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}