package com.springmvc.service;

import com.springmvc.export.request.OrderReq;
import com.springmvc.export.response.Result;

import java.util.List;

/**
 * Created by qudi on 2018/3/28.
 */
public interface OrderService {
    Result getOrderDetailsByCustomerId(OrderReq req);

}
