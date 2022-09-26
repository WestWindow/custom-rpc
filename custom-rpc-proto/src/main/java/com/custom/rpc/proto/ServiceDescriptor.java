package com.custom.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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





}
