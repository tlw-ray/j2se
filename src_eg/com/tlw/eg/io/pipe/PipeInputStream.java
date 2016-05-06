package com.tlw.eg.io.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年4月26日
 */
public class PipeInputStream {

	public static void main(String[] args) throws IOException {

		final PipedOutputStream output = new PipedOutputStream();
		final PipedInputStream input = new PipedInputStream(output);

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					output.write("Hello world, pipe!".getBytes());
				} catch (IOException e) {
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int data = input.read();
					while (data != -1) {
						System.out.print((char) data);
						data = input.read();
					}
				} catch (IOException e) {
					//注意:这里会发生异常
//					e.printStackTrace();
				}
				
				
//				try {
//					int data = -1;
//					while((data = input.read()) != -1){
//						System.out.print((char)data);
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}finally{
//					try {
//						input.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
			}
		});

		thread1.start();
		thread2.start();

	}
}
