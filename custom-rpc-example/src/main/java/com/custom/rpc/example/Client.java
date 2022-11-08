package com.custom.rpc.example;

import com.custom.rpc.client.RpcClient;

/**
 * @ClassName Client
 * @Description TODO
 * @Author peco
 * @Date 2022/11/8 14:46
 */
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService calcService = client.getProxy(CalcService.class);
        int r1 = calcService.add(1, 2);
        int r2 =calcService.minus(10,8);

        System.out.println(r1);
        System.out.println(r2);

    }
}
