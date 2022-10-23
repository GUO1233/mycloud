package com.atguigu.homework;
/**
 * 创建和启动2个子线程，一个打印1-10之间奇数，一个打印1-10之间偶数，
（1）要求每个线程要么不打印，要么就连续打印5个数，每个数打印间隔500毫秒
（2）但两个线程不要求交替打印。//不要求五五开
 *a. 线程---
 *b. 设计模式(单例模式-工厂模式-代理模式)   23种
 */
public class Home3 {

	public static void main(String[] args) {
		Thread101 t1=new Thread101();//先启动的有优势
		Thread102 t2=new Thread102();
		t1.start();
		t2.start();
	}
}
class Thread101 extends Thread{//奇数
	@Override
	public void run() {
		while(true){//多次打印 五个数
			synchronized ("java") {
			//含义就是一个线程必须将for循环全部执行完，另外的线程才能执行(同步锁)
			
				for (int i = 1; i <=10; i=i+2) {//循环多少次？？5
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					System.out.println("奇数线程："+i);
				}
			}
		}
	}
}
class Thread102 extends Thread{//偶数
	@Override
	public void run() {
		while(true){//多次打印 五个数
			synchronized ("java") {
				for (int i = 2; i <=10; i=i+2) {//循环多少次？？5
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					System.out.println("偶数线程："+i);
				}
			}
			
		}
	}
}
