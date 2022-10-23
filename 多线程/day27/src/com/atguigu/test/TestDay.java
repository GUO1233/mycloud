package com.atguigu.test;

class TestDay{
	public static void main(String[] args){
		Thread1 t1=new Thread1(Thread.currentThread());//需要传递一个主线程的对象
		t1.start();
		for(int i=1;i<=10;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//try-catch
			System.out.println("main---"+i);
		}
	}
}

class Thread1 extends Thread{
	private Thread thread;
	public Thread1(Thread thread){
		this.thread=thread;
	};
	public void run(){
		for(int i=1;i<=10;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//try-catch
			System.out.println("Thread1---"+i);
			if(i==5){
				//让主线程进行插队
				//线程对象.join();
				//谁插队？(main)插在谁的前面？(Thread1)
				//主线程调用join方法，join方法执行在子线程的run方法中
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
