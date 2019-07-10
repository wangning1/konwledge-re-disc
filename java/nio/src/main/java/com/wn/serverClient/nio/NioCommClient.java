package com.wn.serverClient.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by wangning on 2017/11/8.
 */
public class NioCommClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        if (socketChannel.connect(new InetSocketAddress("localhost", 8088))) {
            System.out.println(1);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            System.out.println(2);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();


                if (key.isConnectable()) {
                    System.out.println("--get in connect");
                    if (socketChannel.finishConnect()) {
                        System.out.println("完成连接");
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                }

                if (key.isReadable()) {
                    System.out.println("--收--");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
//                    byteBuffer.flip();
                    byteBuffer.get(bytes);
                    System.out.println("从服务端获取到数据为：" + (new String(bytes)));
                    socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                }

                if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    System.out.println("发");
                    String response = "客户端给你的响应！！";
//                    Scanner scanner = new Scanner(System.in);
//                    String str = scanner.next();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(response.getBytes().length);
                    byteBuffer.put(response.getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }

            }
        }


    }
}
