package com.rabbit.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author suoyutian
 * @date 2021年11月26日 3:53 下午
 */
public class ConnectionUtil {


    //获取与mq的连接
    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //远程ip地址和端口
        factory.setHost("39.104.87.214");
        factory.setPort(5672);
        //虚拟机地址
        factory.setVirtualHost("/lagou");
        //用户名
        factory.setUsername("syt");
        //密码
        factory.setPassword("123456");
        //3.通过工厂获得与MQ的连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) {

        Connection connection = null;
        try {
            connection = getConnection();
            System.out.println("connection"+connection);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
