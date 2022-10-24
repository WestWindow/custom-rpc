package com.custom.rpc.tansport;

import com.custom.rpc.proto.Peer;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName HTTPTansportClient
 * @Description 实现http协议的客户端
 * @Author peco
 * @Date 2022/9/27 13:47
 */
public class HTTPTransportClient implements TransportClient {

    private String url;
    @Override
    public void connect(Peer peer) {
        this.url= "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            IOUtils.copy(data, outputStream);
            int resultCode = httpURLConnection.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
