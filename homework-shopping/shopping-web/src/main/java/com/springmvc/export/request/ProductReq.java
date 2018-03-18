package com.springmvc.export.request;

import com.springmvc.domain.po.Product;

import java.io.Serializable;

/**
 * Created by qudi on 2018/2/28.
 */
public class ProductReq extends Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long merchantId;
    private Long customerId;

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
