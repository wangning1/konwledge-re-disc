package com.wn.serverClient.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by wangning on 2017/11/8.
 */
public class NioSimpleClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8088));
//        socketChannel.bind();
        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String string = scanner.next();
            byteBuffer.put((new Date() + string).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.close();
    }

}
