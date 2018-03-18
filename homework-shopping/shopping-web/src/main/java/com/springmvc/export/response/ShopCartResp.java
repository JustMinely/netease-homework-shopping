package com.springmvc.export.response;

import com.springmvc.domain.po.ShopCart;

import java.io.Serializable;

/**
 * Created by qudi on 2018/3/5.
 */
public class ShopCartResp extends ShopCart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;
    private String productPrice;
    private String productUrl;

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private Integer productStatus;

}
