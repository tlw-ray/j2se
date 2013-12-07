package com.tlw.util.concurrent.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @since 2011-7-11
 * @author 唐力伟 (liwei.tang@magustek.com)
 */
public class ReadWriteLockLogic {
	
	public static void main(String[] args) {
	    //1 创建一个具有排程功能的线程池
	    ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

	    //2 读写锁的logic bean
	    ReadWriteLockLogic lockOperator = new ReadWriteLockLogic();

	    //3 生成一个可执行任务(该任务执行完毕可以返回结果 或者 抛出异常；而Runnable接口的run方法则不行)
	    Runnable writeTask1 = new WriteTask(lockOperator, "作者A");

	    //4 延时0秒后每2秒重复执行writeTask1;
	    service.scheduleAtFixedRate(writeTask1, 0, 60, TimeUnit.SECONDS);

	    //5 创建3个读任务
	    Runnable readTask1 = new ReadTask(lockOperator, "读者A");
	    Runnable readTask2 = new ReadTask(lockOperator, "读者B");
	    Runnable readTask3 = new ReadTask(lockOperator, "读者C");

	    //6 延时0秒后每秒执行一次task1;
	    service.scheduleAtFixedRate(readTask1, 1, 1, TimeUnit.SECONDS);
	    service.scheduleAtFixedRate(readTask2, 2, 1, TimeUnit.SECONDS);
	    service.scheduleAtFixedRate(readTask3, 3, 1, TimeUnit.SECONDS);
	}

	// 初始化一个 ReadWriteLock
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	// 共享资源
	private List<String> shareResources = new ArrayList<String>(0);

	// 读
	public String read() {

		// 得到 readLock 并锁定
		Lock readLock = lock.readLock();

		readLock.lock();

		try {
			// 读相对省时，做空循环 大约0.5second
			for (int i = 0; i < 2500000; i++) {
				System.out.print("");
			}

			// 做读的工作
			StringBuffer buffer = new StringBuffer();
			for (String shareResource : shareResources) {
				buffer.append(shareResource);
				buffer.append("\t");
			}

			return buffer.toString();

		} finally {
			readLock.unlock();// 一定要保证锁的释放
		}
	}

	// 写
	public void write(String writer, String content) {

		// 得到 writeLock 并锁定
		Lock writeLock = lock.writeLock();

		writeLock.lock();

		try {
			System.out.println(writer + " write ==="
					+ Thread.currentThread().toString());
			// 写比较费时，所以做空循环 大约13second
			for (int i = 0; i < 50000000; i++) {
				System.out.print("");
				System.out.print("");
			}

			// 做写的工作
			int count = shareResources.size();
			for (int i = count; i < count + 1; i++) {
				shareResources.add(content + "_" + i);
			}

		} finally {
			writeLock.unlock();// 一定要保证锁的释放
		}
	}
}
