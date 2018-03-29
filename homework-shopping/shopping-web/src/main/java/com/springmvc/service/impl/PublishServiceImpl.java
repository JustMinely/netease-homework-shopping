package com.springmvc.service.impl;

import com.springmvc.common.utils.GsonUtils;
import com.springmvc.dao.ProductMapper;
import com.springmvc.dao.PublishMapper;
import com.springmvc.export.request.PublishReq;
import com.springmvc.export.response.ProductResp;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ResultCode;
import com.springmvc.service.BaseService;
import com.springmvc.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qudi on 2018/3/29.
 */
public class PublishServiceImpl extends BaseService implements PublishService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishServiceImpl.class);
    @Resource(name = "publishMapper")
    private PublishMapper publishMapper;
    @Resource(name = "productMapper")
    private ProductMapper productMapper;

    @Override
    public Result getProductsOfMerchant(PublishReq req) {
        LOGGER.info("getProductsOfMerchant....start");
        try {
            if (!checkParam(req)) {
                return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
            }
            Long merchantId = req.getMerchantId();
            List<Long> productIds = publishMapper.findProductIdsByMerchantId(merchantId);
            if (CollectionUtils.isEmpty(productIds)) {
                return new Result(false, ResultCode.NOTEXIST.value(), "不存在任何记录");
            }
            List<ProductResp> productResps = productMapper.findProductsByIds(productIds);
            if (CollectionUtils.isEmpty(productResps)) {
                return new Result(false, ResultCode.NOTEXIST.value(), "商品不存在");
            }
            return new Result<>(true, ResultCode.SUCCESS.value(), "操作成功", productResps);

        } catch (Exception e) {
            LOGGER.error("getProductsOfMerchant  fail...req is {}", GsonUtils.toJSONString(req), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    private Boolean checkParam(PublishReq req) {
        if (req == null)
            return false;
        Long merchantId = req.getMerchantId();
        return merchantId > 0;
    }

    public PublishMapper getPublishMapper() {
        return publishMapper;
    }

    public void setPublishMapper(PublishMapper publishMapper) {
        this.publishMapper = publishMapper;
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
}
