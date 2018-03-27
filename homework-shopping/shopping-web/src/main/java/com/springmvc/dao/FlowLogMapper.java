package com.springmvc.dao;

import com.springmvc.domain.po.FlowLog;
import org.springframework.stereotype.Component;

/**
 * Created by qudi on 2018/3/1.
 */
@Component
public interface FlowLogMapper {
    int insertFlowLog(FlowLog flowLog) throws Exception;
}
