package com.guoyf.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixiaolong
 * @date 2019-12-11 16:57
 */
@Configuration
@MapperScan({"com.guoyf.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
