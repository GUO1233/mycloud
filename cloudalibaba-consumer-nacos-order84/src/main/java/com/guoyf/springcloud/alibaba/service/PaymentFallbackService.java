package com.guoyf.springcloud.alibaba.service;

import com.guoyf.springcloud.entities.CommonResult;
import com.guoyf.springcloud.entities.Payment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author guoyf
 * @Description
 * @date 2022/9/22 1:22
 */
@Component
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}

