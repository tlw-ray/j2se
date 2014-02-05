package com.tlw.eg.thread.wait_notify;
/**
@author liwei.tang@magustek.com
@since 2014年2月5日 上午11:21:25
两个线程交替执行,打印A,B
第一次可能先打印A也可能先打印B
问题：该程序无法退出
 */
public class ThreadPrinter1A extends Thread {
	
	public static int MAX_COUNT=3;

	public static void main(String[] args) {
		Thread1 thread1=new Thread1();
		Thread2 thread2=new Thread2();
		
		thread1.start();
		thread2.start();
	}
	
	static Object LOCK="信号量";
	
	static class Thread1 extends Thread{
		public void run(){
			int count=MAX_COUNT;
			while(count-->0){
				synchronized(LOCK){
					System.out.println("A");
					LOCK.notify();
					try {
						LOCK.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	static class Thread2 extends Thread{
		public void run(){
			int count=MAX_COUNT;
			while(count-->0){
				synchronized(LOCK){
					System.out.println("B");
					LOCK.notify();
					try {
						LOCK.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
