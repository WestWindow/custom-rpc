package com.custom.rpc.codec;

/**
 * @ClassName Dcoder
 * @Description 反序列化
 * @Author peco
 * @Date 2022/9/26 15:13
 */
public interface Decoder {

    <T> T decode( byte[] bytes,Class<T> clazz);

}
