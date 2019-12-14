package fish.study.netty.server;

import fish.study.netty.server.handler.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Server {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        NioEventLoopGroup w = new NioEventLoopGroup();
        NioEventLoopGroup r = new NioEventLoopGroup();

        bootstrap.group(w,r).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel){
//                nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
//                    @Override
//                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//                        ByteBuf byteBuf = (ByteBuf) msg;
//
//                        System.out.println(LocalDateTime.now() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
//                    }
//                });
                nioSocketChannel.pipeline().addLast(new ServerHandler());
            }
        }).bind(6546);
    }
}
