package com.tlw.eg.socket.realtime.asyn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
@author liwei.tang@magustek.com
@since 2014年2月3日 下午4:57:01
 */
public class SoClientAsync {

	public static void main(String[] args) throws IOException {
		//获取socket通道
        SocketChannel channelx = SocketChannel.open();
        
        channelx.configureBlocking(false);
        //获得通道管理器
        Selector selector=Selector.open();
        
        //客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
        channelx.connect(new InetSocketAddress(1234));
        //为该通道注册SelectionKey.OP_CONNECT事件
        channelx.register(selector, SelectionKey.OP_CONNECT);
        
        System.out.println("客户端启动");
        //轮询访问selector
        while(true){
            //选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                //删除已选的key，防止重复处理
                ite.remove();
                if(key.isConnectable()){
                    SocketChannel channel=(SocketChannel)key.channel();
                    
                    //如果正在连接，则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    
                    channel.configureBlocking(false);
                    //向服务器发送消息
                    channel.write(ByteBuffer.wrap(new String("send message to server.").getBytes()));
                    
                    //连接成功后，注册接收服务器消息的事件
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                }else if(key.isReadable()){ //有可读数据事件。
                    SocketChannel channel = (SocketChannel)key.channel();
                    
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    channel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    
                    System.out.println("recevie message from server:, size:" + buffer.position() + " msg: " + message);
                }
            }
        }
	}
}
