package fish.study.netty.serializer.impl;

import com.alibaba.fastjson.JSON;
import fish.study.netty.constant.PacketConstant;
import fish.study.netty.serializer.Serializer;

public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {

        return PacketConstant.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
