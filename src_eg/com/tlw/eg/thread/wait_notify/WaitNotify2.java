package com.tlw.eg.thread.wait_notify;
/**
@author liwei.tang@magustek.com
@since 2014年2月5日 上午10:41:59
线程1等待对象被唤醒；
线程2等待1秒后，唤醒该对象；
线程1，2同时启动；
 */
public class WaitNotify2 {

	public static void main(String[] args) throws InterruptedException {
		final String str="aa";
		
		new Thread(){
			public void run(){
				synchronized(str){
					try {
						System.out.println(Thread.currentThread().getName()+" : start and wait...");
						str.wait();
						System.out.println(Thread.currentThread().getName()+" : notifyed...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		Thread.sleep(1000);
		
		new Thread(){
			public void run(){
				synchronized(str){
					System.out.println(Thread.currentThread().getName()+" : start notify thread1...");
					str.notify();
				}
			}
		}.start();

	}

}
