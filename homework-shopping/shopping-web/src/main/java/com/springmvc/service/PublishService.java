package com.springmvc.service;

import com.springmvc.export.request.PublishReq;
import com.springmvc.export.response.Result;

/**
 * 发布商品 服务
 * Created by qudi on 2018/3/29.
 */
public interface PublishService {
    Result getProductsOfMerchant(PublishReq req);
}
