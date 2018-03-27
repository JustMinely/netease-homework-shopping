package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * Created by qudi on 2018/3/26.
 */
public class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String merchantName;
    private String password;
}
