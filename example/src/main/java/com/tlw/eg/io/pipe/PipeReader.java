package com.tlw.eg.io.pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年4月26日
 */
public class PipeReader {

	public static void main(String[] args) throws IOException {
		PipedReader pr = new PipedReader();
		PipedWriter pw = new PipedWriter(pr);
		pw.append("Hello world!");
		pw.flush();
		pw.close();
		
		BufferedReader br = new BufferedReader(pr);
		String line = br.readLine();
		System.out.println(line);
	}

}
