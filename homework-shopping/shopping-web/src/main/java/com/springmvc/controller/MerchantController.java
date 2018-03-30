package com.springmvc.controller;

import com.springmvc.export.request.PublishReq;
import com.springmvc.export.response.MerchantResp;
import com.springmvc.export.response.Result;
import com.springmvc.service.PublishService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 卖家
 * Created by qudi on 2018/3/29.
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {
    private static final Logger LOGGER = Logger.getLogger(MerchantController.class);
    @Resource(name = "publishService")
    private PublishService publishService;

    @RequestMapping("publish")
    public ModelAndView publishProduct() {
        return new ModelAndView("public");
    }

    @RequestMapping("index")
    public ModelAndView indexForMerchant() {
        LOGGER.info("indexForMerchant..start");
        try {
            MerchantResp merchantResp = new MerchantResp();
            merchantResp.setMerchantName("seller");
            PublishReq publishReq = new PublishReq();
            publishReq.setMerchantId(1L);
            Result result = publishService.getProductsOfMerchant(publishReq);

            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("productList", result.getDatas());
            modelAndView.addObject("user", merchantResp);
            return modelAndView;
        } catch (Exception e) {
            LOGGER.error("indexForMerchant..fail", e);
        }
        return new ModelAndView("login");
    }
}
