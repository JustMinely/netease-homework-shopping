package com.springmvc.service;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by qudi on 2018/2/28.
 */
public abstract class BaseService {
    //获取事务定义
    public DefaultTransactionDefinition define() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return definition;
    }
}
