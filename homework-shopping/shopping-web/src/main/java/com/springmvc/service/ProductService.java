package com.springmvc.service;

import com.springmvc.domain.po.Product;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.response.Result;

import java.util.List;

/**
 * Created by qudi on 2018/2/28.
 */
public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getAllUnPurchasedProducts();

    Result addProduct(ProductReq productReq);

    Result deleteUnPurcharseProduct(ProductReq req);

    Result getProductById(ProductReq req);

    Result purchaseProduct(ProductReq req);



}
