package fish.study.netty.model;

import fish.study.netty.constant.PacketConstant;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return PacketConstant.MESSAGE_REQUEST;
    }
}
