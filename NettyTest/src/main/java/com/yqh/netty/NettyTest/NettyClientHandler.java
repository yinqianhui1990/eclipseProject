package com.yqh.netty.NettyTest;

/**
 * Created by qianhui.yin on 2016/1/19.
 */
import java.io.UnsupportedEncodingException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
public class NettyClientHandler extends ChannelHandlerAdapter  {
    private  ByteBuf firstMessage;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* byte[] data = "Server,give me an APPLE".getBytes();
        firstMessage=Unpooled.buffer();
        firstMessage.writeBytes(data);*/
        ctx.writeAndFlush("Server,有人进来啦！\n");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
       /* ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);*/
        System.out.println( msg);
    }
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
