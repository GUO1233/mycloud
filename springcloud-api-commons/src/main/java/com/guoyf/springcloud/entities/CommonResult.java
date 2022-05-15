package com.guoyf.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author guoyf
 * @Description
 * @date 2022/5/15 15:35
 */
@Data     //自动生成get/set方法
@AllArgsConstructor         //自动生成有数的构造器
@NoArgsConstructor        ////自动生成带无数的的构造器
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);//如果这行报错，请安装lombok插件
    }

}
