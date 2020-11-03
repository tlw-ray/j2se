package com.tlw.eg.thread.sevenseven;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月18日
 */
public class A02Counting {
	public static void main(String[] args) throws InterruptedException{
		class Counter{
			private int count=0;
			//这里如果去掉synchronized关键字就会出现结果不是20000的情况；
			//由于在做++count时发生了并发导致最终数量总是小俞20000
			public synchronized void increment(){++count;}
			public int getCount(){return count;}
		}
		
		final Counter counter=new Counter();
		class CountingThread extends Thread{
			public void run(){
				for(int i=0;i<10000;++i){
					counter.increment();
				}
			}
		}
		
		CountingThread t1=new CountingThread();
		CountingThread t2=new CountingThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(counter.getCount());
	}
}
