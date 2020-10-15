package day01;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CreateNode {

    @Test
    public void createNode() throws Exception {
        //重试连接策略
        //baseSleepTimeMs:重试的时间间隔
        //maxRetries:重试的次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,1);

        //获取客户端对象
        /*
        * 1.要连接的zookeeper服务器列表
        * 2.会话超时时间
        * 3.链接超时时间
        * 4.重试策略
        * */

        //可以写主机名node01代替ip，但是需要在hosts文件中设置主机和ip的映射
        String connectionStr = "192.168.163.110:2181,192.168.163.120:2181,192.168.163.130:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr,3000,8000,retryPolicy);
        //开启客户端
        client.start();
        //创建节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/hello2","world2".getBytes());
        //关闭客户端
        client.close();
    }
    @Test
    public void createTmpNode() throws Exception {
        //重试连接策略
        //baseSleepTimeMs:重试的时间间隔
        //maxRetries:重试的次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,1);

        //获取客户端对象
        /*
         * 1.要连接的zookeeper服务器列表
         * 2.会话超时时间
         * 3.链接超时时间
         * 4.重试策略
         * */

        //可以写主机名node01代替ip，但是需要在hosts文件中设置主机和ip的映射
//        String connectionStr = "192.168.163.110:2181,192.168.163.120:2181,192.168.163.130:2181";
        String connectionStr = "192.168.163.110:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr,3000,8000,retryPolicy);
        //开启客户端
        client.start();
        //创建节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/tmp","tmp1".getBytes());
        //关闭客户端

//        Thread.sleep(8000);
        client.close();
    }

    @Test
    public void changeZnodeData() throws Exception {
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,2);
        String connect = "192.168.163.110:2181";
        //创建客户端
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connect,2000,2000,retryPolicy);
        //打开客户端
        curatorFramework.start();
        //修改节点值
        curatorFramework.setData().forPath("/hello","def".getBytes());
        //关闭客户端
        curatorFramework.close();
    }

    @Test
    public void deleteZnode() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000,2);

        String connectStr = "192.168.163.110:2181";
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectStr,2000,2000,retryPolicy);

        curatorFramework.start();
        curatorFramework.delete().forPath("/hello2");
        curatorFramework.close();
    }

    @Test
    public void getZnodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000,2);

        String connectStr = "192.168.163.110:2181";
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectStr,2000,2000,retryPolicy);

        curatorFramework.start();
//        byte[] bytes =  curatorFramework.getData().forPath("/hello");
//        System.out.println(new String(bytes));
//        curatorFramework.getChildren();
        curatorFramework.close();
    }

    @Test
    public void watchNode() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000,2);

        String connectStr = "192.168.163.110:2181,192.168.163.120:2181,192.168.163.130:2181";
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectStr,2000,2000,retryPolicy);

        curatorFramework.start();

        //创建节点缓存映射
        TreeCache treeCache = new TreeCache(curatorFramework,"/hello3");

        //自定义一个监听器
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                ChildData data = treeCacheEvent.getData();
                if(data != null){
                    switch (treeCacheEvent.getType()){
                        case NODE_ADDED:
                            System.out.println("监控到新增节点");
                            break;
                        case NODE_REMOVED:
                            System.out.println("监控到移除节点");
                            break;
                        case NODE_UPDATED:
                            System.out.println("监控到节点更新");
                            break;
                            default:
                                break;
                    }
                }
            }
        });

        //开始监听
        treeCache.start();
        Thread.sleep(1000000);
        curatorFramework.close();
    }
}

