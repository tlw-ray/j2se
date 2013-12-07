package com.tlw.tool.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
@since 2011-11-21
@author 唐力伟 (liwei.tang@magustek.com)
将文件的指定位置和长度的数据写入到另一文件的指定位置。
有机会做GUI

oracle virtual box 在 *.vdi 文件内嵌入了GUID，导致无法拷贝使用。
该程序可以为将文件的头部同步到另一个文件，使得*.vid文件能够拷贝使用。
用例:
	制作了一个新的*.vdi文件，仅安装了操作系统。通过复制粘贴该文件产生更多空的系统盘；以便于安装软件。
 */
public class CopyFileBlock {
	public static void main(String[] args) throws IOException {
		String path="G:\\virtualBox";
		String fromFile=path+"\\windowsCQCY\\windowsCQCQ.vdi";
		String toFile=path+"\\windowsCQCY\\windowsCQCY.vdi";
		int position=0;
		int dataLength=0xFF0;
		RandomAccessFile rafFrom=new RandomAccessFile(fromFile,"r");
		RandomAccessFile rafTo=new RandomAccessFile(toFile,"rw");
		byte[] bytes=new byte[dataLength];
		FileChannel fcFrom=rafFrom.getChannel();
		rafFrom.read(bytes);
		fcFrom.close();
		rafFrom.close();
		
		FileChannel fcTo=rafTo.getChannel();
		fcTo.position(position);
		ByteBuffer bb=ByteBuffer.wrap(bytes);
		fcTo.write(bb);
		fcTo.close();
		rafTo.close();
		
		System.out.println("finish...");
	}

}
