package com.rabbitmq.workqueues;

import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * 工作队列
 * work queue
 * 工作队列（又称任务队列）的主要思想是避免立即执行资源密集型任务，
 * 而不得不等待它完成。相反，我们安排任务在以后完成。
 * 我们将任务封装 为消息并将其发送到队列。在后台运行的工作进程将弹出任务并最终执行作业
 */
public class receiveB {
    private  final  static  String QUEUE_NAME="hello";
    public static void main(String[] args) throws IOException {

        Connection connection = rabbitmqUtil.connect() ;
        Channel channel =connection.createChannel() ;
        channel.basicQos(1);//一次只接受一条未确认的消息
        //通道绑定对象
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //回调函数
        DeliverCallback deliverCallback=((consumerTag, deliver) -> {
            String message=new String(deliver.getBody(),"UTF-8");
                    System.out.println("[x]Received '" + message + "'");
                    channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);//手动确认消息，参数二：确认多个消息
        });
        //消费信息
        //参数1：消费那个队列的消息 队列名称
        //参数2：开启消息的自动确认机制（true）,flase:关闭自动确认机制，当不手动确认时，该消息在队列中会一直处于未接收状态
        //参数3：消费时的回调接口
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,consumerTag ->{} );

    }

}
