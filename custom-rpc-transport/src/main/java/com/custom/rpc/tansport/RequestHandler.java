package com.custom.rpc.tansport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName RequestHandle
 * @Description 网络请求的Handle
 * @Author peco
 * @Date 2022/9/26 16:37
 */
public interface RequestHandler {

    void onRequest(InputStream revice, OutputStream toResp);

}
