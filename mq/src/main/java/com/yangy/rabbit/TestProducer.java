package com.yangy.rabbit;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import com.yangy.common.entity.User;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送方
 *
 * @author yang yang
 * @create 2018/8/21
 * @since 1.0.0
 */
public class TestProducer {

    public static void main(String[] args) {
        //获取连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //地址
        connectionFactory.setHost("127.0.0.1");
        //端口号
        connectionFactory.setPort(5672);
        //用户名
        connectionFactory.setUsername("guest");
        //密码
        connectionFactory.setPassword("guest");
        //获取连接
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            //获取通道
            Channel channel = connection.createChannel();
            //test 队列名称
            channel.queueDeclare("com.yangy.mq.test", false, false, false, null);
            //消息

            User user = new User();
            User build = user.builder().address("chao yang").gender(1).phone("13300000000").username("yangy").userId(1).build();

            //发送消息
            channel.basicPublish("", "test", new AMQP.BasicProperties()
                    .builder()
                    .contentType("application/json")
                    .contentEncoding("UTF-8")
                    .expiration("1000")
                    .priority(1)
                    .build(), JSON.toJSONBytes(build));

            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {

                }
            });

            //关闭频道 关闭连接
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}