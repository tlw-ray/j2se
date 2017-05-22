package com.tlw.eg.nio.mapped_byte_buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author liwei.tang@magustek.com
 * @since 2012-3-20
 * 创建一个大的内存映射文件，并写入数据。（很快）
 * 但是如果遍历这些数据，可能并不快。
 */
public class MbbCreate {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int fileSize=1024*1024*1024;
		RandomAccessFile raf=new RandomAccessFile("aaa.rfa","rw");
		MappedByteBuffer mbb=raf.getChannel().map(MapMode.READ_WRITE, 0, fileSize);
		for(int i=0;i<fileSize;i++){
			byte v=(byte)(i%Byte.MAX_VALUE);
			mbb.put(v);
		}
		raf.close();
	}

}
