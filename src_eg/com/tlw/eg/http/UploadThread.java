package com.tlw.eg.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadThread {
	
	public static void main(String[] args) throws Exception{
		String urlStr = "http://127.0.0.1:8080";
		URL url = new URL(urlStr);
		final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("content-type", "multipart/form-data");
		conn.setDoOutput(true);
		conn.connect();
		
		new Thread(){
			public void run(){
				System.out.println("Thread upload start...");
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try{
				FileInputStream is = new FileInputStream("lib/derby.jar");
				bis = new BufferedInputStream(is);
				OutputStream os = conn.getOutputStream();
				bos = new BufferedOutputStream(os);
				
				byte[] buffer = new byte[1024];
				int length;
				while((length = bis.read(buffer)) > 0){
					bos.write(buffer, 0, length);
				}
				
				bos.flush();
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Thread upload end...");
			}
		}.start();
		
		new Thread(){
			public void run(){
				System.out.println("Thread progress start...");
				InputStream urlIn = null;
				try{
					urlIn =  conn.getInputStream();
				Reader reader = new InputStreamReader(urlIn);
				BufferedReader br = new BufferedReader(reader);
				String line = null;
				while( (line = br.readLine()) != null ){
					System.out.println(line);
				}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					try {
						urlIn.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Thread progress end...");
			}
		}.start();

	}

}
