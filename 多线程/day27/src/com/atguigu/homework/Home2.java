package com.atguigu.homework;
/**
 * 银行有一个账户，账户中有10000块钱。
有两个储户(两个线程)一起从该账户取钱，每次去100，每次取完打印账户余额。
要求：要保证数据的正确性和完整性	
 *
 */
public class Home2 {

	public static void main(String[] args) {
		Account account=new Account(1001,10000);
		
		Thread1 t=new Thread1(account);
		
		Thread t1=new Thread(t);
		t1.start();
		Thread t2=new Thread(t);
		t2.start();
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
			//一次完整的取钱经历    (只能有一个线程在运行)
			synchronized (account) {//锁对象用account行不行？？？
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(account.getMoney()<=0){
					System.out.println("没钱了。。。。");
					return;//结束当前方法--->结束当前线程
				}
				account.setMoney(account.getMoney()-100);
				System.out.println(Thread.currentThread().getName()+"取了100块，剩余"+account.getMoney());
			}
			
		}
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
