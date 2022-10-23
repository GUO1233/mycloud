# File类与IO流

File类是java.io包下代表与平台无关的文件和目录，也就是说如果希望在程序中操作文件和目录都可以通过File类来完成，File类能新建、删除、重命名文件和目录。

在API中File的解释是文件和目录路径名的抽象表示形式，即File类是文件或目录的路径，而不是文件本身，因此File类不能直接访问文件内容本身，如果需要访问文件内容本身，则需要使用输入/输出流。 

> File类代表磁盘或网络中某个文件或目录的路径名称，如：/atguigu/javase/io/佟刚.jpg
>
> 但不能直接通过File对象读取和写入数据，如果要操作数据，需要IO流。File对象好比是到水库的“路线地址”，要“存取”里面的水到你“家里”，需要“管道”。

## File类

```java
package com.atguigu.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

/**
 * 此类用于演示 文件或目录(File)
 *   引入：如果java程序想要操作一个文件，java肯定会将该文件看做一个对象
 * 类(File)---对象
 * 1. File类的学习
 * 	1.1 构造器
 * 		new File(String pathName);根据路径指向磁盘上的一个文件
 * 		new File(String parent,String child);根据父级字符串和子级字符串构建一个file对象
 *  	new File(File Parent String child);根据父级File对象和子级字符串构建一个File对象
 		（my:new File("")“ ”可以是目录，指定的路径可以是不存在的）
 *  1.2 常用方法
 *  	功能：File类能新建、删除、重命名...文件和目录(操作文件中的内容:谁能够完成？IO流)
 * 		
 * 需求：
 * 	要删除有内容的目录？   File常用方法+递归
 */
public class FileDemo1 {
	/**
	 * 目录的遍历
	 * - `public String[] list()` ：返回一个String数组，表示该File目录中的所有子文件或目录。
		- `public File[] listFiles()` ：返回一个File数组，表示该File目录中的所有的子文件或目录。  
	 	public File[] listFiles(FileFilter filter)  过滤器   
	 			FileFilter  是一个抽象类(是不能够new对象)
	 	public File[] listFiles(FilenameFilter filter)   专门根据文件名称过滤的方法
	 */
	@Test
	public void test7(){
		/*File file=new File("d://aaa/bbb");
		String[] names=file.list();//返回bbb下所有一级的内容的名称(文件夹/文件)
		System.out.println(Arrays.toString(names));
		
		File[] files=file.listFiles();//返回bbb下所有一级的内容的File对象
		for (File file2 : files) {
			System.out.println(file2);
		}*/
		File file=new File("d://aaa/bbb");
//		返回bbb下符合我过滤条件一级的内容的File对象
		/*File[] files=file.listFiles(new FileFilter() {//函数式编程风格(jdk1.8开始的)
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".txt");//设置过滤条件
			}
		});*/
		File[] files=file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".txt");
			}
		});
		for (File file2 : files) {
			System.out.println(file2);
		}
	}
	/**
	 * 重命名功能的方法
		- `public boolean renameTo(File dest)`：重命名文件或目录
	 */
	@Test
	public void test6(){
		File file=new File("d://aaa/bbb/cls1.txt");
		file.renameTo(new File("d://aaa/bbb/jdbc.txt"));
	}
	/**
	 * 创建删除功能的方法
	 * - public boolean createNewFile()` ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。 
		- `public boolean delete()` ：删除由此File表示的文件或目录。  只能删除空目录。
		- `public boolean mkdir()` ：创建由此File表示的目录。
		- `public boolean mkdirs()` ：创建由此File表示的目录，包括任何必需但不存在的父目录。
	 */
	@Test
	public void test5(){
		/*File file=new File("d://aaa/bbb/abc.txt");
		System.out.println(file.exists());
		if(!file.exists()){//进入到if说明文件不存在
			try {
				file.createNewFile();//要求文件前的目录是存在的才行
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("成功");*/
		/*File file=new File("d://aaa/bbb/ccc/ddd/eee/fff");
//		file.mkdir();//只能创建一级目录
		file.mkdirs();//创建多级目录的
		System.out.println("success");*/
		
		File file=new File("d://aaa/bbb/ccc");
		if(file.exists()){//如果存在会进入到if
			file.delete();//删除文件和空目录
		}
		System.out.println("删除成功");
		//有需求，要删除有内容的目录？   File讲完就可以办到
		
	}
	/**
	 * 判断功能的方法
	 *  - public boolean exists()` ：此File表示的文件或目录是否实际存在。
		- `public boolean isDirectory()` ：此File表示的是否为目录。
		- `public boolean isFile()` ：此File表示的是否为文件。
		- `public isAbsolute()`：判断File对象对应的文件或目录是否是绝对路径
		- `public boolean canRead()`：判断File对象对应的文件或目录是否可读
		- `public boolean canWrite()`：判断File对象对应的文件或目录是否可写
		- `public boolean isHidden()`：判断File对象对应的文件或目录是否是否隐藏
	 */
	@Test
	public void test4(){
		File file=new File("./src/MySQL.txt");
		System.out.println(file.exists());
		System.out.println(file.isDirectory());
		System.out.println(file.isFile());
		System.out.println(file.isAbsolute());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		System.out.println(file.isHidden());
	}
	/**
	 * 各种路径问题
	 * 	绝对路径：从盘符开始的路径    
	 *  - public String getPath() ` ：将此File转换为路径名字符串。 
		- `public String getAbsolutePath() ` ：返回此File的绝对路径名字符串。
		- `String getCanonicalPath()`：返回此File对象所对应的规范路径名。
			应用：有相对路径的情况下，可以解析./和../
	 */
	@Test
	public void test3(){
		//1. 采用绝对路径执行day24-src-MySQL.txt
		File file=new File("D://workspace/atguigu/JavaSE/Java0316/day24/src/MySQL.txt");
		//2. 采用相对路径指向day24-src-MySQL.txt
		//   ./      当前文件所在的文件夹(java中是当前项目下)
		//   ../	 当前文件所在的文件夹的上一级     没有.../符号
		//如果我想要当前文件夹的上两级   怎么办？
		File file1=new File("../src/MySQL.txt");
		System.out.println(file1.getPath());
		System.out.println(file1.getAbsolutePath());
		try {
			System.out.println(file1.getCanonicalPath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取文件和目录基本信息的方法
	 *  - `public String getName()`  ：返回由此File表示的文件或目录的名称。  
		- `public long length()`  ：返回由此File表示的文件的长度。 
		- `public String getPath() ` ：将此File转换为路径名字符串。 
		- `public long lastModified()`：返回File对象对应的文件或目录的最后修改时间（毫秒值）
	 */
	@Test
	public void test2(){
		File file=new File("d://aaa/bbb/cls1.txt");
		System.out.println(file.getName());
		System.out.println(file.length());//获取什么长度：文件中内容的字节长度
		//utf-8 一个中文是3个字节   gbk  一个中文是2个字节
		System.out.println(file.getPath());
		System.out.println(file.lastModified());//文件的最后修改时间，毫秒数
		//毫秒数是可以转为Date类型，查看效果
	}
	
	//junit(单元测试)之前讲过没有？
	/**
	 * junit使用步骤
	 * 1. 导包
	 * 2. 在public类中创建一个public方法
	 * 3. 在方法上使用注解  @Test
	 */
	//FIle类中的构造器
	@Test
	public void test1(){
		//文件的来源是磁盘(本地硬盘)
//		File file=new File("d://java1.txt");//让java中的file对象指向D盘下的java.txt
		File parent=new File("d://aaa1");
//		File file=new File("d://aaa","python.txt");//file对象指向的是d盘下aaa文件夹中的python.txt文件
		File file=new File(parent, "abc.txt");//file对象指向的是d盘下aaa1文件夹中的abc.txt
		
		System.out.println(file.exists());//判断该文件或目录是否存在
		
	}
	

}

```

## File类的递归删除目录

```java
package com.atguigu.file;

import java.io.File;

import org.junit.Test;

/**
 * 此类用于递归的演示
 *  循环的种类:
 *  	while循环/for循环/do-while循环/foreach循环/递归[方法自己调用自己]
 *  
 */
public class Demo1 {
	
//	要删除有内容的目录？   File常用方法+递归
	@Test
	public void test2(){
		File file=new File("d://aaa");//有内容的目录
		deleteFile(file);
		System.out.println("删库成功");
	}
	//此方法是产出文件夹的
	public void deleteFile(File file){
		if(file!=null&&file.isDirectory()){//file不是null,并且是个目录
			//讲该目录中的所有内容查询出来
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {//循环删除该数组中的所有内容
				//注意：该数组中也有可能存在目录
				deleteFile(file2);
			}
		}
		//执行此代码之前一定要保证file文件或是空文件夹
		file.delete();
	}


	@Test
	public void test1(){
		/*for(;;){//该死循环短时间内不会造成内存溢出
			System.out.println(100);
		}*/
		method(1);
	}
	
//	递归[方法自己调用自己]    递归的死循环(短时间内就会造成内存溢出)
	//使用递归一定要注意的问题是：循环的出口一定要确定
	public void method(int i){//入栈和出栈
		if(i<3){
			System.out.println(i);
			i++;
			method(i);
		}
		return;//隐藏的代码   才是方法结束的代码
	}
	
	
	
}

```

## IO   输入流   FileInputStream

```java
package com.atguigu.io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * 此类用于演示  IO
 * 1. 什么是IO
 * 	IO: input      output
 * 
 * 2. IO流的分类
 * 	a. 方向上分(我们是程序员，肯定是以程序为主)
 * 		输入流: 数据位置(磁盘,网络上,硬件设备...) ---> 程序(java代码)  
 * 		输出流: 程序(java代码) ---->   数据位置(磁盘,网络上,硬件设备...)
 *  b. 数据的类型分
 *  	字节流 ：处理单位是字节(图片/音频/视频...)
 *  	字符流： 处理单位是字符(文本数据)
 *  c. 角色不同分
 *  	节点流(原始流)：IO流的四大根类就是节点流
 *  	处理流：在节点流的基础上处理一下(要么是效率提高，要么就是增加好用的方法)
 * 3. IO流是一个非常庞大的体系(类似于异常)，但是IO流用起来都类似(不是读就是写)
 		IO流的四大根类：
 			字节输入流：InputStream
 				
 			字节输出流：OutputStream
 			字符输入流：Reader
 			字符输出流：Writer
 	
 */
public class IoDemo1 {

	/**
	 * 主要介绍字节输入流  InputStream(抽象类)   
	 * 	第一大子类：FileInputStream(指向磁盘上的数据)
	 * 		构造器：
	 * 			new FileInputStream(File file);
	 * 			new FileInputStream(String pathname);//节省代码
	 * 			（my:文件找不到会报错！！！，File对象相当于路径，流对象相当于在这个地址上铺设的管道。）
	 * 		读的方法：
	 * 			read();   执行一次读一个字节，读到没有内容时在读会返回-1
	 			read(byte[] b);执行一次将读到的数据存放在byte数组中，返回值是读取的字节数
				read(byte[] b,int off,int len);[用的比较少]从文件头读取len个字节，
					  		存储到数组时，是从数组索引位置为off的位置开始存
	 */
	@Test
	public void test3(){
//		File file=new File("d://java.txt");//长篇小说   4G
		InputStream in=null;
		try {
			in=new FileInputStream("d://java.txt");//此输入流读取的是file对象执行的文件
			byte[] b=new byte[10];
			int len=in.read(b, 4, 5);
			//从第2个字节位置开始读5个字节?
			for (byte c : b) {
				System.out.print(c+",");
			}
			System.out.println("len="+len);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();//IO流是需要关闭的资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test2(){
		File file=new File("d://java.txt");//长篇小说   4G
		InputStream in=null;
		try {
			in=new FileInputStream(file);//此输入流读取的是file对象执行的文件
			/*
			 * 找到了循环结束的位置
			 * byte[] b=new byte[10];//用来存储读取的数据[一般用1024]
			//1. 数组的长度大于文件的字节数       读一次就ok  len就是文件的字节数
			int len = in.read(b);//返回值是读取的字节个数
			//2. 数组的长度小于文件的字节数        读一次读不完   
			for (byte c : b) {
				System.out.print((char)c);
			}
			System.out.println("len="+len);
			System.out.println("------------------");
			//第二次读
			b=new byte[10];
			int len1 = in.read(b);//这次读是没有数据的？返回值是-1
			//又找到了循环结束的位置
			for (byte c : b) {
				System.out.print((char)c);
			}
			System.out.println("len1="+len1);*/
			
			byte[] b=new byte[10];
			int len=0;
			while((len=in.read(b))!=-1){
				for (byte c : b) {
					System.out.print((char)c);
				}
				b = new byte[10];
				/*
				* (my:in.read(b)是 把读的数据放入
				* b数组中，一次没读完会在调用in.read(b)会继续从上次没读的数据开始往下读，并覆盖b数组原有读的数据。
				* 这时可以b=new byte[10],new一个新的数组。（除非in=new FileInputStream("d://java.txt")，
				* 除非新new一个读入流对象，否则读的数据都是接着上次读的位置继续往下读）
				*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();//IO流是需要关闭的资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test1(){
		File file=new File("d://java.txt");
		InputStream in=null;
		try {
			in=new FileInputStream(file);//此输入流读取的是file对象执行的文件
			/*研究循环结束的位置在哪？
			int i = in.read();//i是什么？读回来的一个字节
			System.out.println(i);//112
			int j = in.read();
			System.out.println(j);//117
			int k = in.read();//没有数据！返回什么？-1
			System.out.println(k);*/
			//就可以去写循环了，可以确定循环结束的位置
			int i=0;
			while((i=in.read())!=-1){
				System.out.print((char)i);
			}
//			练习：读取一个文件中的内容到控制台
			
		} catch (FileNotFoundException e) {
			//FileNotFoundException文件找不到
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();//IO流是需要关闭的资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
```

## IO   输出流   FileOutputStream

```java
package com.atguigu.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;


public class IoDemo2 {
	/**
	 * 此类用于演示  字节输出流
	 * 	1. OutputStream   抽象类
	 *     第一大子类：FileOutputStream (指向磁盘)
	 *    a. 构造器
	 *    	new FileOutputStream(File file);根据file对象构建字节输出流
	 *    	new FileOutputStream(Sring pathname);根据文件路径构建对象
	 *    		会将原文件中的内容清空后在写入！想要追加？怎么办？采用新的构造器
	 *      new FileOutputStream(File file,boolean append);根据file对象构建字节输出流
	 *    	new FileOutputStream(Sring pathname,boolean append);根据文件路径构建对象
	 *    		文件可以不存在，但是文件所在的目录必须存在
	 *    b. 写的方法
	 *    	write(int b);执行一次写入一个字节   和  InputStream中的read()  配合使用
	 *      write(byte[] b);执行一次写入整个数组中的数据
	 *      write(byte[] b, int off, int len);从数组的off索引位置的字节开始写，写len个
	 *      	和read(b)   去配合使用
	 */
	@Test
	public void test1(){
//		File file=new File("d://aaa/cls.txt");
		OutputStream out=null;
		try {
			//1. 流对象的创建
			out=new FileOutputStream("d://aaa/cls1.txt");//指向的是d://aaa/cls.txt
			//2. 开始写内容
//			out.write(98);//97    a    abbbbbbcccccc      ccccccc
			byte[] b={98,99,100,101,102};//bcdef
//			out.write(b);
			out.write(b, 1, 3);//99 100 101    cde
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();//3. 关闭资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

```

