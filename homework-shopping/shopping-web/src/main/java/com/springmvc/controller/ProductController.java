package com.springmvc.controller;

import com.springmvc.common.utils.GsonUtils;
import com.springmvc.common.utils.HttpUtils;
import com.springmvc.domain.po.Product;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.response.CustomerResp;
import com.springmvc.export.response.ProductResp;
import com.springmvc.export.response.Result;
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
    public ModelAndView getAllProducts() {
        List<Product> result = new ArrayList<>();
        try {
            CustomerResp customerResp = new CustomerResp();
            customerResp.setCustomerName("buyer");


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
    public ModelAndView getUnPurchasedProducts() {
        List<Product> result = new ArrayList<>();
        try {
            CustomerResp customerResp = new CustomerResp();
            customerResp.setCustomerName("buyer");
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

    @RequestMapping(value = "addProduct",produces = "application/json;charset=UTF-8")
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
        //TODO
        try {
            HttpUtils.writeJson(response,productService.deleteUnPurcharseProduct(req));
        }catch (Exception e){
            LOGGER.error("deleteProduct writeJson fail...req={}", GsonUtils.toJSONString(req), e);
        }
    }

    @RequestMapping("freemarkerTest")
    public String getFirstPage(Model model) {
        model.addAttribute("test", "my name is freemarker");
        return "hello";
    }

    @RequestMapping("show")
    public ModelAndView getProductDetail(@RequestParam long id){
        try{
            ProductReq productReq = new ProductReq();
            productReq.setId(id);
            Result result = productService.getProductById(productReq);
            ProductResp datas = (ProductResp) result.getDatas();
            ModelAndView modelAndView = new ModelAndView("show");
            modelAndView.addObject("product",datas);
            CustomerResp customerResp = new CustomerResp();
            customerResp.setCustomerName("buyer");
            modelAndView.addObject("user",customerResp);
            return modelAndView;
        }catch (Exception e){
            LOGGER.error("getProductDetail writeJson fail...id={}", GsonUtils.toJSONString(id), e);
        }
        return new ModelAndView("show");
    }




}
