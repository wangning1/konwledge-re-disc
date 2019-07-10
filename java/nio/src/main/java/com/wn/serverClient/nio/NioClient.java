package com.wn.serverClient.nio;

import java.io.IOException;
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
public class NioClient {
    public static void main(String[] args) throws Exception {
//        Selector selector = Selector.open();
        final SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8088));
        socketChannel.configureBlocking(false);

        //客户端建立连接
//        socketChannel.register(selector, SelectionKey.OP_CONNECT);
//        socketChannel.isConnected();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("客户端发送消息~~~");
//                String clientMsg = "客户端首发信息";
//                ByteBuffer byteBuffer = ByteBuffer.allocate(clientMsg.getBytes().length);
//                byteBuffer.put(clientMsg.getBytes());
//                byteBuffer.flip();
//                try {
//                    socketChannel.write(byteBuffer);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        System.out.println("客户端发送消息~~~");
        String clientMsg = "客户端首发信息";
        ByteBuffer byteBuffer = ByteBuffer.allocate(clientMsg.getBytes().length);
        byteBuffer.put(clientMsg.getBytes());
        byteBuffer.flip();
        try {
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            byteBuffer.flip();
            String str = scanner.next();
            byteBuffer.put(str.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        }
        socketChannel.close();
//        System.out.println("客户端接收消息~~~");
//        ByteBuffer bbf = ByteBuffer.allocate(1024);
//        int num = socketChannel.read(bbf);
//        if (num > 0) {
//            bbf.flip();
//            byte[] bytes = new byte[bbf.remaining()];
//            bbf.get(bytes);
//            System.out.println("客户端接收到服务端信息：" + (new String(bytes, "UTF-8")));
//        }

//        while (true) {
////            selector.select(1000);
//            Set<SelectionKey> selectionKeys = selector.selectedKeys();
//            Iterator<SelectionKey> iterator = selectionKeys.iterator();
//            System.out.println("--1");
//            while (iterator.hasNext()) {
//                System.out.println("--2");
//                SelectionKey key = iterator.next();
//                System.out.println("--3");
//
//                final SocketChannel channel = (SocketChannel) key.channel();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("客户端发送消息~~~");
//                        String clientMsg = "客户端首发信息";
//                        ByteBuffer byteBuffer = ByteBuffer.allocate(clientMsg.getBytes().length);
//                        byteBuffer.put(clientMsg.getBytes());
//                        byteBuffer.flip();
//                        try {
//                            channel.write(byteBuffer);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//                System.out.println("--4");
//                if (key.isReadable()) {
//                    System.out.println("客户端接收消息~~~");
//                    ByteBuffer bbf = ByteBuffer.allocate(1024);
//                    int num = channel.read(bbf);
//                    if (num > 0) {
//                        bbf.flip();
//                        byte[] bytes = new byte[bbf.remaining()];
//                        bbf.put(bytes);
//                        System.out.println("客户端接收到服务端信息：" + (new String(bytes, "UTF-8")));
//                    }
//                }
//                iterator.remove();
//            }
//        }

    }

}
