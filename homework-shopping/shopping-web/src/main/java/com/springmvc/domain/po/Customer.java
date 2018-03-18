package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * Created by qudi on 2018/2/19.
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String customerName;
    private String password;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
