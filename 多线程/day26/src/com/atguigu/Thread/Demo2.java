package com.atguigu.Thread;
/**
 * 此类用于演示  匿名内部类创建和启动线程(了解)
 *	a. 接口的形式
 *		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("匿名内部类的线程床和启动--"+i);
				}
			}
		}).start();
	b. 继承的形式
		new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("匿名内部类的继承方式--"+i);
				}
			}
		}.start();
 */
public class Demo2 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("匿名内部类的线程创建和启动--"+i);
				}
			}
		}).start();
		
		new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("匿名内部类的继承方式--"+i);
				}
			}
		}.start();
		
		
		for (int i = 0; i < 100; i++) {
			System.out.println("main---"+i);
		}
	}
}
