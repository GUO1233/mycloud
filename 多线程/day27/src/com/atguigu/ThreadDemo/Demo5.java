package com.atguigu.ThreadDemo;
/**
 * 此类用于演示   线程的生命周期(线程从出生到死亡的过程)
 * 1. 新生线程
 * 	刚刚实例化出来的线程对象
 * 	Thread301 t=new Thread301();//此时的t就是新生线程
 * 2. 就绪线程(可运行线程)
 * 	调完start方法之后
 * 	t.start();//start方法执行完后，t就变成了就绪线程
 * 3. 运行线程(不需要程序员写什么代码)
 * 	调用完start方法之后，线程就去抢执行权，抢到后，才进入运行状态
 * 4. 阻塞线程
 * 	线程遇到阻塞了！
 * 	sleep睡眠
 * 	join线程的插队
 * 	wait等待
 * 	IO阻塞
 * 	...
 * 		如果阻塞结束，会进入到那个状态？  会进入到就绪状态
 * 5. 死亡线程
 * 	run方法运行完毕
 * 	调用stop方法(已过时，不建议使用)
 * 
 * 线程的通信  wait等待
 */
public class Demo5 {

	public static void main(String[] args) {
		Thread301 t=new Thread301();//此时的t就是新生线程
		t.start();//start方法执行完后，t就变成了就绪线程
	}
}

class Thread301 extends Thread{

	@Override
	public void run() {
		System.out.println("abcd....");
	}
	
}
