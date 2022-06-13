package com.guoyf.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author guoyf
 * @Description
 * @date 2022/6/11 15:10
 */
//my:这里的服务降级方法只对调用远程接口有用，比如（在调用远程接口的时候出现异常和超时），（如果自身conntroller出现异常或超时，有全局就走全局）
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }
}
