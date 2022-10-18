package com.guoyf.springcloud.alibaba.controller;

import com.guoyf.springcloud.alibaba.domain.CommonResult;
import com.guoyf.springcloud.alibaba.domain.Order;
import com.guoyf.springcloud.alibaba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoyf
 * @Description
 * @date 2022/10/16 22:16
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
