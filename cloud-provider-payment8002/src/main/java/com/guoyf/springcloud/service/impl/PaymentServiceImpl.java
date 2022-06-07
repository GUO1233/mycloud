package com.guoyf.springcloud.service.impl;

import com.guoyf.springcloud.dao.PaymentDao;
import com.guoyf.springcloud.entities.Payment;
import com.guoyf.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guoyf
 * @Description
 * @date 2022/5/15 16:05
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        int i = paymentDao.create(payment);
        return i;
    }

    @Override
    public Payment getPaymentById(Long id) {
         Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }
}
