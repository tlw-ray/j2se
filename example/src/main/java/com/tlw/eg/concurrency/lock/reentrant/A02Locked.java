package com.tlw.eg.concurrency.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月26日
 */
public class A02Locked implements Runnable {
	
	public static void main(String[] args) {
		A02Locked ss = new A02Locked();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}

	ReentrantLock lock = new ReentrantLock();

	public void get() {
		lock.lock();
		System.out.println(Thread.currentThread().getId());
		set();
		lock.unlock();
	}

	public void set() {
		lock.lock();
		System.out.println(Thread.currentThread().getId());
		lock.unlock();
	}

	@Override
	public void run() {
		get();
	}

}
