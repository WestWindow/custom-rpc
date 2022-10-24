package com.custom.rpc.client;

import com.custom.rpc.proto.Peer;
import com.custom.rpc.tansport.TransportClient;
import com.custom.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName RandomTransportSelector
 * @Description TODO
 * @Author peco
 * @Date 2022/10/24 15:51
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private List<TransportClient> clientList;

    public RandomTransportSelector() {
        clientList=new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peerList) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clientList.add(client);
            }
            log.info("connect server :{}", peer);

        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clientList.size());
        return clientList.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clientList.add(client);
    }

    @Override
    public synchronized void close() {
      for (TransportClient client:clientList){
          client.close();
      }
      clientList.clear();
    }
}
