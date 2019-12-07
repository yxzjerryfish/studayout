package fish.study.netty.model;

import fish.study.netty.constant.PacketConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet{


    private Integer userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return PacketConstant.LOGIN_REQUEST;
    }
}
