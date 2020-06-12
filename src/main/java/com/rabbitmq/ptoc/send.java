package com.rabbitmq.ptoc;
import com.rabbitmq.Util.rabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;

/**
 * 直连模式
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
            String message="hello world";
            //发布消息
            //参数1：交换机名称
            //参数2;队列名称
            //参数3：传递消息额外设置
            //参数4：消息的具体内容
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            System.out.println("Send:"+message);
           // rabbitmqUtil.closeConnect(channel,connection);
    }
}
