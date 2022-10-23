package com.atguigu.factory1;

public class AudiFactory {

	public Car getCar(String name){
		if(name.equals("AudiA4"))
			return new AudiA4L();//依然是改之前代码了  不太好
		return null;
	}
}
