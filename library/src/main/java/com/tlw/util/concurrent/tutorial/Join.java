package com.tlw.util.concurrent.tutorial;
/**
@Author liwei.tang@magustek.com
@Since 2013-7-2
 */
public class Join {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JoinThread th1=new JoinThread();
		JoinThread th2=new JoinThread();
		JoinThread th3=new JoinThread();
		th1.start();
		th2.start();
		th3.start();
		try {
			//导致当前线程暂停，直到th2结束
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class JoinThread extends Thread{
		public void run(){
			System.out.println("JoinThread "+getName()+" started...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("JoinThread "+getName()+" finished...");
			
		}
	}
}
