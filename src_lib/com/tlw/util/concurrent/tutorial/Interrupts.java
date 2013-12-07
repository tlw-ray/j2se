package com.tlw.util.concurrent.tutorial;
/**
@Author liwei.tang@magustek.com
@Since 2013-7-2
线程外部调用interrupt()方法会触发线程内部抓取的InterruptedException;
该方法起到唤醒线程的作用？不能，循环调用interrupt()仅会抛出一个异常。

 */
public class Interrupts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadInterrupts th=new ThreadInterrupts();
		th.start();
		for(int i=0;i<30;i++){
			th.interrupt();
		}
	}
	static class ThreadInterrupts extends Thread{
		public void run(){
			while(true){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread running...");
			}
		}
	}
}
