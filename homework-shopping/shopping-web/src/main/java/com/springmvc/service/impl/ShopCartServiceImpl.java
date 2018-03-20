package com.springmvc.service.impl;

import com.springmvc.common.Constant;
import com.springmvc.common.utils.CollectionUtil;
import com.springmvc.common.utils.GsonUtils;
import com.springmvc.dao.CustomerMapper;
import com.springmvc.dao.FlowLogMapper;
import com.springmvc.dao.ProductMapper;
import com.springmvc.dao.ShopCartMapper;
import com.springmvc.domain.enums.OperationEnum;
import com.springmvc.domain.po.Customer;
import com.springmvc.domain.po.FlowLog;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.request.ShopCartReq;
import com.springmvc.export.response.ProductResp;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ResultCode;
import com.springmvc.export.response.ShopCartResp;
import com.springmvc.service.BaseService;
import com.springmvc.service.ShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Service实现类
 * Created by qudi on 2018/3/5.
 */
public class ShopCartServiceImpl extends BaseService implements ShopCartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopCartServiceImpl.class);

    @Resource(name = "shopCartMapper")
    private ShopCartMapper shopCartMapper;
    @Resource(name = "flowLogMapper")
    private FlowLogMapper flowLogMapper;
    @Resource(name = "productMapper")
    private ProductMapper productMapper;
    @Resource(name = "customerMapper")
    private CustomerMapper customerMapper;


    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;


    @Override
    public Result addOrUpdateProduct2ShopCart(ShopCartReq req) {
        if (!checkParam4Add(req)) {
            return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
        }
        Long customerId = req.getCustomerId();
        Long productId = req.getProductId();
        try {
            Customer customer = customerMapper.getCustomerById(customerId);
            if (customer == null)
                return new Result(false, ResultCode.NOTEXIST.value(), "当前用户不存在");
            ProductResp productResp = productMapper.findProductById(productId);
            if (productResp == null) {
                return new Result(false, ResultCode.NOTEXIST.value(), "当前商品不存在");
            }
            ShopCartReq shopCartReq = new ShopCartReq();
            shopCartReq.setCustomerId(customerId);
            shopCartReq.setProductId(productId);
            shopCartReq.setProductQuantity(req.getProductQuantity() == null ? 1 : req.getProductQuantity());
            ShopCartResp shopCartResp = shopCartMapper.findRecordByCustomerIdAndProductId(req);
            if (shopCartResp == null) {
                int addRow = shopCartMapper.addProduct2ShopCart(req);
                if (addRow != 1)
                    throw new Exception("add shopCart fail....");
            } else {
                shopCartMapper.updateProductInShopCart(req);
            }
            return new Result(true, ResultCode.SUCCESS.value(), "操作成功");
        } catch (Exception e) {
            LOGGER.error("addProduct2ShopCart..fail req is {}", GsonUtils.toJSONString(req), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "添加购物车操作失败");
    }

    @Override
    public Result deleteProductFromShopCart(ShopCartReq req) {
        if (!checkParam4Add(req)) {
            return new Result(false, ResultCode.PARAMERROR.value(), "参数错误");
        }
        TransactionStatus status = null;
        try {
            Long customerId = req.getCustomerId();
            Customer customer = customerMapper.getCustomerById(customerId);
            if (customer == null) {
                throw new Exception("can not find current customer...");
            }
            status = transactionManager.getTransaction(define());
            int deleteRow = shopCartMapper.deleteProductFromShopCart(req);
            if (deleteRow != 1) {
                throw new Exception("delete shop cart record fail...");
            }
            FlowLog flowLog = new FlowLog(OperationEnum.DELETE.value(),
                    Constant.DELETE_SHOP_CART_PRODUCT,
                    String.valueOf(req.getProductId()),
                    GsonUtils.toJSONString(req),
                    String.valueOf(req.getCustomerId()));
            int insertRow = flowLogMapper.insertFlowLog(flowLog);
            if (insertRow != 1) {
                throw new Exception("insert flow log fail...");
            }
            transactionManager.commit(status);
            return new Result(true, ResultCode.SUCCESS.value(), "操作成功");
        } catch (Exception e) {
            if (status != null) {
                transactionManager.rollback(status);
            }
            LOGGER.error("deleteProductFromShopCart...fail,req is {}", GsonUtils.toJSONString(req), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    @Override
    public List<ShopCartResp> findProductsByCustomerId(ShopCartReq req) {
        if (!checkParam(req)) {
            return new ArrayList<>();
        }
        Long customerId = req.getCustomerId();
        try {
            Customer customer = customerMapper.getCustomerById(customerId);
            if (customer == null) {
                throw new Exception("can not find current customer...");
            }
            List<ShopCartResp> shopCartResps = shopCartMapper.findProductsByCustomerId(req);
            if (CollectionUtil.isEmpty(shopCartResps)) {
                return new ArrayList<>();
            }
            shopCartResps = getAllProductsInfo(shopCartResps);
            return shopCartResps;
        } catch (Exception e) {
            LOGGER.error("findProductsByCustomerId...fail,req = {}", GsonUtils.toJSONString(req), e);
        }
        return new ArrayList<>();
    }

    private List<ShopCartResp> getAllProductsInfo(List<ShopCartResp> shopCartResps) throws Exception {
        List<Long> productIds = new ArrayList<>();
        for (ShopCartResp resp : shopCartResps) {
            if (resp.getProductId() != null)
                productIds.add(resp.getProductId());
        }
        List<ProductResp> productResps = productMapper.findProductsByIds(productIds);
        if (CollectionUtil.isEmpty(productResps)) {
            return new ArrayList<>();
        }
        for (Integer index = 0; index < productResps.size(); index++) {
            shopCartResps.get(index).setProductName(productResps.get(index).getProductName());
            shopCartResps.get(index).setProductPrice(productResps.get(index).getProductPrice());
            shopCartResps.get(index).setProductUrl(productResps.get(index).getProductUrl());
            shopCartResps.get(index).setProductStatus(productResps.get(index).getProductStatus());
        }
        return shopCartResps;
    }

    private Boolean checkParam(ShopCartReq req) {
        if (req == null) {
            return false;
        }
        Long customerId = req.getCustomerId();
        if (null == customerId || customerId <= 0) {
            return false;
        }
        return true;
    }

    private Boolean checkParam4Add(ShopCartReq req) {
        if (req == null)
            return false;
        Long customerId = req.getCustomerId();
        Long id = req.getProductId();
        if (null == customerId || customerId <= 0 || null == id || id <= 0)
            return false;
        return true;
    }

    public ShopCartMapper getShopCartMapper() {
        return shopCartMapper;
    }

    public void setShopCartMapper(ShopCartMapper shopCartMapper) {
        this.shopCartMapper = shopCartMapper;
    }

    public FlowLogMapper getFlowLogMapper() {
        return flowLogMapper;
    }

    public void setFlowLogMapper(FlowLogMapper flowLogMapper) {
        this.flowLogMapper = flowLogMapper;
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

}
