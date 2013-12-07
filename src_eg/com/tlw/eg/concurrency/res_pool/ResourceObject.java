package com.tlw.eg.concurrency.res_pool;
/**
 * 
 * @author liwei.tang@magustek.com
 * @since 2011-12-6
 * 资源对象,这个对象有多个耗时的并且同步的方法供外界调用。
 */
public class ResourceObject {
	public String name;
	public synchronized void doSomething1(String threadName){
		int millis=(int)(Math.random()*1000D);
		try {
			System.out.println(threadName+" call("+name+").doSomething() spend "+millis+" ms.");
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void doSomething2(){
		int millis=(int)(Math.random()*1000D);
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void doSomething3(){
		int millis=(int)(Math.random()*1000D);
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
