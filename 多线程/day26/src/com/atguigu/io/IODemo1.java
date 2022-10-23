package com.atguigu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

/**
 * 此类用于演示    打印流和标准的输入输出流
 * 1. 打印流    处理输出流
 * 		PrintStream   字节输出打印流
 * 	构造器:
 * 		new PrintStream(File file);
 * 		new PrintStream(String pathname);
 * 		new PrintStream(OutputStream out);
 * 		
 * 	方法：
 * 		write(int b);
 * 		write(byte[] b);
 * 		write(byte[] b,int off,int len);
 * 
 * 		print(XXX xxx)
 * 		println(XXX xxx)
 * 
 * 2. System.out  的类型就是PrintStream(默认指向的设备是控制台)
 * 	  System.in   的类型就是InputStream(默认指向的是键盘)  
 * 3. 模仿Scanner扫描控制台
 * 	  next() 获取用户输入的字符串
 * 4. jdk1.7新增的异常处理内容
 * 	try(需要关闭的资源对象){
 * 		监控区
 * 	}catch(异常类型){
 * 		异常处理
 * 	}finally{
 * 		最终都要执行的区域(关闭资源)
 * 	}
 */
public class IODemo1 {
	/**
	 * 新try-catch的写法
	 */
	@Test
	public void test4(){
		//完成文件的复制
		//你没关闭资源？   声明在小括号中的对象，无论是否发生异常都会自动关闭  
		try (
				InputStream in=new FileInputStream("d://bbb/zzz.txt");
				OutputStream out=new FileOutputStream("d://ccc/aaa.txt");
				)
		{
			//读写操作  省略
			int read = in.read();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void test3(){
		//完成文件的复制
		InputStream in=null;
		OutputStream out=null;
		try {
			in=new FileInputStream("d://bbb/zzz.txt");
			out=new FileOutputStream("d://ccc/aaa.txt");
			//读写操作  省略
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Test
	public void test2(){
		MySacnner input=new MySacnner();
		System.out.println("请输入你的姓名：");
		String next = input.next();
		System.out.println("姓名是："+next);
	}
	

	@Test
	public void test1(){
		//PrintStream对象创建
		PrintStream out=null;
		try {
			out=new PrintStream("d://bbb/zzz.txt");//自己创建的out对象执行的是磁盘
			out.write(97);
			out.println("张三");
			out.print("李四");
			out.write(99);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();//不需要异常处理
		}
	}
}
