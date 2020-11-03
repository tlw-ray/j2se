package com.tlw.util.concurrent;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同时运行一批线程，知道这批线程最耗时的一个结束，这一批在进程中结束。
@since 2011-7-5
@author 唐力伟 (liwei.tang@magustek.com)
 */
public class ThreadGroupRunner {
	public static void main(String[] args) throws InterruptedException{
		int THREAD_COUNT=10;
		List<MyCallable<String>> callables = new Vector<MyCallable<String>>();
		for(int i=0;i<THREAD_COUNT;i++){
			MyCallable<String> th=new MyCallable<String>();
			th.idx=i;
			callables.add(th);
		}
		ExecutorService execSvc = Executors.newCachedThreadPool();
		long start=System.currentTimeMillis();
		execSvc.invokeAll(callables);
		System.out.println(System.currentTimeMillis()-start);
	}
	static class MyCallable<String> implements Callable<String>{
		public int idx;
		public String call(){
			int random=(int)(Math.random()*1000);
			Thread th=Thread.currentThread();
			String result=(String) (th.getName()+"\t\t"+idx+"\tsleeped:"+random);
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(result);
			return result;
		}
	};
}
