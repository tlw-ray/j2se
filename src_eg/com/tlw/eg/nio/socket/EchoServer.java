package com.tlw.eg.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Iterator;

/**
@author liwei.tang@magustek.com
@since 2013年11月20日 下午3:52:47
 */
class EchoServer implements Runnable {  
	public static void main(String[] args) {
        new Thread(new EchoServer(1982)).start();  
	}
	
    //要监听的端口号  
    private int port;  
    //生成一个信号监视器  
    private Selector s;  
    //读缓冲区  
    private ByteBuffer r_bBuf = ByteBuffer.allocate(1024);  
    private ByteBuffer w_bBuf;  
      
    public EchoServer(int port) {  
        this.port = port;  
        try {  
            s = Selector.open();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    @Override  
    public void run() {  
        try {  
            //生成一个ServerScoket通道的实例对象，用于侦听可能发生的IO事件  
            ServerSocketChannel ssc = ServerSocketChannel.open();  
            //将该通道设置为异步方式  
            ssc.configureBlocking(false);  
            //绑定到一个指定的端口  
            ssc.socket().bind(new InetSocketAddress(port));  
            //注册特定类型的事件到信号监视器上  
            ssc.register(s, SelectionKey.OP_ACCEPT);  
            System.out.println("The server has been launched...");  
            while(true) {  
                //将会阻塞执行，直到有事件发生  
                s.select();  
                Iterator<SelectionKey> it = s.selectedKeys().iterator();  
                while(it.hasNext()) {  
                    SelectionKey key = it.next();  
                    //key定义了四种不同形式的操作  
                    switch(key.readyOps()) {  
                    case SelectionKey.OP_ACCEPT :  
                        dealwithAccept(key);  
                        break;  
                    case SelectionKey.OP_CONNECT :  
                        break;  
                    case SelectionKey.OP_READ :  
                        dealwithRead(key);  
                        break;  
                    case SelectionKey.OP_WRITE :  
                        break;  
                    }  
                    //处理结束后移除当前事件，以免重复处理  
                    it.remove();  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    //处理接收连接的事件  
    private void dealwithAccept(SelectionKey key) {  
        try {  
            System.out.println("新的客户端请求连接...");  
            ServerSocketChannel server = (ServerSocketChannel)key.channel();  
            SocketChannel sc = server.accept();  
            sc.configureBlocking(false);  
            //注册读事件  
            sc.register(s, SelectionKey.OP_READ);  
            System.out.println("客户端连接成功...");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    //处理客户端发来的消息，处理读事件  
    private void dealwithRead(SelectionKey key) {  
        try {  
            SocketChannel sc = (SocketChannel)key.channel();  
            System.out.println("读入数据");  
            r_bBuf.clear();  
            //将字节序列从此通道中读入给定的缓冲区r_bBuf  
            sc.read(r_bBuf);  
            r_bBuf.flip();  
            String msg = Charset.forName("UTF-8").decode(r_bBuf).toString();  
            if(msg.equalsIgnoreCase("time")) {  
                w_bBuf = ByteBuffer.wrap(getCurrentTime().getBytes("UTF-8"));  
                sc.write(w_bBuf);  
                w_bBuf.clear();  
            } else if(msg.equalsIgnoreCase("bye")) {  
                sc.write(ByteBuffer.wrap("已经与服务器断开连接".getBytes("UTF-8")));  
                sc.socket().close();  
            } else {  
                sc.write(ByteBuffer.wrap(msg.getBytes("UTF-8")));  
            }  
            System.out.println(msg);  
            System.out.println("处理完毕...");  
            r_bBuf.clear();  
            try {  
                Thread.currentThread();  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    private String getCurrentTime() {  
        Calendar date = Calendar.getInstance();  
        String time = "服务器当前时间：" +  
                      date.get(Calendar.YEAR) + "-" +  
                      date.get(Calendar.MONTH)+1 + "-" +  
                      date.get(Calendar.DATE) + " " +  
                      date.get(Calendar.HOUR) + ":" +  
                      date.get(Calendar.MINUTE) + ":" +  
                      date.get(Calendar.SECOND);  
        return time;  
    }  
}  