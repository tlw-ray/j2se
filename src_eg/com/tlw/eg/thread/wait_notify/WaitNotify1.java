package com.tlw.eg.thread.wait_notify;
/**
@author liwei.tang@magustek.com
@since 2014年2月5日 上午10:46:09
wait() , notify()要用在synchronized块中，否则会抛出异常。
Exception in thread "main" java.lang.IllegalMonitorStateException
synchronized可解释为监视
 */
public class WaitNotify1 {

	public static void main(String[] args) throws InterruptedException {
		String str="aa";
		
		//直接使用会抛出异常
//		str.notify();
//		str.wait();
		
		synchronized(str){
			System.out.println("step1");
			str.wait();
			System.out.println("step2");	//当前线程已被阻塞所以无法执行到这里
			str.notify();
			System.out.println("step3");
		}
	}

}
