package com.custom.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName ServiceDescriptor
 * @Description 服务描述类
 * @Author peco
 * @Date 2022/9/26 14:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    /** 类名称 */
    private String clazz;
    /** 方法名称 */
    private String method;
    /** 返回类型 */
    private String returnType;
    /** 参数类型 */
    private String[] parameterTypes;


    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] =parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return  sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) obj;

        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "clazz="+clazz
                +",method="+method
                +",returnType="+returnType
                +",parameterTypes="+ Arrays.toString(parameterTypes);
    }
}
