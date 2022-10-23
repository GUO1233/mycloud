package com.atguigu.Thread;
/**
 * 此类用于演示    多线程(小难点)
 * 1. 相关概念
 * 	并发："高并发"  微博上一次瘫痪(关晓彤恋情发布)
 * 		两个或多个事件(访问服务器)，同一个时间段发生(不是绝对的)，
 * 		服务器在处理两个请求时快速的交替处理，给我们的感觉就像是在同时处理完成
 * 			例子：饮水机：张凯和阳阳拿着杯子去接水(一人一滴)   
 *  并行：两个或多个事件在同一时刻发生(电脑如果是单核的，是不可能出现并行)
 *  
 *  程序：一系列有序指令的集合！
 *  	老板-->秘书   ① 泡咖啡 ② 汇报今天的行程 ③ 订午饭
 *  	程序员-->计算机   ① 输出一个首页  ② 监控控制台  ③ ...
 *  进程：是指一个内存中运行的应用程序(qq软件)，每个进程都有一个独立的内存空间
 *  线程：线程是进程中的一个执行单元，负责当前进程中程序的执行
 *  	线程和进程的关系：一个进程中最起码要有一个或多个线程
 *  
 *  线程调度：如果有两个线程，这两个线程谁先执行谁后执行？
 *  	分时调度：公平对待。。。
 *  	抢占式调度：优先让优先级高的线程使用 CPU，如果线程的优先级相同，
 *  			     那么会随机选择一个(线程随机性)，java是采用的抢占式调度
 *  
 *  2. 线程的创建方式
 * 		a. Runnable接口
 * 			1. 创建一个普通类  class ThreadDemo1{}
 * 			2. 实现Runnable接口   class ThreadDemo1 implements Runnable{}
 * 			3. 实现接口中的抽象方法
 * 				@Override
				public void run() {
					//子线程运行的代码位置
				}
				
			需要在主线程中启动子线程(依靠主线程运行起来后在启动子线程)
 * 				1. 新建一个子线程的对象    (面向对象)
 * 					ThreadDemo1 threadDemo1=new ThreadDemo1();
 * 				2. 通过Thread进行处理(通过Thread类的构造器进行处理)
 * 					Thread是java.lang下的，不用导包
 * 					Thread t1=new Thread(threadDemo1);
 * 				3. 通过Thread的对象调用start方法！！(千万不要调用run方法)
 * 		b. Thread类
 * 			1. 创建一个普通类  class ThreadDemo2{}
 * 			2. 继承Thread类  class ThreadDemo2 extends Thread{}
 * 			3. 重写run方法
 * 				@Override
				public void run() {
					//子线程运行的代码位置
				}
			需要在主线程中启动子线程
				1. 新建一个子线程的对象
					ThreadDemo2 threadDemo2 =new ThreadDemo2(); 
				2. 直接调用start方法即可！
					threadDemo2.start();//真正运行的依然是run方法中的代码
 * 3. 实现Runnable接口方式和继承Thread类方式有什么区别？
 * 	   a. 采用实现接口的方式，可以避免java的单继承
 * 	   b. 实现接口的方式比较方便操作共享资源
 * 	   c. 
 * 	
 * 	练习：大家创建一个子线程，打印100好好学习天天向上，主线程打印100次，必须的！
 *  练习： 采用实现接口的方式，创建子线程，打印0-100个偶数
 *  	   采用继承父类的方式，创建子线程，打印0-100个奇数
 */
public class Demo1 {

	public static void main(String[] args) {
		//运行时，会启动几个线程   main(主线程) 垃圾回收(GC) 
		//创建一个子线程运行     (3个线程在运行，主线程，子线程，垃圾回收：忽略)
		//主线程和子线程必须要并发发可以？
		//想要的是test中的循环和main方法中的循环并发执行
//		必须将test中的循环放在一个子线程中
		//顺序结构     由上到下
		
		ThreadDemo1 threadDemo1=new ThreadDemo1();
//		threadDemo1.run();//这是启动子线程？？   这个依然是方法的调用，依然时单线程
		//
		Thread t1=new Thread(threadDemo1);//多态的知识点(向上转型)
		t1.start();//才是启动子线程的方法(虽然我调用的是start方法，但是真正执行的依然是run方法中的代码)
		
		
		//启动继承方式的子线程
		ThreadDemo2 threadDemo2 =new ThreadDemo2(); 
		threadDemo2.start();
		
		for (int i = 0; i < 100; i++) {
			System.out.println("main-----"+i);
		}
		
	}
}
class ThreadDemo2 extends Thread{
	public ThreadDemo2(){
		super();//调用Thread无参构造器
	}
	@Override
	public void run() {
		//该线程需要运行的代码
		for (int i = 0; i < 100; i++) {
			System.out.println("ThreadDemo2----"+i);
		}
	}
	
}

class ThreadDemo1 implements Runnable{

	@Override
	public void run() {//就是子线程运行的方法
		for (int i = 0; i < 100; i++) {
			System.out.println("ThreadDemo1-----"+i);
		}
	}
}









