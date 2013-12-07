package com.tlw.eg.nio.random_access_file;

import java.io.IOException;
import java.io.RandomAccessFile;


public class RandomAccessFileStudy {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile(Constants.FILE_RAF,"rw");
		int fileSize=16;
		
		//RandomAccessFile can change it size by setLength() function.
		raf.setLength(fileSize*2);
		System.out.println(Constants.FILE_RAF.length());
		showStatus(raf);
		raf.setLength(fileSize);
		System.out.println(Constants.FILE_RAF.length());
		showStatus(raf);
		
		long start=System.currentTimeMillis();
		
		//write data to RandomAccessFile
		for(int i=0;i<raf.length()/2;i++){
			raf.writeChar('X');
		}
		raf.setLength(fileSize*2);
		for(int i=fileSize;i<raf.length()/2;i++){
			raf.writeChar('X');
		}
		System.out.println("write all data spend:"+(System.currentTimeMillis()-start));
		
		//read data by position
		raf.seek(0);
		char headCh=raf.readChar();
		assert headCh=='X';
		raf.seek(fileSize/2);
		char middleCh=raf.readChar();
		assert middleCh=='X';
		raf.seek(fileSize-2);
		char tailCh=raf.readChar();
		assert tailCh=='X';
		
		//write data by position;
		raf.seek(0);
		raf.writeChar('H');
		raf.seek(fileSize/2);
		raf.writeChar('M');
		raf.seek(fileSize-2);
		raf.writeChar('T');
		
		//validate the write chars
		raf.seek(0);
		for(int i=0;i<fileSize/2;i++){
			System.out.println(raf.getFilePointer()+"\t"+raf.readChar());
		}
		
		raf.seek(0);
		headCh=raf.readChar();
		assert headCh=='H';
		raf.seek(fileSize/2);
		middleCh=raf.readChar();
		assert middleCh=='M';
		raf.seek(fileSize-2);
		tailCh=raf.readChar();
		assert tailCh=='T';
		
		raf.close();
	}
	public static void showStatus(RandomAccessFile raf) throws IOException{
		System.out.println("RandomAccessFile.getFD()\t\t"+raf.getFD());
		System.out.println("RandomAccessFile.getFilePointer()\t"+raf.getFilePointer());
		System.out.println("RandomAccessFile.length()\t\t"+raf.length());
	}
}
