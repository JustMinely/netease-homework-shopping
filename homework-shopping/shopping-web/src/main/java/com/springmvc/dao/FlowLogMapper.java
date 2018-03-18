package com.springmvc.dao;

import com.springmvc.domain.po.FlowLog;

/**
 * Created by qudi on 2018/3/1.
 */
public interface FlowLogMapper {
    int insertFlowLog(FlowLog flowLog) throws Exception;
}
