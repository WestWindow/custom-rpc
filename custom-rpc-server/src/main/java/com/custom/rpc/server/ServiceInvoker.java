package com.custom.rpc.server;

import com.custom.rpc.proto.Request;
import com.custom.rpc.utils.ReflectionUtils;

/**
 * @ClassName ServiceInvoker
 * @Description 调用一个具体服务
 * @Author peco
 * @Date 2022/10/22 10:30
 */
public class ServiceInvoker {

    public Object invoker(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
