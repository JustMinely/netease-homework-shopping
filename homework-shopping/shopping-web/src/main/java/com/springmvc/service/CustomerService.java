package com.springmvc.service;


import com.springmvc.domain.po.Customer;

/**
 * Created by qudi on 2018/2/19.
 */
public interface CustomerService {
    Customer getCustomerById(Long id);

}
