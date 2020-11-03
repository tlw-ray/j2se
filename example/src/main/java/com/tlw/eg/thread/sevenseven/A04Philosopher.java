package com.tlw.eg.thread.sevenseven;


/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月18日
 * 运行一段时间发生死锁，当每个哲学家并发拿起了左边的筷子，发现它们各自右边的筷子没法拿到，于是都进入等待状态
 */
public class A04Philosopher extends Thread {

	public static void main(String[] args) throws InterruptedException {
		Chopstick chopstick1=new Chopstick();
		Chopstick chopstick2=new Chopstick();
		A04Philosopher philosopher1=new A04Philosopher(chopstick1, chopstick2);
		A04Philosopher philosopher2=new A04Philosopher(chopstick2, chopstick1);
		philosopher1.start();
		philosopher2.start();
		philosopher1.join();
		philosopher2.join();
	}
	
	static class Chopstick {
		
	}
	
	private Chopstick left, right;
	
	public A04Philosopher(Chopstick left, Chopstick right){
		this.left = left;
		this.right = right;
	}
	
	public void run(){
			while(true) {
				//思考
				System.out.println(getName()+" Thining...");
				//拿起筷子1
				synchronized(left) {
					//拿起筷子2
					synchronized(right) {
						//进餐
						System.out.println(getName()+" Eating...");
					}
				}
			}
	}
}
