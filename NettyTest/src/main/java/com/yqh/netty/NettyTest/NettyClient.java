package com.yqh.netty.NettyTest;

/**
 * Created by qianhui.yin on 2016/1/19.
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class NettyClient {

    /*
     * 服务器端口号
     */
    private int port;

    /*
     * 服务器IP
     */
    private String host;

    public NettyClient(int port, String host)
            throws InterruptedException {
        this.port = port;
        this.host = host;
        start();
    }

    private void start() throws InterruptedException  {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.group(eventLoopGroup);
            bootstrap.remoteAddress(host, port);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                        throws Exception {
                    ChannelPipeline pipeline=  socketChannel.pipeline();
                    pipeline.addLast(new LineBasedFrameDecoder(1024));
                    pipeline.addLast("decoder", new StringDecoder());
                    pipeline.addLast("encoder", new StringEncoder(Charset.forName("UTF-8")));
                    // 【Charset.forName("UTF-8") WIN下可以，linux也可】
                    pipeline.addLast(new NettyClientHandler());
                   // pipeline.addLast(new ReadTimeoutHandler(10));
                }
            });
            ChannelFuture future = bootstrap.connect(host, port).sync();

            if (future.isSuccess()) {
                SocketChannel socketChannel = (SocketChannel) future.channel();
                System.out.println(socketChannel.remoteAddress()+"----------------connect server success----------------");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                while(true){
                    try{
                       // socketChannel.writeAndFlush("gagagaggaga.....\r\n");
                        socketChannel.writeAndFlush(in.readLine()+"\r\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }
            }
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {

     //NettyClient client = new NettyClient(9998, "localhost");
       NettyClient client2 = new NettyClient(9998, "45.78.40.30");
        //45.78.40.30  localhost

    }
}