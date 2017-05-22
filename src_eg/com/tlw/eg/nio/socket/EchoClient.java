package com.tlw.eg.nio.socket;
/**
@author liwei.tang@magustek.com
@since 2013年11月20日 下午3:53:21
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
  
public class EchoClient {  
    public static void main(String[] args) {  
        new EchoClient("localhost", 1982);  
    }  
    private ByteBuffer w_bBuf;  
    private ByteBuffer r_bBuf = ByteBuffer.allocate(1024);  
    public EchoClient(String host, int port) {  
        try {  
            InetSocketAddress remote = new InetSocketAddress(host, port);  
            SocketChannel sc = SocketChannel.open();  
            sc.connect(remote);  
            if(sc.finishConnect()) {  
                System.out.println("已经与服务器成功建立连接...");  
            }  
            while(true) {  
                if(!sc.socket().isConnected()) {  
                    System.out.println("已经与服务器失去了连接...");  
                    return ;  
                }  
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
                String str = br.readLine();  
                System.out.println("读入一行数据，开始发送...");  
                w_bBuf = ByteBuffer.wrap(str.getBytes("UTF-8"));  
                //将缓冲区中数据写入通道  
                sc.write(w_bBuf);  
                System.out.println("数据发送成功...");  
                w_bBuf.clear();  
                System.out.println("接收服务器端响应消息...");  
                try {  
                    Thread.currentThread();  
                    Thread.sleep(100);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                r_bBuf.clear();  
                //将字节序列从此通道中读入给定的缓冲区r_bBuf  
                sc.read(r_bBuf);  
                r_bBuf.flip();  
                String msg = Charset.forName("UTF-8").decode(r_bBuf).toString();  
                System.out.println(msg);                  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  
