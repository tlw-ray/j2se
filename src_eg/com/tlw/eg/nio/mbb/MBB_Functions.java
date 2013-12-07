package com.tlw.eg.nio.mbb;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

public class MBB_Functions {
	public static void main(String[] args) throws IOException {
		File file=Constants.MBB_FILE;
		int fileSize=10;
		RandomAccessFile raf=new RandomAccessFile(file,"rw");
		FileChannel fc=raf.getChannel();
		FileLock fl=fc.lock();
		MappedByteBuffer mbb=fc.map(MapMode.READ_WRITE, 0, fileSize);
		
		//write 0-9
		for(int i=0;i<fileSize;i++){
			mbb.put((byte)i);
		}
		mbb.limit(fileSize);
		
		//read 0-9
		System.out.println("-----------read 0-9----------");
		mbb.clear();
		showBytes(mbb);
		
		//use array function, normal MappedByteBuffer seem's not support 
		System.out.println("----------MappedByteBuffer.array()-----------");
		mbb.clear();
		System.out.println("hasArray:"+mbb.hasArray());
		if(mbb.hasArray()){
			byte[] bytes=mbb.array();
			for(byte b:bytes){
				System.out.println(b);
			}
		}
		
		System.out.println("----------MappedByteBuffer.reset()-----------");
		mbb.position(0);
		mbb.mark();			//mark a position;
		mbb.position(10);
		mbb.reset();		//reset position to mark place.
		System.out.println("reset the position to marked place:"+mbb.position());

		System.out.println("----------MappedByteBuffer.flip()-----------");
		mbb.position(5);
		showStatus(mbb,"before flip ");
		mbb.flip();		//limit to position, position to zero, capacity no change.
		System.out.println("flip();");
		showStatus(mbb,"after flip ");
		showBytes(mbb);
		System.out.println("----------MappedByteBuffer.compact()-----------");
		//TODO 不明白这个方法以及他的示例
//		mbb.position(2);
		showStatus(mbb,"before compact ");
		mbb.compact();
		System.out.println("compact();");
		showStatus(mbb,"after compact ");
		showBytes(mbb);
		
		System.out.println("----------MappedByteBuffer.duplicate()-----------");
		mbb.position(5);
		showStatus(mbb,"before duplicate ");
		ByteBuffer bb=mbb.duplicate();//duplicate create a new reference, like clone;
		
		System.out.println("the mbb put a byte 100 at 5 position");
		mbb.put((byte) 100);
		bb.position(8);
		bb.put((byte)101);
		bb.clear();
		mbb.clear();
		
		showStatus(mbb,"the duplicated ");
		showBytes(bb);
		showStatus(mbb,"after duplicate ");
		showBytes(mbb);
		
		System.out.println("----------MappedByteBuffer.slice()-----------");
		mbb.position(3);
		mbb.limit(8);
		bb=mbb.slice();
		showStatus(bb,"slice a sub buffer ");
		showBytes(bb);
		
		fl.release();
		fc.close();
		raf.close();
	}
	private static void showBytes(ByteBuffer mbb){
		for(int i=mbb.position();i<mbb.limit();i++){
			byte b=mbb.get();
			System.out.println(b);
		}
	}
	public static void showStatus(ByteBuffer mbb,String msg){
		System.out.println(msg+"position="+mbb.position());	//位置
		System.out.println(msg+"limit="+mbb.limit());		//容量内的有效内容
		System.out.println(msg+"capacity="+mbb.capacity());	//容量
	}
}
