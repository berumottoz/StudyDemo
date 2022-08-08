package com.example.Zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ZkClient {
    private String connectString = "192.168.1.7:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws Exception {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    System.out.println("----------Watcher方法监控----------");
                    List<String> children = zkClient.getChildren("/", true);
                    children.stream().forEach(c -> System.out.println(c));
                    Thread.sleep(Long.MAX_VALUE);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Test
    public void create() throws Exception {
        // 参数 1：要创建的节点的路径； 参数 2：节点数据 ； 参数 3：节点权限 ；参数 4：节点的类型
        String nodeCreated = zkClient.create("/ZkTest", "demo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
    }

    // 获取子节点
    // 注册一次只监听一次
    @Test
    public void getChildren() throws Exception {
        System.out.println("----------getChildren方法监控----------");
        List<String> children = zkClient.getChildren("/", true);
        children.stream().forEach(c -> System.out.println(c));
        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }

    // 判断 znode 是否存在
    @Test
    public void exist() throws Exception {
        Stat stat = zkClient.exists("/ZkTest0000000001", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}
