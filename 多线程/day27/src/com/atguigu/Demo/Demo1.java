package com.atguigu.Demo;
/**
 * 此类用于演示  设计模式
 *1. 设计模式的概念
 *	设计模式的代码都是用我们之前的知识点，写法不同，效果不同
 *	一共有23中设计模式，前辈们智慧的结晶(代码设计经验的总结)
 *2. 设计模式的好处
 *	提高：
 *		- 代码重用性 (即：相同功能的代码，不用多次编写)
		- 可读性 (即：编程规范性, 便于其他程序员的阅读和理解)
		- 可扩展性 (即：当需要增加新的功能时，非常的方便，维护成本低，称为可维护)
		- 可靠性 (即：当我们增加新的功能后，对原来的功能没有影响)
		- 使程序呈现高内聚，低耦合的特性
   3. 面向对象开发原则
   	a. 开闭原则：扩展开放，对修改关闭
   		假设：开发一个系统，1.0版本已完成！
   			  完成2.0版本，新功能的添加，
   			  可以增加java文件，但是不要去修改1.0的源代码
   		你们学习的速度，绝对赶不上我们出视频的速度
   	4. 单例模式(老韩讲过)
   		懒汉式：
   		饿汉式：
   	5. 工厂模式(演示比较复杂，所以创建一个包)
   		需求：生成奥迪汽车(AudiA4/AudiA6/AudiA8...)
   		简单工厂：
   			
   		工厂方法：
   	6. 代理模式(讲完反射之后在讲代理模式)   ***
   		静态代理：
   		动态代理：(反射知识点)
   		
   		框架Spring中AOP就是代理模式    ***
 */
public class Demo1 {

}

/**
 * 懒汉式(不会立刻实例化对象，什么时候实例化？)
 * 	存在线程安全问题，解决方案就是添加同步锁！！效率有些低
 * 		public static Singleton getSingleton(){
			synchronized (Singleton.class) {
				if(singleton==null)
					singleton=new Singleton();
			}
			return singleton;
		}
	//只有第一次才有用，效率比较低，解决方案就是在套一个if
	public static Singleton getSingleton(){
		if(singleton==null){
			synchronized (Singleton.class) {//只有第一次才有用
				if(singleton==null)
					singleton=new Singleton();
			}
		}
		return singleton;
	}
 */
class Singleton{
	private static Singleton singleton;
	private Singleton(){
		
	}
	//存在线程安全问题？
	public static Singleton getSingleton(){
		if(singleton==null){
			synchronized (Singleton.class) {//只有第一次才有用
				if(singleton==null)
					singleton=new Singleton();
			}
		}
		return singleton;
	}
}









