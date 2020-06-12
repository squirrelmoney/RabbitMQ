package com.rabbitmq.topic;

import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * 广播--直连
 */
public class receiveA {
    public static void main(String[] args) throws IOException {
            Connection connection = rabbitmqUtil.connect() ;
            Channel channel =connection.createChannel() ;
            channel.exchangeDeclare("topics","topic");
            //创建临时队列
            String queue = channel.queueDeclare().getQueue();
            //将临时队列绑定exchange
            channel.queueBind(queue,"topics","*.save");
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            //回调函数
            DeliverCallback deliverCallback=((consumerTag, deliver) -> {
                String message=new String(deliver.getBody(),"UTF-8");
                    System.out.println("[x]Received '" + message + "'");
            });
            channel.basicConsume(queue,true,deliverCallback,consumerTag ->{} );

    }

}
