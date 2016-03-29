package com.tlw.eg.nio.n1;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class A01 {

	public static void main(String[] args) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("lib/derby.jar","r");
		FileChannel inChannel = aFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		
		int bytesRead = inChannel.read(buf);
		while(bytesRead != -1){
			System.out.println("Read " + bytesRead);
			buf.flip();
			
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
			
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		
		aFile.close();
	}

}
