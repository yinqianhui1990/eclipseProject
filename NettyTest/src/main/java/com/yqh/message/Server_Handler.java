package com.yqh.message;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server_Handler extends ChannelHandlerAdapter{
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
	    public void channelRead(ChannelHandlerContext ctx, Object msgs) {
	    	MessageTest msg=(MessageTest) msgs;
	        Channel incoming = ctx.channel();
	        System.out.println(msg.getSender()+":"+msg.getMessage());
	    	//System.out.println(msgs);
	        for (Channel channel : channels) {
	            if (channel != incoming) {
	                channel.writeAndFlush(msg);
	            } else {
	                channel.writeAndFlush(msg);
	            }
	        }
	      
	    }

	    @Override
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  
	        Channel incoming = ctx.channel();
	        MessageTest msg=new MessageTest();
	        msg.setSender("server");
	        msg.setMessage(incoming.remoteAddress() + " 离开");
	        for (Channel channel : channels) {
	        	
	            channel.writeAndFlush(msg);
	        }
	        channels.remove(ctx.channel());
	    }
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	    		System.out.println("服务器检测异常！！！");
	    		cause.printStackTrace();
	    		ctx.close();
	    }
}
