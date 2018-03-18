package com.springmvc.domain.enums;

/**
 * Created by qudi on 2018/2/28.
 */
public enum ProductEnum {
    UnPurchased(0,"未购买"),
    Purchased(1,"已购买");

    private int value;
    private String desc;
    ProductEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public int value() {
        return value;
    }

    public String desc() {
        return desc;
    }
}
