package com.rabbit.simplemodel;

import com.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 手动确认机制
 * @author suoyutian
 * @date 2021年11月26日 5:16 下午
 */
public class RecerByAck {


    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //创建信道
        final Channel channel = connection.createChannel();

        //3.从信道中获取信息
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println(s);
                //手动确认
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };
        channel.basicConsume("queue1", false, consumer);
    }
}
