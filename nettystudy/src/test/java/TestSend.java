import fish.study.netty.client.Client;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import static fish.study.netty.client.Client.startConsoleThread;

public class TestSend {
    public static void main(String[] args) {
        ChannelFuture channelFuture= Client.getChannel();
        channelFuture.addListener(future->{
            if(future.isSuccess()){
                System.out.println("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else {
                System.out.println("连接失败");
            }
        });
    }
}
