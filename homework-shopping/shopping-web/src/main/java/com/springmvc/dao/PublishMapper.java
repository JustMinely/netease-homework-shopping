package com.springmvc.dao;

import com.springmvc.export.request.PublishReq;
import com.springmvc.export.response.PublishResp;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by qudi on 2018/3/1.
 */
@Component
public interface PublishMapper {
    int addPublishProduct(PublishReq req) throws Exception;

    List<Long> findProductIdsByMerchantId(Long merchantId) throws Exception;

    int deletePublishUnPurchaseProduct(PublishReq req) throws Exception;

    PublishResp getPublishRespByMerchantIdAndProductId(PublishReq req) throws Exception;


}
