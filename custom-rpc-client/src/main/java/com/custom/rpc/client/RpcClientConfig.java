package com.custom.rpc.client;

import com.custom.rpc.codec.Decoder;
import com.custom.rpc.codec.Encoder;
import com.custom.rpc.codec.JSONDecoder;
import com.custom.rpc.codec.JSONEncoder;
import com.custom.rpc.proto.Peer;
import com.custom.rpc.tansport.HTTPTransportClient;
import com.custom.rpc.tansport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RpcClientConfig
 * @Description TODO
 * @Author peco
 * @Date 2022/10/24 16:00
 */
@Data
public class RpcClientConfig {

    private Class<? extends TransportClient>  transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass= JSONEncoder.class;
    private Class<? extends Decoder> dncoderClass= JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass= RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers=Arrays.asList( new Peer("127.0.0.1",3000));



}
