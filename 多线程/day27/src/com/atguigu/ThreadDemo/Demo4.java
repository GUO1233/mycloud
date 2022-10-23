package com.atguigu.ThreadDemo;
/**
 * 此类用于演示 守护线程(了解)
 *  1. java中一个非常经典的守护线程就是  GC线程
 *  	守护其他线程：当前其他线程执行完毕，自己无论有没有执行完毕都立刻结束
 *  2. 如何设置为守护线程？
 *  	线程对象.setDaemon(true);
 *  	注意：设置守护线程一定要在线程启动之前
 */
public class Demo4 {
	public static void main(String[] args) {
		//将t2设置为守护线程  t2什么时候结束？
		Thread201 t1=new Thread201();
		t1.start();
		Thread202 t2=new Thread202();
		t2.setDaemon(true);//将t2设置为守护线程    只有t2是守护线程
		t2.start();
		
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("main---"+i);
		}
	}
}
class Thread201 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread201---"+i);
		}
	}
}
class Thread202 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread202++++"+i);
		}
	}
}
