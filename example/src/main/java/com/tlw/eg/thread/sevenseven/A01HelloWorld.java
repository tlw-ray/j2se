package com.tlw.eg.thread.sevenseven;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月18日
 */
public class A01HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		Thread myThread=new Thread(){
			public void run(){
				System.out.println("Hello from new thread");
			}
		};
		myThread.start();
		Thread.yield();
//		Thread.sleep(1);
		System.out.println("Hello from main thread");
		myThread.join();
	}

}
