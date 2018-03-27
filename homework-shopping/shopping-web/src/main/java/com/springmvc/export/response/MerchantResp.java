package com.springmvc.export.response;

import com.springmvc.domain.po.Merchant;

import java.io.Serializable;

/**
 * Created by qudi on 2018/3/26.
 */
public class MerchantResp extends Merchant implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer usertype = 1;

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}
