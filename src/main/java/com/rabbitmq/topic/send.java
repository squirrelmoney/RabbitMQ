package com.rabbitmq.topic;
import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 广播--直连
 */
public class send {
    public static void main(String[] args) throws IOException {
        Connection connection =rabbitmqUtil.connect() ;
        Channel channel =connection.createChannel() ;
        //生命交换机和交换机类型 topic 使用动态路由(通配符方式)
        channel.exchangeDeclare("topics","topic");
        String routekey1 = "user.save";//动态路由key
        String routekey2 = "user.login.insert";//动态路由key
        channel.basicPublish("topics",routekey1,null,"test 通配符*：匹配不多不少恰好1个词".getBytes());
        channel.basicPublish("topics",routekey2,null,"test #：匹配一个或多个词".getBytes());
        System.out.println("Send:");
        // rabbitmqUtil.closeConnect(channel,connection);
    }
}
