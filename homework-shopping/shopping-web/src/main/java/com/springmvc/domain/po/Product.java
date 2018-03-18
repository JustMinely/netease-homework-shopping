package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * Created by qudi on 2018/2/28.
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private String productPrice;
    /**
     * 商品库存，默认100
     */
    private String productAmount;
    /**
     * 商品描述
     */
    private String productDesc;
    /**
     * 商品正文内容
     */
    private String productContent;
    /**
     * 商品图片url
     */
    private String productUrl;
    /**
     * 是否被购买
     */
    private Integer productStatus;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }


}
