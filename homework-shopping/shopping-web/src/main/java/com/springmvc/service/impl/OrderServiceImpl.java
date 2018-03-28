package com.springmvc.service.impl;

import com.springmvc.common.utils.GsonUtils;
import com.springmvc.dao.OrderDetailMapper;
import com.springmvc.dao.OrderMapper;
import com.springmvc.export.request.OrderDetailReq;
import com.springmvc.export.request.OrderReq;
import com.springmvc.export.response.OrderDetailResp;
import com.springmvc.export.response.OrderResp;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ResultCode;
import com.springmvc.service.BaseService;
import com.springmvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qudi on 2018/3/28.
 */
public class OrderServiceImpl extends BaseService implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;
    @Resource(name = "orderDetailMapper")
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Result getOrderDetailsByCustomerId(OrderReq req) {
        LOGGER.info("getOrderDetailsByCustomerId...start");
        if (!checkParam(req)){
            return new Result(false,ResultCode.PARAMERROR.value(),"参数错误");
        }
        List<Long> orderIds = new ArrayList<>();
        try {
            List<OrderResp> orderResps = orderMapper.findOrdersByConsumerId(req);
            if (CollectionUtils.isEmpty(orderResps)) {
                return new Result(false, ResultCode.NOTEXIST.value(), "不存在任何记录");
            }
            for (OrderResp orderResp : orderResps) {
                if (orderResp.getId() > 0)
                    orderIds.add(orderResp.getId());
            }
            List<OrderDetailResp> orderDetailReqs = batchObtainOrderDetails(orderIds);
            if (CollectionUtils.isEmpty(orderDetailReqs)) {
                return new Result(false, ResultCode.NOTEXIST.value(), "不存在任何记录");
            }
            return new Result<>(true, ResultCode.SUCCESS.value(), "操作成功", orderDetailReqs);

        } catch (Exception e) {
            LOGGER.error("getOrderDetailsByCustomerId fail....req is {}", GsonUtils.toJSONString(req), e);

        }
        return new Result(false,ResultCode.FAILURE.value(),"操作失败");
    }

    private List<OrderDetailResp> batchObtainOrderDetails( List<Long> orderIds) throws Exception {
        return orderDetailMapper.getOrderDetailsByOrderId(orderIds);
    }

    private Boolean checkParam(OrderReq req) {
        if (req == null) {
            return false;
        }
        Long consumerId = req.getConsumerId();
        if (consumerId < 0) {
            return false;
        }
        return true;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public OrderDetailMapper getOrderDetailMapper() {
        return orderDetailMapper;
    }

    public void setOrderDetailMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }
}
