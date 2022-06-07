package com.guoyf.springcloud.service;

import com.guoyf.springcloud.entities.CommonResult;
import com.guoyf.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")   //通过Feign调用哪一个远程服务,指定服务名称
public interface PaymentFeignService {
    //调用哪一个接口？  方法的声明要与被调用的服务的接口方法一致;my:与controller中的方法一致
    @GetMapping(value = "/payment/get/{id}")         //my:这个地址如果被调用的服务的接口方法被调用的服务的接口方法的类上还有路径也要加上
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);             //my: @PathVariable("id")后面的id 就算和参数一样也不能省略(在写电商的时候可以省略呀如果一样的话，例如这个@PathVariable("id")后面的id 就可以省略)

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
