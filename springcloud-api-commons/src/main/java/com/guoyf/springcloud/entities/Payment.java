package com.guoyf.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoyf
 * @Description
 * @date 2022/5/15 15:34
 */
@Data     //自动生成get/set方法
@AllArgsConstructor         //自动生成有数的构造器
@NoArgsConstructor        ////自动生成带无数的的构造器
public class Payment {
    private Long id;
    private String serial;
}
