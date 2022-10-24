package com.custom.rpc.tansport;

import com.custom.rpc.proto.Peer;

import java.io.InputStream;

/**
 * 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 *
 * @ClassName TansportClient
 * @Description 网络模块客户端接口
 * @Author peco
 * @Date 2022/9/26 16:31
 */
public interface TransportClient {

    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();



}
