package com.atguigu.factory2;
/**
 * 如果要生产AudiA4,就创建一个AudiA4的工厂
 * @author 86152
 *
 */
public class AudiA4Facotry implements AudiFactory {

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new AudiA4();//AudiA4不要了，全部换成AudiA4L
	}

}
