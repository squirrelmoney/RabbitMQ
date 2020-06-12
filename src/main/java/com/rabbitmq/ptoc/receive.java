package com.rabbitmq.ptoc;

import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
/**
 * 直连模式
 */
public class receive {
    private  final  static  String QUEUE_NAME="hello";
    public static void main(String[] args) throws IOException {

            Connection connection = rabbitmqUtil.connect() ;
            Channel channel =connection.createChannel() ;
            //通道绑定对象
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            //回调函数
            DeliverCallback deliverCallback=((consumerTag, deliver) -> {
                String message=new String(deliver.getBody(),"UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            });
            //消费信息
            //参数1：消费那个队列的消息 队列名称
            //参数2：开始消息的自动确认机制
            //参数3：消费时的回调接口
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag ->{} );

    }
}
