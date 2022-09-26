package com.custom.rpc.tansport;

/**
 * 1.启动，监听端口
 * 2.接收请求
 * 3.关闭监听
 * @ClassName TansportServer
 * @Description 网络模块服务端接口
 * @Author peco
 * @Date 2022/9/26 16:36
 */
public interface TansportServer {

    void init(int port,RequestHandler handler);

    void start();

    void stop();

}
