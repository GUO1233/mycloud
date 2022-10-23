package com.atguigu.ThreadDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此类用于演示    Lock 锁
 * 	线程安全--->效率肯定低
 * a. 同步代码块
 * 	synchronized(锁对象[多个线程必须是同一个对象]){需要上锁的代码}
 * b. 同步方法
 * 	b.1 实现Runnable接口的方式  在普通方法的修饰符位置加上  synchronized
 * 		public synchronized boolean test1(){}
 *  b.2 继承Thread类的方式    在普通方法的修饰符位置加上 static synchronized
 *  	public static synchronized boolean test1(){}
 * c. Lock锁
 * 	c.1  实现Runnable接口的方式
 * 		a. 在线程中创建一个Lock对象(锁对象)
 * 			Lock lock=new ReentrantLock();
 * 		b. 在需要上锁的代码上方，执行方法lock();
 * 		c. 在需要释放锁的位置下一行，执行unlock();[一定要放在finally中]
 * 		
 *  c.2 继承Thread类的方式
 *  	a. 在线程中创建一个静态的Lock对象(锁对象)
 * 			static Lock lock=new ReentrantLock();
 * 		b. 在需要上锁的代码上方，执行方法lock();
 * 		c. 在需要释放锁的位置下一行，执行unlock();[一定要放在finally中]
 * 
 * 脑子：这个我会了！
 * 手：不，你不会
 * 
 * 三个窗口卖票的去练习锁
 * 
 * 
 * 
 * 同一个账户，两个储户去取钱
 */

public class Demo2 {

	public static void main(String[] args) {
		Account account=new Account(1001,10000);
		
		/* 采用实现接口方式的启动
		 * */
		/*Thread11 t=new Thread11(account);
		
		Thread t1=new Thread(t);
		t1.start();
		Thread t2=new Thread(t);
		t2.start();*/
		
		/*
		 * 采用竭诚方式的启动
		 */
		Thread12 t1=new Thread12(account);
		t1.start();
		Thread12 t2=new Thread12(account);
		t2.start();
	}
}
//老师在试一下继承的方式有无问题
class Thread12 extends Thread{
	private static Account account;//需要设置为static
	//第一步锁对象建立
	private static Lock lock=new ReentrantLock();//因为不是同一把锁lock
	public Thread12(Account account) {
		super();
		this.account = account;
	}
	@Override
	public void run() {
		while(true){
			try {
				lock.lock();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(account.getMoney()<=0){
					System.out.println("没钱了。。。。");
					return;//结束当前方法(run方法)--->结束当前线程
				}
				account.setMoney(account.getMoney()-100);
				System.out.println(Thread.currentThread().getName()+"取了100块，剩余"+account.getMoney());
			} finally {
				lock.unlock();
			}
			
		}
	}
	
}
class Thread11 implements Runnable{
	private Account account;
	//1. 创建锁对象    如何上锁？  
	//在需要上锁的开头  lock.lock();    在末尾  lock.unlock();
	private Lock lock=new ReentrantLock();
	public Thread11(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		while(true){
			try{
				lock.lock();//从此位置开始上锁   发生了死锁问题！！死锁是一定要避免的
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(account.getMoney()<=0){
					System.out.println("没钱了。。。。");
					return;//结束当前方法(run方法)--->结束当前线程
				}
				account.setMoney(account.getMoney()-100);
				System.out.println(Thread.currentThread().getName()+"取了100块，剩余"+account.getMoney());
			}finally{
				lock.unlock();
				//到这里结束    此时的死锁是由于return执行后，没有执行unlock方法
				//unlock一定要放在finally中 ***  finally是遇到return、break/异常...也会执行的区域
			}
		}
	}
	
	
}

