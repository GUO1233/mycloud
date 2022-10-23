package com.atguigu.ThreadDemo;
/**
 * 案例演示
 *2. 生产者和消费者的问题
 *  	吃货(吃包子)  
 *  		判断现在是否有包子
 *  			有：  吃(吃完之后，会唤醒包子铺等待的线程，让生产包子的线程继续运行)
 *  			没有：   当前吃的线程要进行等待(等待包子铺去生产包子)
 *      包子铺(生产包子)
 *      	判断现在是否有包子
 *      		有：当前生产包子的线程进行等待(等待吃货去吃)
 *      		没有：  立刻去做(如果做完了，唤醒吃货的线程，让吃货去吃)
 */
public class Demo7 {

	public static void main(String[] args) {
		BaoZi baozi=new BaoZi();
		
		ChiHuo chihuo=new ChiHuo(baozi);
		chihuo.start();
		
		BaoZiPu baozipu=new BaoZiPu(baozi);
		baozipu.start();
				
	}
}
class ChiHuo extends Thread{
	private BaoZi baozi;
	
	public ChiHuo(BaoZi baozi){
		this.baozi=baozi;
	}
	@Override
	public void run() {
		while(true){//吃货无限的吃包子
			synchronized (baozi) {
				//1. 进行包子资源的判断
				if(baozi.isFlag()){
					//存在包子，开始吃就可以了
					System.out.println("吃货在吃"+baozi.getPer()+baozi.getXian()+"的包子");
					//吃货吃完了
					for (int i = 1; i <= 3; i++) {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("吃第"+i+"口");
					}
					baozi.setFlag(false);
					//还有一件事情需要做？？？？唤醒包子铺进行包子制作
					baozi.notify();
				}else{
					//说明没有包子
					//应该进行等待
					try {
						baozi.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
}
class BaoZiPu extends Thread{
	private BaoZi baozi;
	
	public BaoZiPu(BaoZi baozi) {
		this.baozi = baozi;
	}
	@Override
	public void run() {
		while(true){
			synchronized (baozi) {
				//判断包子资源是否存在
				if(baozi.isFlag()){
					//包子资源时存在的,包子铺进行等待操作
					try {
						baozi.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					//包子不存在，包子铺开始制作包子
					System.out.println("包子铺正在制作包子。。。。");
					baozi.setPer("白面");
					baozi.setXian("猪肉大葱");
					
					baozi.setFlag(true);//包子制作成功
					for (int i = 3; i >0; i--) {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("倒计时："+i);
					}
					//此处还要做一个事情？？？？？
					System.out.println("包子已完成！吃货快来吃！！！");
					//唤醒吃货
					baozi.notify();
				}
			}
		}
	}
	
}
class BaoZi{
	private String per;
	private String xian;
	
	private boolean flag;//包子资源是否存在   true存在  false不存在

	public BaoZi(String per, String xian) {
		super();
		this.per = per;
		this.xian = xian;
	}

	public BaoZi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "BaoZi [per=" + per + ", xian=" + xian + "]";
	}
	
	
}
