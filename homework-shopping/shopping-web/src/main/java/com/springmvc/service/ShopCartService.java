package com.springmvc.service;

import com.springmvc.export.request.ProductReq;
import com.springmvc.export.request.ShopCartReq;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ShopCartResp;

import java.util.List;

/**
 * Created by qudi on 2018/3/5.
 */
public interface ShopCartService {
    Result addOrUpdateProduct2ShopCart(ShopCartReq req);

    Result deleteProductFromShopCart(ShopCartReq req);

    List<ShopCartResp> findProductsByCustomerId(ShopCartReq req);
}
