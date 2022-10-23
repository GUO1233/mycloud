package com.atguigu.Thread;
/**
 * 此类用于演示join方法
 *
 */
public class JoinDemo {

	public static void main(String[] args) {
		
		Thread102 t2=new Thread102();
		t2.start();
		
		for (int i = 0; i < 10; i++) {
			try {
				if(i==5){
//					t2.join(2000);//t2调用join方法
					//谁插队(调用join方法线程插队)？   t2
					//谁等待(哪个线程执行join方法，哪个等待)？  main
					//i==5时，让t2结束
					//t2.stop();
//					使用变量的值的变化来控制线程的结束
					t2.setFlag(true);
				}
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("main---"+i);
		}
	}
}
class Thread102 extends Thread{
	private boolean flag=false;
    
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread102---"+i);
			if(flag){
				return;//让run方法结束
			}
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}