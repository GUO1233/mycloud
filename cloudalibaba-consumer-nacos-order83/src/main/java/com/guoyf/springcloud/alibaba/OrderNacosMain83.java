package com.guoyf.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author guoyf
 * @Description
 * @date 2022/7/25 4:30
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderNacosMain83{
    public static void main(String[] args){
        SpringApplication.run(OrderNacosMain83.class,args);
    }
}

