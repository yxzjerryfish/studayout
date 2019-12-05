package fish.study.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(w)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {

                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        nioServerSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(LocalDateTime.now()+ ": 客户端写出数据");
                                byte[] bytes = "你好!".getBytes(StandardCharsets.UTF_8);

                                ByteBuf buffer = ctx.alloc().buffer();

                                buffer.writeBytes(bytes);

                                ctx.channel().writeAndFlush(buffer);
                            }
                        });
                    }
                });

        bootstrap.connect("localhost",6546).addListener(future->{
            if(future.isSuccess()){
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });

    }
}
