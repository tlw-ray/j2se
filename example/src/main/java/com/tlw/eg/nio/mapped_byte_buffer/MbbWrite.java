package com.tlw.eg.nio.mapped_byte_buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author liwei.tang@magustek.com
 * @since 2012-3-20
 * 为映射的文件部分写入数据
 */
public class MbbWrite {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int from=1024*1024*1023;
		RandomAccessFile raf=new RandomAccessFile("aaa.rfa","rw");
		MappedByteBuffer mbb=raf.getChannel().map(MapMode.READ_WRITE, from, 1024);
		for(int i=0;i<1024;i++){
			mbb.put((byte)i);
		}
		raf.close();
	}

}
