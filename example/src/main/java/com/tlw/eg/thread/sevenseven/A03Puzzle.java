package com.tlw.eg.thread.sevenseven;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月18日
 */
public class A03Puzzle {

	static boolean answerReady = false;
	static int answer = 0;
	static Thread t1 = new Thread() {
		public void run() {
			answer = 42;
			answerReady = true;
		}
	};
	
	static Thread t2 = new Thread() {
		public void run() {
			//如果这样写有可能发生死锁，但实际情况是有可能什么也没有输出程序就退出了。
			while (! answerReady){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The mean of life is: " + answer);
			}
			
			//如果这样写，两种答案都可能被输出；
//			if (answerReady) {
//				System.out.println("The meaning of life is: " + answer);
//			} else {
//				System.out.println("I don't know the answer");
//			}
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
