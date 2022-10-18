package com.guoyf.springcloud.alibaba.config;

/**
 * @author guoyf
 * @Description
 * @date 2022/10/16 22:34
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.guoyf.springcloud.alibaba.dao"})		//指定我们的接口的位置
public class MyBatisConfig {
}
