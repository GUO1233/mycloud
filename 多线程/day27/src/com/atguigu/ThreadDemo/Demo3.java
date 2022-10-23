package com.atguigu.ThreadDemo;
/**
 * 此类用于演示    死锁
 *1. 死锁在开发过程中是必须要避免的
 *	a. Lock锁的死锁，unlock没有执行到
 *		将unlock放在finally中
 *	b. synchronized同步代码块
 *		两个线程会出现相互等待的效果，【要避免的问题】
 */
class Demo3 {

	public static void main(String[] args) {
		Thread101 t1=new Thread101();
		t1.start();
		Thread102 t2=new Thread102();
		t2.start();
	}
}
class Thread101 extends Thread{
	@Override
	public void run() {
		synchronized ("java") {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread101---"+i);
			}
			//在第一把锁的里面
			synchronized ("python") {
				System.out.println("也是要执行的代码。。。。");//没有输出
			}
		}
	}
}
class Thread102 extends Thread{
	@Override
	public void run() {
		synchronized ("python") {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread102---"+i);
			}
			//在第一把锁的里面
			synchronized ("java") {
				System.out.println("也是要执行的代码。。。。");//没有输出
			}
		}
	}
}
