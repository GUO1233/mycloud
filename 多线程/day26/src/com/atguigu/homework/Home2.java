package com.atguigu.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

/**
 * 1、声明一个Message类，包含：发送者(sender)、接收者(recipient)、
 * 		消息内容(content)、发送时间(time)，
 * 		创建一个Message对象，并写到message.dat文件中，并再次读取显示
 *
 */
public class Home2 {
	@Test
	public void test1() {
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(
					new FileInputStream("d://bbb/message.dat"));
			
			Object obj = ois.readObject();
			Message m=(Message)obj;
			System.out.println(m);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void main(String[] args) {
		Message m=new Message("老韩", "佟校长", "涨工资", new Date());
		//1. 将m序列化到磁盘上
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(
					new FileOutputStream("d://bbb/message.dat"));
			//2. 开始写入
			oos.writeObject(m);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		
	}
}

class Message implements Serializable{
	/**
	 * 保证版本的兼容性
	 */
	private static final long serialVersionUID = 1L;
	private String sender;
	private String recipient;
	private String content;
	private Date time;
	public Message(String sender, String recipient, String content, Date time) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.content = content;
		this.time = time;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", recipient=" + recipient + ", content=" + content + ", time=" + time
				+ "]";
	}
	
}
