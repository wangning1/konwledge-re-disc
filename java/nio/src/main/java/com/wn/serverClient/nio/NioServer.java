package com.wn.serverClient.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by wangning on 2017/11/8.
 */
public class NioServer {

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8088));
        //监听客户端连接请求
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            //进行阻塞
//            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

//                if (key.isValid()) {
                //处理新建立链接的请求
                if (key.isAcceptable()) {
                    System.out.println("客户端建立连接");
//                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }

                if (key.isWritable()) {
                    //发送应答消息
                    SocketChannel socketChannel = (SocketChannel) key.channel();
//                    System.out.println("服务器给客户端发送消息");
                    String response = "服务器给你的响应！！";
                    ByteBuffer byteBuffer = ByteBuffer.allocate(response.getBytes().length);
                    byteBuffer.put(response.getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);

                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);


                }

                if (key.isReadable()) {
                    System.out.println("服务器读取客户端信息~~");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer bbf = ByteBuffer.allocate(1024);
                    int num = socketChannel.read(bbf);
                    System.out.println("接收数量：" + num);
                    if (num > 0) {
                        bbf.flip();
                        System.out.println("remaining数量：" + bbf.remaining());
                        byte[] bytes = new byte[bbf.remaining()];
                        bbf.get(bytes);
                        System.out.println("服务器收到客户端信息：" + new String(bytes, "UTF-8"));
                    }

                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                }
            }

            //处理后进行清除
        }
//        }


    }

}
