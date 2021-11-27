package com.rabbit.workmodel;

import com.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**  工作队列模式  多个消费这从队列中领取任务进行消费
 * @author suoyutian
 * @date 2021年11月27日 11:33 上午
 */
public class Sender {


    public static void main(String[] args) throws IOException, TimeoutException {


        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //获取信道
        Channel channel = connection.createChannel();

        // 创建工作队列
        channel.queueDeclare("test_work_queue", false, false, false, null);


        for (int i = 1; i <= 100; i++) {
            String msg="羊肉串---> "+i;
            channel.basicPublish("","test_work_queue",null,msg.getBytes());
            System.out.println("师傅烤好："+msg);
        }
        channel.close();
        connection.close();
    }
}
