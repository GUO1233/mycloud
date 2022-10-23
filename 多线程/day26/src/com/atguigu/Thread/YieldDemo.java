package com.atguigu.Thread;
/**
 * 此类用于演示yield方法效果
 *
 */
public class YieldDemo {

	public static void main(String[] args) {
		
		//启动子线程
		Thread101 t1=new Thread101();
		t1.start();
		
		for (int i = 0; i < 100000; i++) {
			String str=new String("hello"+i);
			str+="aaaa"+i;
		}
	}
}
class Thread101 extends Thread{
    
	@Override
	public void run() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String str=new String("hello"+i);
			str+="aaaa"+i;
			Thread.yield();//循环一次就让步一次
		}
		long end=System.currentTimeMillis();
		System.out.println("执行时间："+(end-start));
	}
	
}
