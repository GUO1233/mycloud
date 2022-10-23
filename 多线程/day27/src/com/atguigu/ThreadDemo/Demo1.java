package com.atguigu.ThreadDemo;
/**
 * 此类用于演示     同步方法
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
 * 
 *  c.2 继承Thread类的方式
 * 
 * 
 * 
 * 同一个账户，两个储户去取钱
 */

public class Demo1 {

	public static void main(String[] args) {
		Account account=new Account(1001,10000);
		
		/* 采用实现接口方式的启动
		 * Thread1 t=new Thread1(account);
		
		Thread t1=new Thread(t);
		t1.start();
		Thread t2=new Thread(t);
		t2.start();*/
		/*
		 * 采用竭诚方式的启动
		 */
		Thread2 t1=new Thread2(account);
		t1.start();
		Thread2 t2=new Thread2(account);
		t2.start();
	}
}
//老师在试一下继承的方式有无问题
class Thread2 extends Thread{
	private static Account account;//需要设置为static
	public Thread2(Account account) {
		super();
		this.account = account;
	}
	@Override
	public void run() {
		while(true){
			//还是取钱的过程
			boolean test1 = test1();
			if(test1){
				return;
			}
		}
	}
	public static synchronized boolean test1(){//加上static，锁对象相当于换成了this.getClass()
		//一次完整的取钱经历    (只能有一个线程在运行)
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(account.getMoney()<=0){
			System.out.println("没钱了。。。。");
			return true;//结束当前方法(run方法)--->结束当前线程
		}
		account.setMoney(account.getMoney()-100);
		System.out.println(Thread.currentThread().getName()+"取了100块，剩余"+account.getMoney());
		return false;
	}
	
}
class Thread1 implements Runnable{
	private Account account;

	public Thread1(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		while(true){
//			System.out.println("-----");//没有必要放在锁里面
			boolean test1 = test1();//取钱的过程
			if(test1){
				return;
			}
//			System.out.println("====");
		}
	}
	//是否存在锁对象？？  this
	//如果子线程是通过继承的方式创建的？能行吗？不行！我们去试一试
	public synchronized boolean test1(){
		//一次完整的取钱经历    (只能有一个线程在运行)
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(account.getMoney()<=0){
			System.out.println("没钱了。。。。");
			return true;//结束当前方法(run方法)--->结束当前线程
		}
		account.setMoney(account.getMoney()-100);
		System.out.println(Thread.currentThread().getName()+"取了100块，剩余"+account.getMoney());
		return false;
	}
	
}
class Account{
	private int id;// 银行卡号
	private int money;//银行余额
	public Account(int id, int money) {
		super();
		this.id = id;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", money=" + money + "]";
	}
	
	
}

