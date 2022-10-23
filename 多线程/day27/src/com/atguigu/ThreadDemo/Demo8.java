package com.atguigu.ThreadDemo;
/**
 * 此类用于演示  释放锁内容
 * 1. sleep和wait的区别？
 * 	sleep不会释放锁资源
 * 	wait会释放锁资源
 *
 */
public class Demo8 {

	public static void main(String[] args) {
		
		Thread801 t=new Thread801();
		t.start();
		
		synchronized ("java") {
			for (int i = 0; i < 10; i++) {
				if(i==5){
					try {
//						Thread.sleep(2000);
						"java".wait();//子线程执行的时候，主线程执行完了吗？
						//是因为主线程执行完wait方法，会对所资源进行释放
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("main-----------"+i);
			}
		}
	}
}
class Thread801 extends Thread{
	@Override
	public void run() {
		synchronized ("java") {
			for (int i = 0; i < 10; i++) {
				if(i==5){
					try {
//						Thread.sleep(2000);
						"java".wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Thread801----"+i);
			}
		}
	}
	
}
