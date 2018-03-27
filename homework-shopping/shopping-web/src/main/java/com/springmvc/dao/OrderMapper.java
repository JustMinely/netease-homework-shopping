package com.springmvc.dao;

import com.springmvc.export.request.OrderReq;
import com.springmvc.export.response.OrderResp;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by qudi on 2018/3/27.
 */
@Component
public interface OrderMapper {
    int addOrder(OrderReq req) throws Exception;

    int deleteOrderById(OrderReq req) throws Exception;

    List<OrderResp> findOrdersByConsumerId(OrderReq req) throws Exception;

}
