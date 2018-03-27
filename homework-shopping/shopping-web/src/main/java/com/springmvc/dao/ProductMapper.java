package com.springmvc.dao;

import com.springmvc.domain.po.Product;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.response.ProductResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by qudi on 2018/2/28.
 */
@Component
public interface ProductMapper {
    List<Product> getAllProducts() throws Exception;

    List<Product> getAllUnPurchasedProducts() throws Exception;

    int addProduct(Product product) throws Exception;

    int deleteUnPurcharseProduct(Long id) throws Exception;

    ProductResp findProductById(Long id);

    List<ProductResp> findProductsByIds(@Param("productIds") List<Long> productIds) throws Exception;

    ProductResp getProductById(Long id) throws Exception;

    int updateProductInfo(ProductReq req) throws Exception;

}
