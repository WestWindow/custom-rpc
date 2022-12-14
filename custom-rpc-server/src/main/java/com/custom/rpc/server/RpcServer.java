package com.custom.rpc.server;

import com.custom.rpc.codec.Decoder;
import com.custom.rpc.codec.Encoder;
import com.custom.rpc.proto.Request;
import com.custom.rpc.proto.Response;
import com.custom.rpc.tansport.RequestHandler;
import com.custom.rpc.tansport.TransportServer;
import com.custom.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName RpcServer
 * @Description TODO
 * @Author peco
 * @Date 2022/10/22 10:33
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        //net
        this.net = ReflectionUtils.newInstance(config.getTansportClass());
        this.net.init(config.getProt(), handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {

        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] inBytes = new byte[recive.available()];
                IOUtils.readFully(recive, inBytes, 0, recive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request {}", request);
                /**
                 * ????????????????????????
                 * 1.??????client?????????request?????? ????????? ServiceDescrptor????????????????????? ??? ??????
                 * 2.??????serviceManager?????????register??????RpcService???????????????class ??? ?????????serviceDescriptor?????????bean ???map????????????
                 */
                ServiceInstance serviceInstance = serviceManager.lookUp(request);
                /**
                 * ???????????????????????? ??????+?????? ?????????serviceManager????????????
                 */
                Object invoker = serviceInvoker.invoker(serviceInstance, request);
                resp.setData(invoker);
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: " + e.getClass().getName() + ":" + e.getMessage());
            } finally {
                try {
                    byte[] ecode = encoder.ecode(resp);
                    toResp.write(ecode);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }


        }
    };

}
