package com.atguigu.factory1;

public abstract class Demo {

	public static void main(String[] args) {
		//当前项目中有一万个地方都new AudiA4了,在项目的任意一个角落
		//创建一个工厂对象
		AudiFactory factory=new AudiFactory();
		Car car=factory.getCar("AudiA4");
		//如果AudiA4升级了    --->  AudiA4L
		//项目中所有new AudiA4的地方都需要换成AudiA4L!!
		//怎么办？   工作量大    容易漏掉一些地方
		//为了解决这个弊端，采用工厂模式来试一下  改造
		//简单工厂也是违背了开闭原则的！！！
		
		Car car1=factory.getCar("AudiA4");
		
		
		Car car2=factory.getCar("AudiA4");
		
		
		Car car3=factory.getCar("AudiA4");
	}
}
