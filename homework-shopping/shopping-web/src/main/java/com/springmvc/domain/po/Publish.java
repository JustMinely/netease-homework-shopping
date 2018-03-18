package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * 卖家发布商品关联信息
 * Created by qudi on 2018/3/1.
 */
public class Publish implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 卖家id
     */
    private Long merchantId;
    /**
     * 商品id
     */
    private Long productId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
