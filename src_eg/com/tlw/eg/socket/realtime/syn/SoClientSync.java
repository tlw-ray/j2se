package com.tlw.eg.socket.realtime.syn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
@author liwei.tang@magustek.com
@since 2014年2月3日 下午4:56:51
 */
public class SoClientSync {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("127.0.0.1",5678);

		//由于是即时通讯因而不使用Buffer
		DataInputStream in=new DataInputStream(socket.getInputStream());
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		
		long start = System.currentTimeMillis();
		for(int i=0;i<10;i++){
			//write an integer
			out.writeInt(i);
			out.write(new byte[1024]);
			out.flush();
			
			//read
			in.readInt();
			System.out.println(i);
		}
		System.out.println(System.currentTimeMillis()-start);
		

		socket.close();
	}
}
