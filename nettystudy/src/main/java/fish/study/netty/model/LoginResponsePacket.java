package fish.study.netty.model;

import lombok.Data;

import static fish.study.netty.constant.PacketConstant.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
