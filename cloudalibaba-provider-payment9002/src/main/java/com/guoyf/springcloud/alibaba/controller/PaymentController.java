package com.guoyf.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoyf
 * @Description
 * @date 2022/7/25 4:23
 */
@RestController
public class PaymentController{
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }
}

