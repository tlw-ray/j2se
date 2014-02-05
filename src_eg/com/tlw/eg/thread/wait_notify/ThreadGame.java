package com.tlw.eg.thread.wait_notify;

/**
 * @author liwei.tang@magustek.com
 * @since 2014年2月5日 上午10:48:50
 * 服务线程处理一次数据，客户线程读写一次数据；读写可并发不分先后；
 * 服务线程与两个客户端线程交替执行；
 * 
 * 类似于游戏，服务端用于为各个客户端沟通数据；
 * 既：将A客户端的数据发给B客户端，把B客户端的数据发给A客户端；
 * 
 * 客户端写入数据和读取数据，服务端处理，客户端再写入或读取数据；
 * 客户端的写入和读取是可并发的；
 * 服务端一次，客户端一次交替进行；
 */
public class ThreadGame {
	
	static int MAX_COUNT=1;

	public static void main(String[] args) {
		
		ThreadServer threadServer = new ThreadServer();
		ThreadClient threadClientRead = new ThreadClient();
		ThreadClient threadClientWrite = new ThreadClient();

		threadServer.threadClientRead = threadClientRead;
		threadServer.threadClientWrite = threadClientWrite;
		
		threadClientRead.threadServer = threadServer;
		threadClientRead.read=true;
		
		threadClientWrite.threadServer = threadServer;
		threadClientWrite.read=false;

		threadClientRead.start();
		threadClientWrite.start();
		threadServer.start();
	}

	static class ThreadServer extends Thread {
		
		ThreadClient threadClientRead;
		ThreadClient threadClientWrite;

		public void run() {

			int count=MAX_COUNT;
			while (count-->0) {

				synchronized (threadClientRead) {
					synchronized (threadClientWrite) {

						System.out.println("Server\t"+count);

						threadClientWrite.notify();
						threadClientRead.notify();

						try {
							threadClientRead.wait();
							threadClientWrite.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}

			}
		}
	}

	static class ThreadClient extends Thread {

		boolean read;

		ThreadServer threadServer;

		public void run() {

			int count=MAX_COUNT;
			while (count-->0) {
				
				synchronized (ThreadClient.this) {

					if(read){
						System.out.println("Read\t"+count);
					}else{
						System.out.println("Write\t"+count);
					}

					ThreadClient.this.notify();

					try {
						ThreadClient.this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

		}
	}
}
