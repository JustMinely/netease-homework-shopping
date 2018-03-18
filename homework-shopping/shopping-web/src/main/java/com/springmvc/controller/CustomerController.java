package com.springmvc.controller;

import com.springmvc.domain.po.Customer;
import com.springmvc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

}
