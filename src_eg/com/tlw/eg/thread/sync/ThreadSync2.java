package com.tlw.eg.thread.sync;

import java.util.Random;

/**
@author liwei.tang@magustek.com
@since 2014年2月7日 上午9:09:41
synchronized可以根据类做同步
 */
public class ThreadSync2 extends Thread {

	public static void main(String[] args) {
		ThreadSync2 th1=new ThreadSync2();
		ThreadSync2 th2=new ThreadSync2();
		th1.start();
		th2.start();
	}
	public void run(){
		play(getName());
	}
	public void play(String name){
		synchronized(ThreadSync2.class){
			int count=3;
			while(count-->0){
				try {
					Random random=new Random();
					int toSleep=random.nextInt(1000);
					System.out.println(name+" will sleep: "+toSleep);
					long start=System.currentTimeMillis();
					sleep(toSleep);
					System.out.println(name+" realy sleep: "+(System.currentTimeMillis()-start));
					
					//线程交替执行
					ThreadSync2.class.notify();
					ThreadSync2.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
