package com.custom.rpc.client;

import com.custom.rpc.proto.Peer;
import com.custom.rpc.tansport.TransportClient;

import java.util.List;

/**
 * @ClassName TransportSelector
 * @Description 表示选择哪个server去连接
 * @Author peco
 * @Date 2022/10/24 15:40
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peerList 可以连接的server端点信息
     * @param count client 与 server 建立多少个连接
     * @param clazz client 实现 class
     */
    void init(List<Peer> peerList,int count,Class<? extends TransportClient> clazz);
    /**
     * 选择一个transport 与 server 做交互
     * @return 网络client
     */
     TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
     void release(TransportClient client);

     void close();

}
