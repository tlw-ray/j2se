package com.tlw.swing.event.invoke_later_wait;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月9日
 */
public class DoHello implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Before doHello()...");
			Thread.sleep(3000);
			System.out.println("Hello World on " + Thread.currentThread());
			Thread.sleep(3000);
			System.out.println("After doHello()...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
