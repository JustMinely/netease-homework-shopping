package com.springmvc.export.response;

import java.io.Serializable;

/**
 * 请求结果
 * Created by qudi on 2018/2/28.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请求状态
     */
    private Boolean success;
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T datas;
    public Result(Boolean isSuccess, String code, String msg) {
        this.success = isSuccess;
        this.code = code;
        this.msg = msg;
    }

    public Result(Boolean success, String code, String msg, T datas) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }

    public Result(Boolean success, ResultCode resultCode, T datas) {
        this.success = success;
        this.code = resultCode.value();
        this.msg = resultCode.desc();
        this.datas = datas;
    }

    public Result(Boolean success, ResultCode resultCode) {
        this.success = success;
        this.code = resultCode.value();
        this.msg = resultCode.desc();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
