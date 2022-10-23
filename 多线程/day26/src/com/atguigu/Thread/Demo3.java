package com.atguigu.Thread;
/**
 * 此类用于演示  Thread类
 * 1. 构造器：
 * 		public Thread() :分配一个新的线程对象。其子类调用
 * 		public Thread(String name);分配一个指定名字的新的线程对象
 * 				线程的名字不是我们关注的重点
 * 		public Thread(Runnable target) :分配一个带有指定目标新的线程对象。
 * 		public Thread(Runnable target,String name) :分配一个带有指定目标新的线程对象。
 * 
 * 2. 常用方法：
 * 		常用方法系列一
 * 			public void run() :此线程要执行的任务在此处定义代码。
 *			public String getName() :获取当前线程名称。
 *			public static Thread currentThread() :返回对当前正在执行的线程对象的引用。
 			public final boolean isAlive()：测试线程是否处于活动状态。如果线程已经启动且尚未终止，则为活动状态。
 				线程结束的标识：
 					run方法执行完毕(代码运行完毕，遇到return...)
 					stop方法
 			public final int getPriority() ：返回线程优先级 
 				每个线程都有一定的优先级，线程的优先级分为10个等级[1-10]的整数  10是最高  1最低
 				Thread对于优先级提供了三个常量，MIN_PRIORITY(1) NORM_PRIORITY(5) MAX_PRIORITY(10)
 				线程的初始优先级和创建该线程的线程优先级保持一致
 					main方法默认优先级是5，t1的优先级是多少
 			public final void setPriority(int newPriority) ：改变线程的优先级
 			
 		常用方法系列二
 			public void start() :导致此线程开始执行; Java虚拟机调用此线程的run方法。
 			public static void sleep(long millis) :使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行）。
 			public static void yield()：线程的让步
 				yield只是让当前线程暂停一下，让系统的线程调度器重新调度一次，
 				希望优先级与当前线程相同或更高的其他线程能够获得执行机会，
 				  总结：当线程抢到执行权了，让出该执行权,和其他线程在重新抢执行权力，
 				  	     还是有可能被自己抢到的
 				演示效果(效果不明显)
 			void join() ：线程的插队
 			void join(long millis) ：设置插队的时间
 			void join(long millis, int nanos) ：
 				谁插队？(调用join方法线程插队)？
 				谁等待？(哪个线程执行join方法，哪个等待)？
 			public final void stop()：强迫线程停止执行(不建议大家使用)
 				建议大家使用结束run方法的方式
 */
public class Demo3 {
	

	public static void main(String[] args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		/*Thread t=new Thread();
		t.start();*/
		Thread1 t1=new Thread1("尚硅谷");//自己制定线程的名字
		//t1相当于是线程的引用
		System.out.println(t1.getName());//获取线程的名字 默认(Thread-n)
		
		System.out.println("主线程的优先级为----"+Thread.currentThread().getPriority());//6
		System.out.println("t1的优先级为----"+t1.getPriority());// 6 5
		t1.start();
		
		//查看主线程的线程名字？   有线程的引用吗？
		Thread abc = Thread.currentThread();
//		返回对当前正在执行的线程对象的引用。(此方法运行在哪个线程中就获取哪个线程的引用)
		System.out.println(abc.getName());
		for (int i = 0; i < 10; i++) {
			if(i==6){//t1大概率运行完毕
				System.out.println("t1是否存活----"+t1.isAlive());//true
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}

class Thread1 extends Thread{
	public Thread1(String name){
		super(name);//调用Thread类中有参构造(参数为String)
	}
	public Thread1(){
	}
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
//			Thread.currentThread()运行在t1线程内，
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+i);
		}
	}
	
}
