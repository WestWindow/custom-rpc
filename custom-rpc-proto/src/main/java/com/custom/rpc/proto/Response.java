package com.custom.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Reponse
 * @Description 响应对象
 * @Author peco
 * @Date 2022/9/26 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /** 相应码 [0:成功,-1:失败] */
    private int code;
    /** 相应信息 */
    private String message;
    /** 相应数据 */
    private Object data;
}
