package com.tlw.eg.thread.wait_notify;

/**
 * @author liwei.tang@magustek.com
 * @since 2014年2月4日 下午10:45:06
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 * 这个问题用Object的wait()，notify()就可以很方便的解决。
 * 通过这种方式能够良好的解决多线程之间执行的顺序，例如用在Socket处理有的可并发有的不可。
 * 游戏手柄的解决可用到该方案
 */
public class ThreadPrinter implements Runnable {

	private String name;
	private Object prev;
	private Object self;

	private ThreadPrinter(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;

					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		ThreadPrinter pa = new ThreadPrinter("A", c, a);
		ThreadPrinter pb = new ThreadPrinter("B", a, b);
		ThreadPrinter pc = new ThreadPrinter("C", b, c);

		new Thread(pa).start();
		Thread.sleep(1);//这句可以保证A先执行
		new Thread(pb).start();
//		Thread.sleep(1);
		new Thread(pc).start();
	}
}
