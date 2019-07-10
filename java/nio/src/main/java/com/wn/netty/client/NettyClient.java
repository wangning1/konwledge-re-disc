package com.wn.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created by wangning on 2017/11/9.
 */
public class NettyClient {

    private String host;
    private int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void work() {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder",new StringDecoder())
                                    .addLast("encoder", new StringEncoder()).addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String msg = "客户端说：" + scanner.next();
                future.channel().writeAndFlush(msg + "\r\n" );
            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("--error:" + e.getMessage());
        } finally {
            try {
                loopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient("localhost", 7707);
        client.work();
    }

}
