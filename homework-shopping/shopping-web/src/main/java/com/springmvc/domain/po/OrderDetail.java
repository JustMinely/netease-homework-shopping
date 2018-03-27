package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * 订单详情
 * Created by qudi on 2018/3/27.
 */
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 订单外键
     */
    private Long orderId;
    /**
     * 商品外键
     */
    private Long productId;
    /**
     * 购买数量
     */
    private String productNum;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private String productPrice;
    /**
     * 商品图片地址
     */
    private String productUrl;
    /**
     * 备用字段
     */
    private String productAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
