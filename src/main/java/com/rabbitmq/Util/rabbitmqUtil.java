package com.rabbitmq.Util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工具类
 * 连接工厂
 */
public class rabbitmqUtil {
    private static ConnectionFactory factory ;
    static {
        //重量级资源
        factory=new ConnectionFactory();
        factory.setHost("104.168.148.71");
        factory.setPort(5672);
        factory.setVirtualHost("ems");
        factory.setUsername("ems");
        factory.setPassword("123");
    }
    public static Connection connect(){
        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnect(Channel channel,Connection connection){
        try {
            if (channel!=null)  channel.close();
            if (connection!=null)  connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TimeoutException e) {
            e.printStackTrace();
        }
        }
    }

