package com.springmvc.service.impl;


import com.springmvc.dao.CustomerMapper;
import com.springmvc.domain.po.Customer;
import com.springmvc.service.BaseService;
import com.springmvc.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qudi on 2018/2/19.
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseService implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);



    @Resource(name = "customerMapper")
    private CustomerMapper customerMapper;

    public Customer getCustomerById(Long id) {
        try {
            return customerMapper.getCustomerById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
}
