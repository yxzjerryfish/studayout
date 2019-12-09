package fish.study.netty.serializer;

import fish.study.netty.serializer.impl.JsonSerializer;


public interface Serializer {
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    /**
     * 默认序列化方法
     */
    Serializer DEFAULT = new JsonSerializer();
}
