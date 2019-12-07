package fish.study.netty.model;

import lombok.Data;

@Data
public class LoginRequestPacket extends Packet{

    private static Byte LOGIN_REQUEST = 1;

    private Integer userId;

    private String userName;

    private String password;


    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
