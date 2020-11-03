package com.tlw.eg.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket server=new ServerSocket(5678);
		while(true){
			System.out.println("Listen...");
			Socket client=server.accept();
			BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out=new PrintWriter(client.getOutputStream());
			String str=in.readLine();
			Thread.sleep(500);
			if(str!=null){
				System.out.println("recive:"+str);
				out.println(str);
				out.flush();
			}
			client.close();
		}
	}
}
