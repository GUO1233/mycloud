package com.atguigu.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MySacnner {

	public String next(){
		/*abc   --->   readLine()   --->   BufferedReader
		已知条件是   System.in
		想得到    BufferedReader  的对象*/
		String str=null;
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(
					new InputStreamReader(System.in));
			str=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	public int nextInt(){
		String next = next();
		return Integer.parseInt(next);
	}
}
