package com.springmvc.export.response;

import com.springmvc.domain.po.Order;
import com.springmvc.domain.po.OrderDetail;

import java.io.Serializable;

/**
 * 订单详情响应对象
 * Created by qudi on 2018/3/27.
 */
public class OrderDetailResp extends OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
}
