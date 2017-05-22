package com.tlw.eg.socket.realtime.asyn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author liwei.tang@magustek.com
 * @since 2014年2月3日 下午4:56:41
 */
public class SoServerAsync {

	public static void main(String[] args) throws IOException {
		
		// 获取一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(1234));
		
		// 获取通道管理器
		Selector selector = Selector.open();
		// 将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
		// 只有当该事件到达时，Selector.select()会返回，否则一直阻塞。？？
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		System.out.println("服务器端启动成功");

		// 使用轮询访问selector
		while (true) {
			// 当有注册的事件到达时，方法返回，否则阻塞。
			selector.select();

			// 获取selector中的迭代器，选中项为注册的事件
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

			while (ite.hasNext()) {
				SelectionKey key = ite.next();
				// 删除已选key，防止重复处理
				ite.remove();
				// 客户端请求连接事件
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					// 获得客户端连接通道
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);
					// 向客户端发消息
					channel.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
					// 在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
					channel.register(selector, SelectionKey.OP_READ);

					System.out.println("客户端请求连接事件");
				} else if (key.isReadable()) {// 有可读数据事件
					// 获取客户端传输数据可读取消息通道。
					SocketChannel channel = (SocketChannel) key.channel();
					// 创建读取数据缓冲器
					ByteBuffer buffer = ByteBuffer.allocate(10);
					try{
						int read = channel.read(buffer);
						byte[] data = buffer.array();
						String message = new String(data);
						System.out.println("receive message from client, size:" + buffer.position() + " msg: " + message);
					}catch(IOException ex){
						//java.io.IOException: 远程主机强迫关闭了一个现有的连接。
						System.out.println("channel close....");
						channel.close();
						ex.printStackTrace();
					}
				}
			}
		}
	}

}
