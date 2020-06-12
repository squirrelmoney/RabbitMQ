package com.rabbitmq.direct;
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
        channel.exchangeDeclare("logs_direct","direct");//广播一条消息,多个消费者同时消费

        //发布消息
        //参数1：交换机名称
        //参数2;队列名称
        //参数3：传递消息额外设置
        //参数4：消息的具体内容
        channel.basicPublish("logs_direct","severity",null,"test serverity".getBytes());
        channel.basicPublish("logs_direct","error",null,"test error".getBytes());
        System.out.println("Send:");
        // rabbitmqUtil.closeConnect(channel,connection);
    }
}
