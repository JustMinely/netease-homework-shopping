package com.springmvc.controller;

import com.springmvc.common.utils.GsonUtils;
import com.springmvc.common.utils.HttpUtils;
import com.springmvc.domain.po.Merchant;
import com.springmvc.domain.po.Product;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.response.*;
import com.springmvc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品Controller
 * Created by qudi on 2018/3/1.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Resource(name = "productService")
    private ProductService productService;

    @RequestMapping("getAllProducts")
    public ModelAndView getAllProducts(HttpSession httpSession) {
        List<Product> result = new ArrayList<>();
        try {
            CustomerResp customerResp = (CustomerResp) httpSession.getAttribute("user");

            result = productService.getAllProducts();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("productList", result);
            modelAndView.addObject("user", customerResp);
            return modelAndView;
        } catch (Exception e) {
            LOGGER.error("getAllProducts...fail", GsonUtils.toJSONString(result), e);
        }
        return new ModelAndView("index");
    }

    @RequestMapping("getUnPurchasedProducts")
    public ModelAndView getUnPurchasedProducts(HttpSession httpSession) {
        List<Product> result = new ArrayList<>();
        try {
            CustomerResp customerResp = (CustomerResp) httpSession.getAttribute("user");
            result = productService.getAllUnPurchasedProducts();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("productList", result);
            modelAndView.addObject("user", customerResp);
            return modelAndView;
        } catch (Exception e) {
            LOGGER.error("getUnPurchasedProducts...fail", GsonUtils.toJSONString(result), e);
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "addProduct", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result addProduct(HttpServletResponse response, @RequestBody ProductReq req) {
        try {
            return productService.addProduct(req);
        } catch (Exception e) {
            LOGGER.error("addProduct writeJson fail...req={}", GsonUtils.toJSONString(req), e);
        }
        return null;
    }

    @RequestMapping("deleteProduct")
    @ResponseBody
    public void deleteProduct(HttpServletResponse response, @RequestBody ProductReq req) {
        try {
            HttpUtils.writeJson(response, productService.deleteUnPurcharseProduct(req));
        } catch (Exception e) {
            LOGGER.error("deleteProduct writeJson fail...req={}", GsonUtils.toJSONString(req), e);
        }
    }

    @RequestMapping("freemarkerTest")
    public String getFirstPage(Model model) {
        model.addAttribute("test", "my name is freemarker");
        return "hello";
    }

    @RequestMapping("show")
    public ModelAndView getProductDetail(@RequestParam long id) {
        try {
            ProductReq productReq = new ProductReq();
            productReq.setId(id);
            Result result = productService.getProductById(productReq);
            ProductResp datas = (ProductResp) result.getDatas();
            ModelAndView modelAndView = new ModelAndView("show");
            modelAndView.addObject("product", datas);
            CustomerResp customerResp = new CustomerResp();
            customerResp.setCustomerName("buyer");
            modelAndView.addObject("user", customerResp);
            return modelAndView;
        } catch (Exception e) {
            LOGGER.error("getProductDetail writeJson fail...id={}", GsonUtils.toJSONString(id), e);
        }
        return new ModelAndView("show");
    }

    @RequestMapping(value = "buy", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result purchaseProduct(@RequestParam long id) {
        try {
            ProductReq productReq = new ProductReq();
            productReq.setId(id);
            productReq.setProductNum(1L);
            return productService.purchaseProduct(productReq);
        } catch (Exception e) {
            LOGGER.error("purchaseProduct writeJson fail...id={}", GsonUtils.toJSONString(id), e);
        }
        return new Result(false, ResultCode.FAILURE.value(), "操作失败");
    }

    @RequestMapping("index")
    public ModelAndView index(HttpSession httpSession) {
        List<Product> result = new ArrayList<>();
        try {
            ModelAndView modelAndView = new ModelAndView("index");
            MerchantResp merchantResp =  (MerchantResp) httpSession.getAttribute("seller");
            if (merchantResp != null){
                modelAndView.addObject("user",merchantResp);
            }
            result = productService.getAllProducts();
            modelAndView.addObject("productList", result);
            return modelAndView;
        } catch (Exception e) {
            LOGGER.error("index...fail", GsonUtils.toJSONString(result), e);
        }
        return new ModelAndView("index");
    }


}
