package com.tlw.eg.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Upload {

	public static void main(String[] args) throws Exception {
		String urlStr = "http://127.0.0.1:8201";
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("content-type", "multipart/form-data");
		conn.setDoOutput(true);
		conn.connect();
		
//		FileInputStream is = new FileInputStream("README");
		FileInputStream is = new FileInputStream("lib/derby.jar");
		BufferedInputStream bis = new BufferedInputStream(is);
		OutputStream os = conn.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		byte[] buffer = new byte[1024];
		int length;
		while((length = bis.read(buffer)) > 0){
			bos.write(buffer, 0, length);
		}
		
		bos.flush();
		
		bis.close();
		bos.close();
		
		InputStream urlIn =  conn.getInputStream();
		Reader reader = new InputStreamReader(urlIn);
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while( (line = br.readLine()) != null ){
			System.out.println(line);
		}
		
		System.out.println(urlStr);
	}

}
