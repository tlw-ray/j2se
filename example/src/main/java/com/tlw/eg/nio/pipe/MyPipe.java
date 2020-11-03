package com.tlw.eg.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年3月29日
 */
public class MyPipe {

	public static void main(String[] args) throws IOException {
		  Pipe pipe = Pipe.open();
		  final Pipe.SinkChannel psic = pipe.sink();
		  final Pipe.SourceChannel psoc = pipe.source();
		  String hello = "Hello!";
		  ByteBuffer bb = ByteBuffer.wrap(hello.getBytes());
		  psic.write(bb);
		  ByteBuffer bbb = ByteBuffer.wrap(new byte[20]);
		  psoc.read(bbb);
		  String out = new String(bbb.array());
		  System.out.println(out);
	}

}
