package com.tlw.eg.thread;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-11-29
@version:2009-11-29
Description:这个例子需要查看eclipse 的 debug画面中线程的数量来了解线程中开辟线程的样式.
验证了线程中开辟的子线程和其他线程也是平级的。
 */
public class ThreadInThread {
	public static void main(String[] args) {
		Thread th=new Thread(){
			public void run(){
				while(true){
					try {
						Thread th1=new Thread(){
							public void run(){
								try {
									System.out.println("new Thread!"+Thread.currentThread().getName()+" ID:"+Thread.currentThread().getId());
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						};
						th1.start();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
	}
}
