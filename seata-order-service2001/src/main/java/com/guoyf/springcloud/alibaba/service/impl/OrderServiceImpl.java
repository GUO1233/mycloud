package com.guoyf.springcloud.alibaba.service.impl;

import com.guoyf.springcloud.alibaba.dao.OrderDao;
import com.guoyf.springcloud.alibaba.domain.Order;
import com.guoyf.springcloud.alibaba.service.AccountService;
import com.guoyf.springcloud.alibaba.service.OrderService;
import com.guoyf.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guoyf
 * @Description
 * @date 2022/10/16 19:38
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderDao orderDao;

    @Resource
    AccountService accountService;

    @Resource
    StorageService storageService;


    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)//全局异常注解
    @Override
    public void create(Order order) {

        log.info("----->开始新建订单");
        //1 新建订单
        orderDao.create(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了，O(∩_∩)O哈哈~");

    }
}
