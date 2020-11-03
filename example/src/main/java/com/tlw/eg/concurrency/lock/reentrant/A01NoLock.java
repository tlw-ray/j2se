package com.tlw.eg.concurrency.lock.reentrant;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月26日
 */
public class A01NoLock implements Runnable{
	
	public static void main(String[] args) {
		A01NoLock ss = new A01NoLock();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}

	public synchronized void get() {
		System.out.println(Thread.currentThread().getId());
		set();
	}

	public synchronized void set() {
		System.out.println(Thread.currentThread().getId());
	}

	@Override
	public void run() {
		get();
	}

}
