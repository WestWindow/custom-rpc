package com.custom.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Peer
 * @Description 表示网络传输的一个端点
 * @Author peco
 * @Date 2022/9/26 14:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    /** 地址 */
    private String host;
    /** 端口 */
    private int port;

}
