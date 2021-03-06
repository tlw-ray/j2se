package com.tlw.eg.nio.data_alernation;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import com.tlw.eg.nio.mbb.Constants;



public class MBBProcessWriter {
	public static void main(String[] args) throws IOException, InterruptedException {
		RandomAccessFile raf=new RandomAccessFile(Constants.MBB_FILE,"rw");
		FileChannel fc=raf.getChannel();
		MappedByteBuffer mbb=fc.map(MapMode.READ_WRITE, 0, Constants.MBB_FILE_SIZE);
		while(true){
			double value=Math.random();
			mbb.position(0);
			mbb.putDouble(value);
			System.out.println("write:"+value);
			Thread.sleep(1000);
		}
	}

}
