package com.springmvc.service.impl;

import com.springmvc.common.Constant;
import com.springmvc.common.utils.GsonUtils;
import com.springmvc.dao.*;
import com.springmvc.domain.enums.OperationEnum;
import com.springmvc.domain.enums.ProductEnum;
import com.springmvc.domain.po.FlowLog;
import com.springmvc.domain.po.Product;
import com.springmvc.export.request.OrderDetailReq;
import com.springmvc.export.request.OrderReq;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.request.PublishReq;
import com.springmvc.export.response.ProductResp;
import com.springmvc.export.response.PublishResp;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ResultCode;
import com.springmvc.service.BaseService;
import com.springmvc.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品Service实现类
 * Created by qudi on 2018/2/28.
 */
public class ProductServiceImpl extends BaseService implements ProductService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource(name = "productMapper")
    private ProductMapper productMapper;
    @Resource(name = "flowLogMapper")
    private FlowLogMapper flowLogMapper;
    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;
    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;
    @Resource(name = "orderDetailMapper")
    private OrderDetailMapper orderDetailMapper;

    @Resource(name = "publishMapper")
    private PublishMapper publishMapper;

    @Override
    public List<Product> getAllProducts() {
        try {
            return productMapper.getAllProducts();
        } catch (Exception e) {
            LOGGER.error("getAllProducts...fail", e);
        }
        return null;
    }

    @Override
    public List<Product> getAllUnPurchasedProducts() {
        try {
            return productMapper.getAllUnPurchasedProducts();
        } catch (Exception e) {
            LOGGER.error("getAllUnPurchasedProducts...fail", e);
        }
        return null;
    }

    @Override
    public Result addProduct(ProductReq productReq) {
        if (!checkParam(productReq)) {
            return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
        }
        TransactionStatus status = null;
        try {
            status = transactionManager.getTransaction(define());
            productReq.setProductStatus(ProductEnum.UnPurchased.value());
            int row4Product = productMapper.addProduct(productReq);
            if (row4Product != 1) {
                throw new Exception("insertProduct...fail");
            }

            PublishReq publishReq = new PublishReq();
            publishReq.setMerchantId(productReq.getMerchantId());
            publishReq.setProductId(productReq.getId());
            int row4Publish = publishMapper.addPublishProduct(publishReq);
            if (row4Publish != 1) {
                throw new Exception("insert publish....fail");
            }
            FlowLog flowLog = new FlowLog(OperationEnum.ADD.value(),
                    Constant.PUBLISH_MERCHANT_PRODUCT,
                    String.valueOf(productReq.getId()),
                    GsonUtils.toJSONString(productReq),
                    String.valueOf(productReq.getMerchantId()));
            flowLogMapper.insertFlowLog(flowLog);
            transactionManager.commit(status);
            return new Result(true, ResultCode.SUCCESS.value(), "发布商品成功");
        } catch (Exception e) {
            if (status != null)
                transactionManager.rollback(status);
            LOGGER.error("addProduct fail...productReq is {}", GsonUtils.toJSONString(productReq), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    @Override
    public Result deleteUnPurcharseProduct(ProductReq req) {
        if (!checkParam4Delete(req)) {
            return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
        }
        TransactionStatus status = null;
        try {
            Long merchantId = req.getMerchantId();
            Long productId = req.getId();
            PublishReq publishReq = new PublishReq();
            publishReq.setMerchantId(merchantId);
            publishReq.setProductId(productId);
            if (merchantId == null) {
                throw new Exception("MerchantId of ProductReq is null....");
            }
            if (productId == null) {
                throw new Exception("productId of ProductReq is null....");
            }
            ProductResp productResp = productMapper.findProductById(productId);
            if (productResp == null) {
                throw new Exception("can not find product by productId...");
            }
            Integer productStatus = productResp.getProductStatus();
            if (productStatus == ProductEnum.Purchased.value()) {
                throw new Exception("the product is purchased,can not be removed...");
            }
            PublishResp publishResp = publishMapper.getPublishRespByMerchantIdAndProductId(publishReq);
            if (publishResp == null) {
                throw new Exception("can not find published product...");
            }
            status = transactionManager.getTransaction(define());
            int row4deleteProduct = productMapper.deleteUnPurcharseProduct(productId);
            if (row4deleteProduct != 1) {
                throw new Exception("delete product By id fail...");
            }
            int row4deletePublishProduct = publishMapper.deletePublishUnPurchaseProduct(publishReq);
            if (row4deletePublishProduct != 1) {
                throw new Exception("delete publish  fail...");
            }
            FlowLog flowLog = new FlowLog(OperationEnum.DELETE.value(),
                    Constant.DELETE_PUBLISHED_MERCHANT_PRODUCT,
                    String.valueOf(productId),
                    GsonUtils.toJSONString(req),
                    String.valueOf(merchantId));
            flowLogMapper.insertFlowLog(flowLog);
            transactionManager.commit(status);
            return new Result(true, ResultCode.SUCCESS.value(), "删除成功");
        } catch (Exception e) {
            if (status != null) {
                transactionManager.rollback(status);
            }
            LOGGER.error("deleteUnPurcharseProduct fail...productReq is {}", GsonUtils.toJSONString(req), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    @Override
    public Result getProductById(ProductReq req) {
        LOGGER.info("getProductById req={}", GsonUtils.toJSONString(req));
        try {
            if (req == null || req.getId() < 0) {
                return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
            }
            Long id = req.getId();
            ProductResp productResp = productMapper.findProductById(id);
            if (productResp == null) {
                return new Result(false, ResultCode.NOTEXIST.value(), "商品不存在");
            }
            return new Result<>(true, ResultCode.SUCCESS.value(), "成功", productResp);
        } catch (Exception e) {
            LOGGER.error("getProductById fail...productReq is {}", GsonUtils.toJSONString(req), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    @Override
    public Result purchaseProduct(ProductReq req) {
        LOGGER.info("purchaseProduct...start");
        if (!checkParam4Purchase(req)) {
            return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
        }
        TransactionStatus status = null;
        try {
            Long productId = req.getId();
            Long productNum = req.getProductNum();
            ProductResp productResp = productMapper.findProductById(productId);
            if (productResp == null) {
                throw new Exception("can not find product by id, productId is {}" + productId);
            }
            //开启事务
            status = transactionManager.getTransaction(define());
            //插入order记录
            String totalAmount = String.valueOf(Long.parseLong(productResp.getProductPrice()) * productNum);
            OrderReq orderReq = new OrderReq();
            orderReq.setConsumerId(1L);
            orderReq.setTotalAmount(totalAmount);
            int insertRow = orderMapper.addOrder(orderReq);
            if (insertRow <= 0) {
                throw new Exception("insert order fail...");
            }
            Long orderReqId = orderReq.getId();
            //插入order_detail记录
            OrderDetailReq orderDetailReq = new OrderDetailReq();
            orderDetailReq.setOrderId(orderReqId);
            orderDetailReq.setProductId(productId);
            orderDetailReq.setProductName(productResp.getProductName());
            orderDetailReq.setProductPrice(productResp.getProductPrice());
            orderDetailReq.setProductUrl(productResp.getProductUrl());
            orderDetailReq.setProductNum(productNum.toString());
            orderDetailReq.setProductAmount("1");
            int addRow4detail = orderDetailMapper.addOrderDetail(orderDetailReq);
            if (addRow4detail <= 0) {
                throw new Exception("add order detail fail...req is {}" + GsonUtils.toJSONString(orderDetailReq));
            }
            //修改商品状态
            req.setProductStatus(ProductEnum.Purchased.value());
            int updateRow4Product = productMapper.updateProductInfo(req);
            if (updateRow4Product <= 0) {
                throw new Exception("updateProductInfo fail req is {}" + GsonUtils.toJSONString(req));
            }
            transactionManager.commit(status);
            return new Result(true, ResultCode.SUCCESS.value(), "操作成功");
        } catch (Exception e) {
            if (status != null) {
                transactionManager.rollback(status);
            }
            LOGGER.error("purchaseProduct...fail,req is {}", GsonUtils.toJSONString(req), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    private boolean checkParam4Delete(ProductReq req) {
        if (req == null) {
            return false;
        }
        Long merchantId = req.getMerchantId();
        Long id = req.getId();
        if (merchantId == null || merchantId < 0 || id == null || id < 0) {
            return false;
        }
        return true;
    }

    private Boolean checkParam(ProductReq req) {
        if (req == null) {
            return false;
        }
        String productName = req.getProductName();
        String productPrice = req.getProductPrice();
        String productAmount = req.getProductAmount();
        String productDesc = req.getProductDesc();
        String productContent = req.getProductContent();
        String productUrl = req.getProductUrl();
        Long merchantId = req.getMerchantId();
        if (StringUtils.isBlank(productName) ||
                StringUtils.isBlank(productPrice) ||
                StringUtils.isBlank(productAmount) ||
                StringUtils.isBlank(productDesc) ||
                StringUtils.isBlank(productContent) ||
                StringUtils.isBlank(productUrl) ||
                merchantId == null || merchantId < 0)
            return false;
        return true;
    }

    private Boolean checkParam4Purchase(ProductReq req) {
        if (req == null) {
            return false;
        }
        Long id = req.getId();
        Long productNum = req.getProductNum();
        if (id <= 0 || productNum < 0) {
            return false;
        }
        return true;
    }


    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public FlowLogMapper getFlowLogMapper() {
        return flowLogMapper;
    }

    public void setFlowLogMapper(FlowLogMapper flowLogMapper) {
        this.flowLogMapper = flowLogMapper;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
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

    public PublishMapper getPublishMapper() {
        return publishMapper;
    }

    public void setPublishMapper(PublishMapper publishMapper) {
        this.publishMapper = publishMapper;
    }
}
