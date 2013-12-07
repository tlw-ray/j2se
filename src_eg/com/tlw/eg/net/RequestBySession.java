package com.tlw.eg.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
@since 2010-3-2
@version 2010-3-2
@author 唐力伟 (tlw_ray@163.com)
 */
public class RequestBySession {
	public static void main(String[] args) throws IOException {
		URL u=new URL("http://192.168.2.183:8181/system/system/doSysLeftMenu.do?method=downloadSwingTree&jobNo=1");
		URLConnection httpConn=u.openConnection();
        httpConn.setRequestProperty("Cookie", "JSESSIONID=6715FF50C154B5E7835274B1FAA64CFF");
		InputStream is=httpConn.getInputStream();
		
		//保存到文件
//		FileOutputStream fis=new FileOutputStream("D:\\aaa.txt");
//		while(true){
//			byte[] bs=new byte[1];
//			int length=is.read(bs);
//			fis.write(bs);
//			if(length<bs.length)break;
//		}
//		fis.flush();

		
		//输出到控制台
		InputStreamReader isr=new InputStreamReader(is);
		BufferedReader br=new BufferedReader(isr);
		String line=null,xml="";
		while((line=br.readLine())!=null)xml+=line;
		System.out.println(xml);
	}
}