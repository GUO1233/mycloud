package com.atguigu.factory2;

public class AudiA4LFactory implements AudiFactory {

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new AudiA4L();
	}

}
