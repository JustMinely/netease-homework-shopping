package com.springmvc.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志流水
 * Created by qudi on 2018/3/1.
 */
public class FlowLog implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 流水ID
     */
    private Long id;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 操作描述
     */
    private String operationDesc;
    /**
     * 操作数据标识
     */
    private String dataId;
    /**
     * 操作数据内容
     */
    private String content;

    private String user;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;

    public FlowLog(String operationType, String operationDesc, String dataId, String content, String user) {
        this.operationType = operationType;
        this.operationDesc = operationDesc;
        this.dataId = dataId;
        this.content = content;
        this.user = user;
    }

    public FlowLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
