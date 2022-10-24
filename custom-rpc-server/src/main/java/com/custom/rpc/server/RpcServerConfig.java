package com.custom.rpc.server;

import com.custom.rpc.codec.Decoder;
import com.custom.rpc.codec.Encoder;
import com.custom.rpc.codec.JSONDecoder;
import com.custom.rpc.codec.JSONEncoder;
import com.custom.rpc.tansport.HTTPTransportServer;
import com.custom.rpc.tansport.TransportServer;
import lombok.Data;

/**
 * @ClassName RpcServerConfig
 * @Description server 配置
 * @Author peco
 * @Date 2022/9/27 14:20
 */
@Data
public class RpcServerConfig {
         private Class<? extends TransportServer> tansportClass= HTTPTransportServer.class;
         private Class<? extends Encoder> encoderClass= JSONEncoder.class;
         private Class<? extends Decoder> decoderClass= JSONDecoder.class;
         private int prot=3000;


}
