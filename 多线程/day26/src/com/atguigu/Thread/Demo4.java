package com.atguigu.Thread;
/**
 * 需求：
 * 	模拟一个售票系统！有三个售票窗口！一趟列车的总票数是100张！
 * 		提示：三个售票窗口就是三个线程，各个都是1秒钟卖一张票
 *   窗口A卖出了一张票，剩余99张！
 *   窗口B卖出了一张票，剩余98张！
 *   窗口C卖出了一张票，剩余97张！
 *   窗口C卖出了一张票，剩余96张！
 *   窗口A卖出了一张票，剩余95张！
 *   窗口B卖出了一张票，剩余94张！
 *   ...
 *   
 *如何解决线程安全问题？
 *	1. 出现多个窗口卖同一张票(错误，不允许出现的情况)
 *	2. 还出现了负票的情况
 *
 *上锁：***
 *	a:同步代码块
 *		synchronized(锁对象){需要上同步锁的代码}
 *			锁对象：任意对象(三个线程必须是同一个对象)  ***
	 			继承Thread类创建的线程，锁对象一般用   类名.class
	 			实现Runnable接口创建的线程，锁对象一般同  this
 *	b：
 *		
 *	c：
 *
 *
 *练习：
 *	1. 尝试用两种方式实现售票需求！
 *  2. 采用同步代码块去解决线程安全问题！
 */
public class Demo4 {

	public static void main(String[] args) {
		//创建三个线程    存在什么问题？各自都是100张票    
//		依然存在问题：线程不安全(暂时先不解决)
		//继承Thread的方式：需要将num进行static
		Thread201 t1=new Thread201("窗口A");
		t1.start();
		Thread201 t2=new Thread201("窗口B");
		t2.start();
		Thread201 t3=new Thread201("窗口C");
		t3.start();
		//实现接口的方式    并发执行(多核处理器是有可能存在并行执行的)
		
		/*Thread202 t=new Thread202();
//		依然存在问题：线程不安全(暂时先不解决)
		//包装
		Thread t1=new Thread(t, "窗口A");
		t1.start();
		
		Thread t2=new Thread(t, "窗口B");
		t2.start();
		
		Thread t3=new Thread(t, "窗口C");
		t3.start();*/
		
		
//		Thread202 t=new Thread202();//是否有必要在重新new一个？没必要，只需要包装三次就可以了
		
		
	}
}
class Thread202 implements Runnable{
	private int num=100;//是够有必要将num进行静态化？？？
	@Override
	public void run() {
		while(true){
			//给整体上一把锁   锁对象：任意对象(三个线程必须是同一个对象)
			synchronized(this){//this当前对象  --> t
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//循环什么结束？当票数小于等于0
				if(num<=0){
					System.out.println("票卖完了");
					return ;//break;
				}
				System.out.println(Thread.currentThread().getName()+"卖出了一张票，剩余"+--num);
			}
		}
	}
	
}
class Thread201 extends Thread{
	public Thread201(String name){
		super(name);
	}
	private static int num=100;//普通的属性(属于对象)   --->   静态属性(属于类的)
	//静态常量如果过多是一件好事还是一件坏事？？？   坏事(不建议什么做)
	@Override
	public void run() {
		while(true){
			synchronized (Thread201.class) {//用this是否可以？因为不是同一个
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//循环什么结束？当票数小于等于0
				if(num<=0){
					System.out.println("票卖完了");
					return ;//break;
				}
				System.out.println(Thread.currentThread().getName()+"卖出了一张票，剩余"+--num);
			}
		}
	}
}




