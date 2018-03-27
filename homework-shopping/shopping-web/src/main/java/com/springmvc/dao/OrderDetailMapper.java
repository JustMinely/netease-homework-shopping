package com.springmvc.dao;

import com.springmvc.export.request.OrderDetailReq;
import com.springmvc.export.response.OrderDetailResp;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单详情dao
 * Created by qudi on 2018/3/27.
 */
@Component
public interface OrderDetailMapper {
    List<OrderDetailResp> getOrderDetailsByOrderId(OrderDetailReq req) throws Exception;

    int deleteOrderDetailsByOrderId(OrderDetailReq req) throws Exception;

    int addOrderDetail(OrderDetailReq req) throws Exception;

}
