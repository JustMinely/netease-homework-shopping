package com.springmvc.dao;

import com.springmvc.export.request.ShopCartReq;
import com.springmvc.export.response.ShopCartResp;

import java.util.List;

/**
 * Created by qudi on 2018/3/5.
 */
public interface ShopCartMapper {
    int addProduct2ShopCart(ShopCartReq req) throws Exception;

    int deleteProductFromShopCart(ShopCartReq req) throws Exception;

    List<ShopCartResp> findProductsByCustomerId(ShopCartReq req) throws Exception;

    ShopCartResp findRecordByCustomerIdAndProductId(ShopCartReq req) throws Exception;

    int updateProductInShopCart(ShopCartReq req) throws Exception;
}
