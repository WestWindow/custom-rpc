package com.custom.rpc.client;

import com.custom.rpc.codec.Decoder;
import com.custom.rpc.codec.Encoder;
import com.custom.rpc.proto.Request;
import com.custom.rpc.proto.Response;
import com.custom.rpc.proto.ServiceDescriptor;
import com.custom.rpc.tansport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName REmoteInvoker
 * @Description 调用远程服务的代理类
 * @Author peco
 * @Date 2022/10/26 17:43
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
         this.clazz=clazz;
         this.encoder=encoder;
         this.decoder=decoder;
         this.selector=selector;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if (resp == null || resp.getCode()!=0){
            throw new IllegalAccessException("fail to invoke remote:"+resp.getMessage());
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        Response resp;
        TransportClient client=null;
        try{
            client =selector.select();
            byte[] outBytes = encoder.ecode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            try {
                byte[] inBytes = new byte[revice.available()];
                IOUtils.readFully(revice, inBytes, 0, revice.available());
                resp = decoder.decode(inBytes, Response.class);
            } catch (IOException e) {
                log.warn(e.getMessage(),e);
                resp =new Response();
                resp.setCode(1);
                resp.setMessage("RpcClient got error:"+e.getClass()+":"+e.getMessage());
            }

        }finally {
            if (client !=null){
                selector.release(client);
            }
        }
        return resp;
    }
}
