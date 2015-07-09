package com.tlw.eg.nio.rw;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月8日
 */
public class FileReadWrite {

	public static void main(String[] args) throws IOException {
		File file=new File("temp.dat");
		RandomAccessFile raf=new RandomAccessFile(file, "rwd");
		FileChannel readChannel1=raf.getChannel();
		FileChannel readChannel2=raf.getChannel();
		FileChannel writeChannel=raf.getChannel();
		
//		//使用流写入再通过Channel读出
//		FileOutputStream fos=new FileOutputStream(file);
//		DataOutputStream dos=new DataOutputStream(fos);
//		dos.writeInt('a');
//		dos.flush();
//		dos.close();
		
		//使用Channel写入再通过其他的Channel读出
		ByteBuffer writeBuffer=ByteBuffer.allocate(4);
		writeBuffer.putInt('b');
		writeBuffer.flip();						//反转buffer准备用于写入
		while(writeBuffer.hasRemaining()){		//写入直到所有buffer被写完
			writeChannel.write(writeBuffer, 0);
		}
		
		ByteBuffer readBuffer1=ByteBuffer.allocate(4);
		readChannel1.position(0);
		readChannel1.read(readBuffer1);
		readBuffer1.flip();
		System.out.println(readBuffer1.getInt());
		
		ByteBuffer readBuffer2=ByteBuffer.allocate(4);
		readChannel2.position(0);
		readChannel2.read(readBuffer2);
		readBuffer2.flip();
		System.out.println(readBuffer2.getInt());
		
		raf.close();
		
	}

}
