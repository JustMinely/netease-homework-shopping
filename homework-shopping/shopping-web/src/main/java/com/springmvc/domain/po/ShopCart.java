package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * 购物车实体类
 * Created by qudi on 2018/3/5.
 */
public class ShopCart implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;


    /**
     * 买家Id
     */
    private Long customerId;
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 预购数量
     */
    private Long productQuantity;

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
