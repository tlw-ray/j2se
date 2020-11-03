package com.tlw.eg.nio.mapped_byte_buffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

/**
 * @author liwei.tang@magustek.com
 * @since 2012-3-21
 * 结论内存映射似乎与文件锁无关。
 * 映射过的文件，不能被流操作写入。
 */
public class MbbWriteMapped {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int from=1024*1024*1023;
		
		//第一次打开加锁并建立映射
		RandomAccessFile raf0=null;
		FileLock lock0=null;
		MappedByteBuffer mbb0=null;
		try{
			raf0=new RandomAccessFile("aaa.rfa","rw");
			FileChannel fc=raf0.getChannel();
			lock0=fc.lock();
			mbb0=fc.map(MapMode.READ_WRITE, from, 1024);
		}finally{
			if(lock0!=null){
				lock0.release();
			}
			if(raf0!=null){
				raf0.close();
			}
		}
		
		//第二次打开加锁，并建立映射，写入数据；
		RandomAccessFile raf1=null;
		FileLock lock1=null;
		MappedByteBuffer mbb1=null;
		try{
			raf1=new RandomAccessFile("aaa.rfa","rw");
			FileChannel fc=raf1.getChannel();
			lock1=fc.lock();
			mbb1=fc.map(MapMode.READ_WRITE, from, 1024);
			for(int i=0;i<1024;i++){
				mbb1.put((byte)i);
			}
		}finally{
			if(lock1!=null){
				lock1.release();
			}
			if(raf1!=null){
				raf1.close();
			}
		}

		//从第一次打开的映射获取数据，此时映射内的数据应已被改变。
		mbb0.position(0);
		for(int i=0;i<10;i++){
			System.out.println("mbb0:"+mbb0.get());
		}
		
		//用文件流写入被映射过的文件，会报异常。
		FileOutputStream fos=new FileOutputStream("aaa.rfa");
		for(int i=from;i<100;i++){
			fos.write(i);
		}
		fos.close();
		System.out.println("finished");
	}

}
