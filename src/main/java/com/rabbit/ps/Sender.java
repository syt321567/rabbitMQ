package com.rabbit.ps;

import com.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/** 发布订阅模式    生产者生产消息
 * @author suoyutian
 * @date 2021年11月27日 2:48 下午
 */
public class Sender {


    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建路由
        channel.exchangeDeclare("test_exchange_fanout","fanout");
        //构建消息
        String msg="hellow 大家好";
        //发送消息
        channel.basicPublish("test_exchange_fanout","",null,msg.getBytes());
        System.out.println("生产者："+msg);
        channel.close();
        connection.close();
    }
}
