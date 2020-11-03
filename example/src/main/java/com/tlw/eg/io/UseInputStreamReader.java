package com.tlw.eg.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

/*******************************
Author:鍞愬姏浼�
E-Mail:tlw_ray@163.com
Date:2008-10-12
Description:
 ********************************/
public class UseInputStreamReader {
	public static void main(String[] args) throws Exception {
		URL u=ClassLoader.getSystemClassLoader().getResource("tlw/example/io/DataFile.txt");
		System.out.println(u);
		File file=new File(u.getPath());
		FileInputStream fis=new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis);
		System.out.println(isr.getEncoding());
		int temp;
		while((temp=isr.read())!=-1)System.out.print(Integer.toHexString(temp)+" ");
		System.out.println();
		while((temp=isr.read())!=-1)System.out.print(Integer.toHexString(temp)+" ");
		isr.close();
		fis.close();
	}

}
