package com.rabbitmq.fanout;
import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 广播
 */
public class send {
    public static void main(String[] args) throws IOException {
        Connection connection =rabbitmqUtil.connect() ;
        Channel channel =connection.createChannel() ;
        channel.exchangeDeclare("logs","fanout");//广播一条消息,多个消费者同时消费
        String message = "Hello World";
        //发布消息
        //参数1：交换机名称
        //参数2;队列名称
        //参数3：传递消息额外设置
        //参数4：消息的具体内容
        channel.basicPublish("logs","",null,message.getBytes());
        System.out.println("Send:"+message);
        // rabbitmqUtil.closeConnect(channel,connection);
    }
}
