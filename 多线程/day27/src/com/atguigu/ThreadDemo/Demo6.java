package com.atguigu.ThreadDemo;
/**
 * 此类用于演示   线程的通信
 * 	1. 线程等待 (暂停)    唤醒线程(恢复)
 * 		Object
 * 			obj.wait();   让线程等待的方法
 * 				注意：谁等待？是obj吗？不是obj等待，是运行wait方法的线程等待
 * 				"java".wait();   运行wait方法的线程，在对象的监视器"java"下等待
 * 				执行完wait方法会释放锁资源  ***
 * 			obj.wait(long timeout);  等待的同时，设置了时间
 * 			obj.wait(long timeout,long nanos);
 * 
 * 			obj.notify();唤醒在obj对象监视器下等待的单个线程
 * 			obj.notifyAll() ;唤醒在obj对象监视器下等待的所有线程
 * 
 *  
 */
public class Demo6 {

	public static void main(String[] args) {
		
		Thread601 t1=new Thread601();
		t1.start();
		Thread601 t2=new Thread601();
		t2.start();
		
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i==10){
				//肯定运行在子线程等待之后
				//唤醒等待的线程
				synchronized ("java") {
					//在"java"对象监视器下，等待两个线程
//					"java".notify();//谁去调用notify方法能够唤醒子线程呢？
//					"java".notifyAll();
				}
			}
			System.out.println("main--------"+i);
		}
	}
}
class Thread601 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i==5){//判断子线程运行到第五次时，让当前(子线程)线程等待
				//来自于 Object  继承后，讲过Object类  equals
				synchronized ("java") {//锁对象和对象监视器是同一个对象
					try {
						"java".wait(2000);//谁调？必须运行在锁里面    
						//为什么？因为等待和唤醒只能运行一个   wait和notify方法能同时执行
						//wait一旦有一个线程执行，就必须从头到尾执行完才可以
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			System.out.println(Thread.currentThread().getName()+"----"+i);
		}
	}
}










