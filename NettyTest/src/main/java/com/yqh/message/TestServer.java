package com.yqh.message;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import com.yqh.message.Server_Handler;;


public class TestServer {

    private int port;

    public TestServer(int port) {
        this.port = port;
        bind();
    }

    private void bind() {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss, worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024); //连接数
            bootstrap.option(ChannelOption.TCP_NODELAY, true);  //不延迟，消息立即发送
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); //长连接
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                        throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //pipeline.addLast(new LineBasedFrameDecoder(1024));
                    pipeline.addLast("decoder", new ObjectDecoder(1024*1024,ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                    pipeline.addLast("encoder", new ObjectEncoder());

                    pipeline.addLast(new Server_Handler());
                }
            });
            ChannelFuture f = bootstrap.bind(port).sync();
            if (f.isSuccess()) {
               // logger.debug("启动Netty服务成功，端口号：" + this.port);
                System.out.println("ttt启动Netty服务成功，端口号：" + this.port);
            }
            // 关闭连接
            f.channel().closeFuture().sync();

        } catch (Exception e) {
           // logger.error("启动Netty服务异常，异常信息：" + e.getMessage());
            System.out.println("启动Netty服务异常，异常信息：" + e.getMessage());
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) {
		TestServer ts=new TestServer(9999);
		
	}

}
