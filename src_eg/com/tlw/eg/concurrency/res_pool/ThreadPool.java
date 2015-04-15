package com.tlw.eg.concurrency.res_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年3月18日
 * 线程池一次运行10个线程，总共100个线程
 */
public class ThreadPool {

	public static void main(String[] args) {
		ExecutorService es=Executors.newFixedThreadPool(10);
		for(int i=0;i<100;i++){
			es.execute(new Runnable(){
				public void run(){
					int sleep=(int)(1000*Math.random());
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread()+"\tsleep\t"+sleep);
				}
			});
		}
		es.shutdown();
	}

}
