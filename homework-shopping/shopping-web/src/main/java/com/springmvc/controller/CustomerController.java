package com.springmvc.controller;

import com.springmvc.common.utils.GsonUtils;
import com.springmvc.common.utils.HttpUtils;
import com.springmvc.domain.po.Customer;
import com.springmvc.export.request.OrderReq;
import com.springmvc.export.response.CustomerResp;
import com.springmvc.export.response.MerchantResp;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ResultCode;
import com.springmvc.service.CustomerService;
import com.springmvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by qudi on 2018/2/19.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Resource(name = "customerService")
    private CustomerService customerService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomerById(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        Customer res = customerService.getCustomerById(id);
        model.addAttribute("customer", res);
        return res;
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "loginAction", method = RequestMethod.POST)
    @ResponseBody
    public void loginAction(HttpServletResponse response, @RequestParam String userName, HttpSession httpSession) {
        if (userName.trim().equals("seller")) {
            MerchantResp merchantResp = new MerchantResp();
            merchantResp.setMerchantName(userName);
            httpSession.setAttribute("user",merchantResp);
            try {
                HttpUtils.writeJson(response, GsonUtils.toJSONString(merchantResp));
            } catch (IOException e) {
                LOGGER.error("loginAction fail...");
            }
        } else if (userName.trim().equals("buyer")) {
            CustomerResp customerResp = new CustomerResp();
            customerResp.setCustomerName(userName);
            httpSession.setAttribute("user",customerResp);
            try {
                HttpUtils.writeJson(response, GsonUtils.toJSONString(customerResp));
            } catch (IOException e) {
                LOGGER.error("loginAction fail...");
            }
        } else {
            try {
                HttpUtils.writeJson(response, GsonUtils.toJSONString(new CustomerResp()));
            } catch (IOException e) {
                LOGGER.error("loginAction fail...");
            }

        }
    }

    @RequestMapping("logout")
    public ModelAndView loginOut(HttpSession httpSession){
        httpSession.setAttribute("user",null);
        return new ModelAndView("index");
    }

    @RequestMapping("account")
    public ModelAndView account() {

        OrderReq orderReq = new OrderReq();
        orderReq.setConsumerId(1L);
        Result result = orderService.getOrderDetailsByCustomerId(orderReq);
        ModelAndView modelAndView = new ModelAndView("account");
        modelAndView.addObject("buyList", result.getDatas());
        return modelAndView;
    }

}
