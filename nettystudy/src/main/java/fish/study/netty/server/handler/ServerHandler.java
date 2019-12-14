package fish.study.netty.server.handler;

import com.alibaba.fastjson.JSON;
import fish.study.netty.model.LoginRequestPacket;
import fish.study.netty.model.LoginResponsePacket;
import fish.study.netty.model.Packet;
import fish.study.netty.packet.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(JSON.toJSON(msg));

        Packet packet = PacketCode.INSTANCE.decode(byteBuf);
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            if (valid(loginRequestPacket)) {
                System.out.println(LocalDateTime.now() + ": 客户端登录成功");
                loginResponsePacket.setSuccess(true);
            } else {
                System.out.println(LocalDateTime.now()  + ": 客户端登录失败");
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
            }

            loginResponsePacket.setSuccess(true);
            ByteBuf responseByteBuf = PacketCode.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);

        }


    }

    private Boolean valid(LoginRequestPacket loginRequestPacket){
        System.out.println("客户端请求ID ： "+loginRequestPacket.getUserId());
        System.out.println("客户端请求密码：" +loginRequestPacket.getPassword());
        return true;
    }
}
