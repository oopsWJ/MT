package com.mt.customer.controller;

import com.mt.constants.Code;
import com.mt.customer.pojo.CustomerListDTO;
import com.mt.customer.service.CustomerService;
import com.mt.pojo.Customer;
import com.mt.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Api(tags = "用户中心-用户信息管理")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Object registerCustomer(@RequestBody Customer customer) {
        return new Result(Code.OK, customerService.insertCustomer(customer));
    }

    @GetMapping("/customer")
    @ApiOperation(value = "用户查看个人信息")
    public Object getCurrentCustomer(@RequestHeader String token) {
        return  new Result(Code.OK, customerService.getCustomerByToken(token));
    }

    @GetMapping("/customers")
    @ApiOperation(value = "管理员查询所有用户")
    public Object listCustomer() {
        return new Result(Code.OK, customerService.listAllCustomer());
    }

    @RequestMapping(value = "/customer",method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户信息")
    public Object updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/customer/customerName/{customerName}")
    @ApiOperation(value = "用户名是否存在")
    public Object existCustomerName(@PathVariable("customerName") String customerName){
        if (customerService.getCustomerByName(customerName)==null){
            return new Result(Code.OK,true);
        }
        return new Result(Code.OK,false);
    }

    @GetMapping("/customer/phone/{phone}")
    @ApiOperation(value = "用户手机是否已注册 ")
    public Object existPhone(@PathVariable("phone") String phone){
        if ( customerService.getCustomerByPhone(phone)==null){
            return new Result(Code.OK,true);
        }
        return new Result(Code.OK,false);
    }

    @GetMapping("/cinema")
    @ApiOperation(value = "管理员登录后,获取管理电影院Id")
    public Object getCinemaId(@RequestHeader String token) {
        return new Result(Code.OK, "cinemaId",customerService.getCinemaId(token));
    }



}
