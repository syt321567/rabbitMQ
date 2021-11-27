package com.rabbit.ps;

import com.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/** 消费者
 * @author suoyutian
 * @date 2021年11月27日 2:51 下午
 */
public class Recer1 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //创建信道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare("test_exchange_fanout_queue1",false,false,false,null);
        //绑定路由
        channel.queueBind("test_exchange_fanout_queue1","test_exchange_fanout","");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s =new String(body);
                System.out.println("【消费者1】="+s);
            }
        };

        channel.basicConsume("test_exchange_fanout_queue1",true,consumer);
    }
}
