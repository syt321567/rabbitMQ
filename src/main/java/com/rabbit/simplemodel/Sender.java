package com.rabbit.simplemodel;

import com.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author suoyutian
 * @date 2021年11月26日 5:16 下午
 */
public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {


        String msg = " 你好  rabbitmq";

        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建信道
        Channel channel = connection.createChannel();
        //3.创建消息队列
        /*参数1:队列的名称
        参数2:队列中的数据是否持久化
        参数3:是否排外（是否支持扩展，当前队列只能自己用，不能给别人用）
        参数4:是否自动删除（当队列的连接数为0时，队列会销毁，不管队列是否还存保存数据）
        参数5:队列参数（没有参数为null）
        */
        channel.queueDeclare("queue1", false, false, false, null);
        //向指定的消息队列发送消息
        /*  参数1:交换机名称，当前是简单模式，也就是P2P模式，没有交换机，所以名称为""
            参数2:目标队列的名称
            参数3:设置消息的属性（没有属性则为null）
            参数4:消息的内容(只接收字节数组)
             */
        channel.basicPublish("", "queue1", null, msg.getBytes());
        System.out.println("发送：" + msg);
        channel.close();
        connection.close();


    }

}
