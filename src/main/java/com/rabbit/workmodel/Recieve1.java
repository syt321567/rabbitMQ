package com.rabbit.workmodel;

import com.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author suoyutian
 * @date 2021年11月27日 11:33 上午
 */
public class Recieve1 {

    static int i = 1;

    public static void main(String[] args) throws IOException, TimeoutException {


        Connection connection = ConnectionUtil.getConnection();

        final Channel channel = connection.createChannel();
        channel.queueDeclare("test_work_queue", false, false, false, null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new java.lang.String(body);
                System.out.println("【顾客1】 吃掉" + msg + "共吃掉【" + i++ + "】串");

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume("test_work_queue",false,consumer);

    }

}
