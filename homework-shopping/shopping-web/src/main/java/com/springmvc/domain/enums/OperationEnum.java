package com.springmvc.domain.enums;

/**
 * Created by qudi on 2018/3/1.
 */
public enum OperationEnum {
    ADD("add", "添加操作"),
    DELETE("delete", "删除操作"),
    UPDATE("update", "更新操作");

    private String value;
    private String desc;

    OperationEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String value() {
        return value;
    }

    public String desc() {
        return desc;
    }
}
