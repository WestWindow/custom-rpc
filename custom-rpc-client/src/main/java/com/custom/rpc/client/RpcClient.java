package com.custom.rpc.client;

import com.custom.rpc.codec.Decoder;
import com.custom.rpc.codec.Encoder;
import com.custom.rpc.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @ClassName RpcClient
 * @Description TODO
 * @Author peco
 * @Date 2022/10/26 16:53
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        decoder = ReflectionUtils.newInstance(this.config.getDncoderClass());
        selector = ReflectionUtils.newInstance(this.config.getSelectorClass());
        this.selector.init(this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );

    }


    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );


    }









}
