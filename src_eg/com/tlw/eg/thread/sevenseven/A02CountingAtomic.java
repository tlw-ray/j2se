package com.tlw.eg.thread.sevenseven;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月18日
 */
public class A02CountingAtomic {

	public static void main(String[] args) throws InterruptedException {
		final AtomicInteger counter = new AtomicInteger();
		
		class CountingThread extends Thread {
			public void run() {
				for(int x = 0; x < 10000; ++x){
					counter.incrementAndGet();
				}
			}
		}
		
		CountingThread t1=new CountingThread();
		CountingThread t2=new CountingThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(counter.get());
	}

}
