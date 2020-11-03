package com.tlw.tool.port;

import java.io.IOException;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;

/**
@since 2011-9-27
@author 唐力伟 (liwei.tang@magustek.com)
 */
public class ScanPort {
	static int threadCount=0;
	static Set<String> result=new TreeSet<String>();
	public static void main(String[] args){
		final int port=80;
		final String prefix="10.78.3";
		for(int i=0;i<256;i++){
			final int tail=i;
			new Thread(){
				public synchronized void run(){
					threadCount++;
					String ip=getIP(prefix,tail);
					Socket socket=null;
					String info="";
					try{
						socket=new Socket(ip, port);
						socket.setSoTimeout(1000);
						info=ip+"\t:"+port+"\t"+"open";
					}catch(Exception ex){
//						info=ip+"\t:"+port+"\t"+"closed";
					}finally{
						try {
							if(socket!=null){
								socket.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
//					System.out.println("checked:"+ip);
					result.add(info);
					threadCount--;
				}
			}.start();
		}
		
		new Thread(){
			public void run(){
				while(true){
					System.out.println(threadCount);
					if(threadCount==0){
						for(String str:result){
							System.out.println(str);
						}
						break;
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	public static String getIP(String prefix,int lastInt){
		return prefix+"."+lastInt;
	}
}
