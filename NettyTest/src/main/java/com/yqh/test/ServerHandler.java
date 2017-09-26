package com.yqh.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends ChannelHandlerAdapter{
	 public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	    @Override
	    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	        Channel incoming = ctx.channel();
	        for (Channel channel : channels) {
	            channel.writeAndFlush("-----------"+channels.size()+"------"+"[SERVER] - " + incoming.remoteAddress() + " 加入\n");
	        }
	        channels.add(ctx.channel());
	    }

	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	
	        Channel incoming = ctx.channel();
	        System.out.println(incoming.remoteAddress()+"说：" + msg);
	        for (Channel channel : channels) {
	            if (channel != incoming) {
	                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + "\n");
	            } else {
	                channel.writeAndFlush("[you]" + msg + "\n");
	            }
	        }
	      
	    }

	    @Override
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  
	        Channel incoming = ctx.channel();
	        for (Channel channel : channels) {
	            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
	        }
	        channels.remove(ctx.channel());
	    }
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	    		System.out.println("服务器检测异常！！！");
	    		//cause.printStackTrace();
	    		ctx.close();
	    }
}
