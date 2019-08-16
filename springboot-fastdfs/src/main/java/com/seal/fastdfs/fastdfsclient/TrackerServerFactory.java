package com.seal.fastdfs.fastdfsclient;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;


public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

    public TrackerServer create() throws Exception {
        //实例化TrackerClient
        TrackerClient client = new TrackerClient();
        //获取TrackerServer
        TrackerServer trackerServer = client.getConnection();
        return trackerServer;
    }

    public PooledObject<TrackerServer> wrap(TrackerServer server) {
        return new DefaultPooledObject<TrackerServer>(server);
    }
}
