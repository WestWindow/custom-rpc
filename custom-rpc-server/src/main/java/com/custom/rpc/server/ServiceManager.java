package com.custom.rpc.server;

import com.custom.rpc.proto.Request;
import com.custom.rpc.proto.ServiceDescriptor;
import com.custom.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ServiceManager
 * @Description 管理rpc暴露的服务
 * @Author peco
 * @Date 2022/10/21 19:20
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis=new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp,sis);
            log.info("register service: {} {}",sdp.getClazz(),sis.getMethod());
        }

    }


    public ServiceInstance lookUp(Request request){
        ServiceDescriptor sdp=request.getServiceDescriptor();
        return services.get(sdp);
    }
}
