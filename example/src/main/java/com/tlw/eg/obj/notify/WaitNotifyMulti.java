package com.tlw.eg.obj.notify;

import java.util.List;
import java.util.Vector;
/**
 * @author liwei.tang@magustek.com
 * @since 2011-12-6
 * 用于实现连接池
 * 1.连接有等待和空闲两种状态
 * 2.池内有一定数量的连接
 * 3.连接有等待和空闲两种状态
 * 4.池内至少一个连接处于空闲则池空闲，反之池忙。
 * 5.不忙的池获可以获得其中不忙的连接，忙的池在获取连接时阻塞直至有空闲的连接提供。
 * 第一阶段:unlimit pool		最简单的池，需要即提供连接。能够感知已有连接是否繁忙，不忙则提供。
 * 第二阶段:maxSized pool
 */
public class WaitNotifyMulti {
	public static void main(String[] args) {
		final List<Integer> items=new Vector<Integer>();
		items.add(1);
		items.add(2);
		items.add(3);
		//x个物件
		//
		
		//pool (wait,notify)
		//	res*x(wait,notify)
		//		
	}
	public class ConnectionPool{
		final int maxPoolSize=3;
		List<Object> connections=new Vector<Object>(maxPoolSize);
		public Object getConnection(){
			
			return null;
		}
	}
}