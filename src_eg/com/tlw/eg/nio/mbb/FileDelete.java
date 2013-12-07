package com.tlw.eg.nio.mbb;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
/**
 * @author liwei.tang@magustek.com
 * @since 2011-12-2
 * 问题：无法在程序中删除进行过映射的文件，即便所有的引用已关闭。
 */
public class FileDelete {
	public static void main(String[] args) throws IOException {
		long start=System.currentTimeMillis();
		int fileSize=100*1024*1024;
		//创建一个2G的内存映射文件耗时10毫秒
		File file=Constants.MBB_FILE;
		RandomAccessFile raf=new RandomAccessFile(file,"rw");
		FileChannel fc=raf.getChannel();
		FileLock fl=fc.lock();
		fc.map(MapMode.READ_WRITE, 0, fileSize);
		fl.release();
		fc.close();
		raf.close();
		System.out.println("create spend:"+(System.currentTimeMillis()-start));
		System.out.println("-------------------------");
		
		//删除该文件耗时
		start=System.currentTimeMillis();
		assert file.exists();
		boolean deleted=file.delete();
		assert deleted;
		System.out.println("delete spend:"+(System.currentTimeMillis()-start));
		System.out.println("-------------------------");
	}

}
