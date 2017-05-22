package com.tlw.eg.socket.realtime.syn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
@author liwei.tang@magustek.com
@since 2014年2月3日 下午4:56:32
传统的阻塞式SocketServer与线程绑定
 */
public class SoServerSync {

	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket server=new ServerSocket(5678);
		Socket client=null;
		try{
			System.out.println("Waite client connect...");
			client=server.accept();
			while(true){
				System.out.println("loop...");
				DataInputStream in=new DataInputStream(client.getInputStream());
				DataOutputStream out=new DataOutputStream(client.getOutputStream());
				
				int value=in.readInt();
				in.read(new byte[1024]);
				System.out.println(value);
				
				out.writeInt(value);
				out.flush();
				Thread.sleep(100);
			}
		}finally{
			server.close();
			if(client!=null){
				client.close();
			}
		}
	}

}
