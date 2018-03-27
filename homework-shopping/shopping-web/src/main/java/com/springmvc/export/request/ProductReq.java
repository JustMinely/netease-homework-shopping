package com.springmvc.export.request;

import com.springmvc.domain.po.Product;

import java.io.Serializable;

/**
 * 商品product请求对象
 * Created by qudi on 2018/2/28.
 */
public class ProductReq extends Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long merchantId;
    private Long customerId;
    /**
     * 购买数量
     */
    private Long productNum;

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
