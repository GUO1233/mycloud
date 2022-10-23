package com.atguigu.factory2;
/**
 * 工厂方法：
 * 	每一个产品都创建一个工厂
 * 		如果增加新产品，不需要去修改原来的代码，都是新增的文件
 *  //以后的学习，会用到别人写的工厂模式，但是自己不会去写
 *  //工作中，自己设计软件时，就需要用到设计模式了
 */
public class Demo {

	public static void main(String[] args) {
		//生产汽车
		//1. 先获得工厂
		AudiFactory factory=new AudiA4Facotry();
		Car car = factory.getCar();
		car.run();
		
		//要进行升级
		//AudiA4和AudiA4L同时存在
		AudiFactory factory1=new AudiA4LFactory();
		Car car2 = factory1.getCar();
		car2.run();
	}
}
