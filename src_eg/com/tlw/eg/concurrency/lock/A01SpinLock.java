package com.tlw.eg.concurrency.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月25日
 */
public class A01SpinLock {

	private AtomicReference<Thread> sign = new AtomicReference<Thread>();

	public void lock() {
		Thread current = Thread.currentThread();
		while (!sign.compareAndSet(null, current)) {
		}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		sign.compareAndSet(current, null);
	}

}
