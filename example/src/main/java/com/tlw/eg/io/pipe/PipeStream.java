package com.tlw.eg.io.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年5月18日
 */
public class PipeStream {

	public static void main(String[] args) throws IOException {
		byte[] bytes = new byte[]{(byte)0, (byte)1, (byte)2, (byte)3};
		PipedInputStream is = new PipedInputStream();
		PipedOutputStream io = new PipedOutputStream(is);
		io.write(bytes);
		io.flush();
		io.close();
		
		byte[] bytes1 = new byte[4];
		is.read(bytes1);
		is.close();
		
		for(byte byt:bytes1){
			System.out.println(byt);
		}
	}

}
