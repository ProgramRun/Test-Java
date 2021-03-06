package com.zad.java;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * ZK Java API
 *
 * @author zad
 * @create 2018-09-07 6:01
 */
@Slf4j
public class JavaAPI implements Watcher {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        JavaAPI watcher = new JavaAPI();
        zk = new ZooKeeper(ZK_CONNECTION, 2000, watcher);
        countDownLatch.await();
        // 创建一个持久节点
        //zk.create("/zad1", "1991".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        /*Stat exists = zk.exists("/zad1" + "/node1", watcher);

        if (exists == null) {
            // 创建节点
            zk.create("/zad1" + "/node1", "0207".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            TimeUnit.SECONDS.sleep(10L);
            // 修改节点
            zk.setData("/zad1/node1", "0208".getBytes(), -1);
        }
        // 删除节点
        zk.delete("/zad1/node1", -1);*/

        // 获取节点
        List<String> children = zk.getChildren("/", true);
        System.out.println(children);

        // 控制权限
        zk.addAuthInfo("digest", "root:root".getBytes());
        zk.create("/auth1", "111".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }

    public void process(WatchedEvent event) {
        // 已经连接到服务端
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
                System.out.println("OK you're connected to server");
            } else if (Event.EventType.NodeDataChanged == event.getType()) {
                try {
                    System.out.println("node date changed");
                    log.info("data changed from {} to {}", event.getPath(), zk.getData(event.getPath(), true, new Stat()));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeCreated == event.getType()) {
                System.out.println("create a new node");
            } else if (Event.EventType.NodeDeleted == event.getType()) {
                System.out.println("huhu 再见" + event.getPath());
            }
        }
    }
}
