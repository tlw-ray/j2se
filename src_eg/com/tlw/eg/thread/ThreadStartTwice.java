package com.tlw.eg.thread;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月26日
 */
public class ThreadStartTwice extends Thread {

	public static void main(String[] args) {
		ThreadStartTwice thread=new ThreadStartTwice();
		System.out.println(thread.isInterrupted());
		thread.start();
		System.out.println(thread.isInterrupted());
		thread.start();
	}
	
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Run...");
	}

}
