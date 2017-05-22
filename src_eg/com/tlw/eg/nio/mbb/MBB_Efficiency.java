package com.tlw.eg.nio.mbb;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

public class MBB_Efficiency {
	public static void main(String[] args) throws IOException {
		long start=System.currentTimeMillis();
		int fileSize=100*1024*1024;
		int positionHead=1024;
		int positionTail=fileSize-1024;
		int positionMiddle=fileSize/2;
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
		
		//分别随机访问该内存映射文件的头，中，尾耗时
		start=System.currentTimeMillis();
		raf=new RandomAccessFile(file,"rw");
		fc=raf.getChannel();
		fl=fc.lock();
		MappedByteBuffer mbb=fc.map(MapMode.READ_WRITE, 0, fileSize);
		mbb.position(positionTail);
		mbb.putChar('T');
		mbb.position(positionHead);
		mbb.putChar('H');
		mbb.position(positionMiddle);
		mbb.putChar('M');
		mbb.position(positionTail);
		char chTail=mbb.getChar();
		mbb.position(positionMiddle);
		char chMiddle=mbb.getChar();
		mbb.position(positionHead);
		char chHead=mbb.getChar();
		assert chTail=='T';
		assert chMiddle=='M';
		assert chHead=='H';
		fl.release();
		fc.close();
		raf.close();
		System.out.println("access spend:"+(System.currentTimeMillis()-start));
		System.out.println("-------------------------");
		
		//遍历写入耗时
		start=System.currentTimeMillis();
		raf=new RandomAccessFile(file,"rw");
		fc=raf.getChannel();
		fl=fc.lock();
		mbb=fc.map(MapMode.READ_WRITE, 0, fileSize);
		for(int i=0;i<fileSize/2;i++){
			mbb.putChar('X');
		}
		mbb.position(positionHead);
		chHead=mbb.getChar();
		mbb.position(positionMiddle);
		chMiddle=mbb.getChar();
		mbb.position(positionTail);
		chTail=mbb.getChar();
		assert chHead=='X';
		assert chMiddle=='X';
		assert chTail=='X';
		mbb.position(positionHead+8);
		chHead=mbb.getChar();
		mbb.position(positionMiddle+8);
		chMiddle=mbb.getChar();
		mbb.position(positionTail+8);
		chTail=mbb.getChar();
		assert chHead=='X';
		assert chMiddle=='X';
		assert chTail=='X';
		fl.release();
		fc.close();
		raf.close();
		System.out.println("write all spend:"+(System.currentTimeMillis()-start));
		System.out.println("-------------------------");
		
		//遍历读取耗时
		start=System.currentTimeMillis();
		raf=new RandomAccessFile(file,"rw");
		fc=raf.getChannel();
		fl=fc.lock();
		mbb=fc.map(MapMode.READ_WRITE, 0, fileSize);
		for(int i=0;i<fileSize/8;i++){
			char ch=mbb.getChar();
			assert ch=='X';
		}
		fl.release();
		fc.close();
		raf.close();
		System.out.println("read all spend:"+(System.currentTimeMillis()-start));
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
