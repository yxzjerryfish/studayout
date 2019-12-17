package fish.study.netty.client;

import fish.study.netty.client.handler.ClientHandler;
import fish.study.netty.model.MessageRequestPacket;
import fish.study.netty.packet.PacketCode;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(w)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch)  {
                        ch.pipeline().addLast(new ClientHandler());

                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                           @Override
                           public void channelActive(ChannelHandlerContext ctx) {
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
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else {
                System.out.println("连接失败");
            }
        });

    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCode.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
            }
        }).start();
    }

    private static ChannelFuture getChannel(){
        NioEventLoopGroup w = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(w)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch)  {
                        ch.pipeline().addLast(new ClientHandler());

                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                System.out.println(LocalDateTime.now()+ ": 客户端写出数据");
                                byte[] bytes = "你好!".getBytes(StandardCharsets.UTF_8);

                                ByteBuf buffer = ctx.alloc().buffer();

                                buffer.writeBytes(bytes);

                                ctx.channel().writeAndFlush(buffer);
                            }
                        });
                    }
                });

      return bootstrap.connect("localhost",6547);
    }
}
