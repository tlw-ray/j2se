package com.tlw.eg.thread.wait_notify;


/**
@author liwei.tang@magustek.com
@since 2014年2月5日 上午11:35:37
两个线程交替执行,打印A,B
保证第一次先打印A
 */
public class ThreadPrinter1B {
	
	public static int MAX_COUNT=2;

	public static void main(String[] args) throws InterruptedException {
		Thread1 thread1=new Thread1();
		Thread2 thread2=new Thread2();
		
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
	}
	
	static Object LOCK="锁";
	
	//通过条件判断第一次打印
	static boolean flag=true;
	
	static class Thread1 extends Thread{
		public void run(){
			int count=MAX_COUNT;
			while(count-->0){
				synchronized(LOCK){
					if(flag){
						System.out.println("A");
						flag=!flag;
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
	
	static class Thread2 extends Thread{
		public void run(){
			int count=MAX_COUNT;
			while(count-->0){
				synchronized(LOCK){
					if(!flag){
						System.out.println("B");
						flag=!flag;
						
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

}
