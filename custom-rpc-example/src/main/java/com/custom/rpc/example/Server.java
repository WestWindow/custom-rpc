package com.custom.rpc.example;

import com.custom.rpc.server.RpcServer;

/**
 * @ClassName Server
 * @Description TODO
 * @Author peco
 * @Date 2022/11/8 14:45
 */
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
