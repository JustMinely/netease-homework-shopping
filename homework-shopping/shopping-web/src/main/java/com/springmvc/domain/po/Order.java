package com.springmvc.domain.po;

import java.io.Serializable;

/**
 * Created by qudi on 2018/3/27.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long consumerId;
    private String totalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
