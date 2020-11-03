package com.tlw.eg.thread.sync;

import java.util.Random;

/**
@author liwei.tang@magustek.com
@since 2014年2月7日 上午8:51:06
synchronized等于在方法内第一行
synchronized(this){
...
}
 */
public class ThreadSync extends Thread {

	public static void main(String[] args) {
		ThreadSync th1=new ThreadSync();
		ThreadSync th2=new ThreadSync();
		th1.start();
		th2.start();
	}
	
	public synchronized void run(){
		play(getName());
	}
	
	public synchronized void play(String name){
		int count=3;
		while(count-->0){
			try {
				Random random=new Random();
				int toSleep=random.nextInt(1000);
				System.out.println(name+" will sleep: "+toSleep);
				long start=System.currentTimeMillis();
				sleep(toSleep);
				System.out.println(name+" realy sleep: "+(System.currentTimeMillis()-start));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
