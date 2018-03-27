package com.springmvc.dao;


import com.springmvc.domain.po.Customer;
import org.springframework.stereotype.Component;

/**
 * Created by qudi on 2018/2/19.
 */
@Component
public interface CustomerMapper {
    Customer getCustomerById(Long id) throws  Exception;

    Customer getCustomerByName(String customerName) throws  Exception;
}
