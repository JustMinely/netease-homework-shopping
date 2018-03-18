package com.springmvc.export.response;

/**
 * Created by qudi on 2018/2/28.
 */
public enum ResultCode {

    SUCCESS("10001", "成功"),
    FAILURE("10002", "失败"),
    EXCEPTION("10003", "内部异常"),
    PARAMERROR("10004", "参数错误"),
    NOTEXIST("10005", "不存在"),
    HASEXIST("10009", "已存在"),
    RECORDNOTEXIST("10010", "记录不存在");
    private String value;
    private String desc;

    ResultCode(String value, String desc) {
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
