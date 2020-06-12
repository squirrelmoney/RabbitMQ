package com.rabbitmq.workqueues;
import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;

/**
 * 工作队列
 * work queue
 * 工作队列（又称任务队列）的主要思想是避免立即执行资源密集型任务，
 * 而不得不等待它完成。相反，我们安排任务在以后完成。
 * 我们将任务封装 为消息并将其发送到队列。在后台运行的工作进程将弹出任务并最终执行作业
 */
public class send {
    private  final static  String QUEUE_NAME="hello";
    public static void main(String[] args) throws IOException {
        Connection connection =rabbitmqUtil.connect() ;
        Channel channel =connection.createChannel() ;
        //通道绑定对应消息队列
        //参数1;队列名称，如果队列不存在则会自动化创建
        //参数2：用来定义队列特殊性 是否有持久化
        //参数3： exclusive 是否独占队列
        //参数5： autoDelete 是否在消费完成后自动完成队列
        //参数6： 额外附加参数
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World";
        //发布消息
        //参数1：交换机名称
        //参数2;队列名称
        //参数3：传递消息额外设置
        //参数4：消息的具体内容
        for (int i=0;i<=20;i++)
        channel.basicPublish("",QUEUE_NAME,null,(i+message).getBytes());
        System.out.println("Send:"+message);
        // rabbitmqUtil.closeConnect(channel,connection);
    }
}
