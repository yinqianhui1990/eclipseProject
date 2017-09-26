package com.yqh.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class Client_Handler extends ChannelHandlerAdapter{
	private  ByteBuf firstMessage;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
    	MessageTest msg=new MessageTest();
    	msg.setSender("yinqianhui");
    	msg.setMessage("Server,wo进来啦！\n");
        ctx.writeAndFlush(msg);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
    	MessageTest msgs=(MessageTest )msg;
        System.out.println( msgs.getSender()+":"+msgs.getMessage());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    		System.out.println("客户端发生异常！！！");
    		ctx.close();
    }
}
