package com.wn.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by wangning on 2017/11/9.
 */
public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void work() {
        EventLoopGroup loopGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(loopGroup).channel(NioServerSocketChannel.class).localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast("decoder",new StringDecoder())
                                    .addLast("encoder", new StringEncoder()).addLast(new NettyServerHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind().sync();
            System.out.println(NettyServer.class.getName()+" started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                loopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        NettyServer server = new NettyServer(7707);
        server.work();
    }

}
