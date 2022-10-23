package com.atguigu.test;

public class Test2 {
/*
 * 采用实现接口的方式，创建子线程，打印0-100个偶数
 * 采用继承父类的方式，创建子线程，打印0-100个奇数
 */
	public static void main(String[] args) {
		//通过主线程去启动
		Thread01 t1=new Thread01();
		Thread t11=new Thread(t1);
		t11.start();
		
		Thread02 t2=new Thread02();
		t2.start();
	}
}

class Thread01 implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 100; i=i+2) {
			System.out.println("Thread1------"+i);
		}
	}
}
class Thread02 extends Thread{
	@Override
	public void run() {
		for (int i = 1; i < 100; i=i+2) {
			System.out.println("Thread2--"+i);
		}
	}
}