package com.springmvc.dao;


import com.springmvc.domain.po.Customer;

/**
 * Created by qudi on 2018/2/19.
 */
public interface CustomerMapper {
    Customer getCustomerById(Long id) throws  Exception;

    Customer getCustomerByName(String customerName) throws  Exception;
}
