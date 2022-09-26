package com.custom.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Request
 * @Description 请求对象
 * @Author peco
 * @Date 2022/9/26 14:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    /** 服务描述 */
    private ServiceDescriptor serviceDescriptor;
    /** 参数 */
    private Object[] parameters;
}
