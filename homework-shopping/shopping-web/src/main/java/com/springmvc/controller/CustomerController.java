package com.springmvc.controller;

import com.springmvc.domain.po.Customer;
import com.springmvc.export.response.CustomerResp;
import com.springmvc.export.response.MerchantResp;
import com.springmvc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by qudi on 2018/2/19.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource(name = "customerService")
    private CustomerService customerService;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomerById(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        Customer res = customerService.getCustomerById(id);
        model.addAttribute("customer", res);
        return res;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginAction")
    public String loginAction(@RequestParam String userName,@RequestParam String password){
        if (userName.trim().equals("buyer")){
            return "/product/index";
        }else if (userName.trim().equals("seller")){
//            MerchantResp merchantResp = new MerchantResp();
//            merchantResp.setMerchantName("seller");
//            ModelAndView modelAndView = new ModelAndView("index");
//            modelAndView.addObject("user",merchantResp);
//            return modelAndView;
            return "/product/index";
        }else {
            return "login";

        }
    }

}
