package com.custom.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @ClassName ServerInstance
 * @Description 表示一个具体服务
 * @Author peco
 * @Date 2022/10/9 13:59
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;


}
