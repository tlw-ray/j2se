package com.tlw.eg.thread;
/**
@author liwei.tang@magustek.com
@since 2014年2月5日 下午12:19:42
JOIN等待子线程结束
 */
public class ThreadJoin {

	public static void main(String[] args) {
		ThreadParent threadParent=new ThreadParent();
		threadParent.start();
	}
	
	static class ThreadParent extends Thread{
		public void run(){
			try {
				System.out.println("parent start...");
				Thread.sleep(500);
				ThreadChild threadChild=new ThreadChild();
				threadChild.start();
				threadChild.join();
				System.out.println("parent finish...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class ThreadChild extends Thread{
		public void run(){
			try {
				System.out.println("child start...");
				Thread.sleep(1000);
				System.out.println("child finish...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
