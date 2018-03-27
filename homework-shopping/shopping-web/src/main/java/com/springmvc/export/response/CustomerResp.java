package com.springmvc.export.response;

import com.springmvc.domain.po.Customer;

import java.io.Serializable;

/**
 * Created by qudi on 2018/3/26.
 */
public class CustomerResp extends Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer usertype = 0;

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}
