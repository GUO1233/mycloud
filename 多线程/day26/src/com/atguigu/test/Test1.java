package com.atguigu.test;
/**
 * 练习：大家创建一个子线程，打印100好好学习天天向上，
 * 主线程打印100次，必须的！
 * 
 * 1. 子线程不能再junit中启动,(junit他会在主线程结束直接退出)
 * 2. 子线程启动的代码要在主线程执行代码的上方
 * 3. 如果没有交叉效果：
 * 	a  将循环次数调大
 *  b  添加sleep方法
 *  		Thread.sleep(500);  让程序睡眠指定时间(ms)
 */
public class Test1 {

	public static void main(String[] args) {
		
		Thread1 thread1=new Thread1();
		Thread t=new Thread(thread1);
		t.start();
		
		for (int i = 0; i < 10000; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("必须的"+i);
		}
	}
}

class Thread1 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("好好学习天天向上"+i);
		}
		
	}
	
}
