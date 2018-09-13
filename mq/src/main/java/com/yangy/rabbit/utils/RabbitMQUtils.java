package com.yangy.rabbit.utils;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import com.yangy.common.exception.MyException;
import com.yangy.rabbit.config.RabbitMQConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * MQ工具类
 *
 * @author yang yang
 * @create 2018/9/12
 * @since 1.0.0
 */
public class RabbitMQUtils {

    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(RabbitMQConfig.HOST);
        factory.setPort(RabbitMQConfig.PORT);
        factory.setUsername(RabbitMQConfig.USERNAME);
        factory.setPassword(RabbitMQConfig.PASSWORD);
        factory.setConnectionTimeout(RabbitMQConfig.CONNECTIONTIMEOUT);
        factory.setShutdownTimeout(RabbitMQConfig.SHUTDOWNTIMEOUT);

        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        throw new MyException("获取连接失败");
    }

    public static Channel getChannel() {
        Connection connection = getConnection();
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new MyException("创建Channel失败");
    }

    /**
     * 发送即时消息
     *
     * @param exchangeName 交换机
     * @param queueName    队列名称
     * @param payload      消息体
     */
    public static void send(String exchangeName, String queueName, Object payload) {
        Channel channel = getChannel();
        try {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, queueName);

            channel.confirmSelect();
            channel.basicPublish(exchangeName, queueName, true,
                    new AMQP.BasicProperties
                            .Builder()
                            .contentType("application/json")
                            .contentEncoding("UTF-8")
                            .build(),
                    JSON.toJSONString(payload).getBytes());

            if (!channel.waitForConfirms()) {
                throw new MyException("发送消息失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Connection connection = channel.getConnection();
            closeChannel(channel, connection);
        }
    }

    /**
     * 发送延时消息
     *
     * @param exchangeName  交换机
     * @param queueName     队列名称
     * @param deadQueueName 死信队列名称
     * @param payload       消息体
     * @param delayTime     延时时间
     */
    public static void sendDelay(String exchangeName, String queueName, String deadQueueName, Object payload, Long delayTime) {
        Channel channel = getChannel();
        try {
            //交换器|队列|死信队列 创建与关系绑定
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-max-length", 10000);
            arguments.put("x-dead-letter-exchange", exchangeName);
            arguments.put("x-dead-letter-routing-key", queueName);
            channel.queueDeclare(deadQueueName, true, false, false, null);
            channel.queueDeclare(queueName, true, false, false, null);

            channel.queueBind(queueName, exchangeName, queueName);
            channel.queueBind(deadQueueName, exchangeName, deadQueueName);

            //开启发送
            channel.confirmSelect();
            //消息的发送
            channel.basicPublish(exchangeName, queueName, true,
                    new AMQP.BasicProperties
                            .Builder()
                            .contentType("application/json")
                            .contentEncoding("UTF-8")
                            .expiration(delayTime + "")
                            .build(),
                    JSON.toJSONString(payload).getBytes());

            if (!channel.waitForConfirms()) {
                throw new MyException("发送消息失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Connection connection = channel.getConnection();
            closeChannel(channel, connection);
        }
    }

    /**
     * get方式获取消息 对应的是
     *
     * @param queueName 队列名称
     * @return
     */
    public static String get(String queueName) {
        Channel channel = getChannel();

        try {
            channel.queueDeclare(queueName, true, false, false, null);

            channel.basicQos(1);
            GetResponse getResponse = channel.basicGet(queueName, false);
            if (null != getResponse && null != getResponse.getEnvelope()) {
                channel.basicAck(getResponse.getEnvelope().getDeliveryTag(), false);
                return new String(getResponse.getBody());
            } else {
                throw new MyException("获取消息失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Connection connection = channel.getConnection();
            closeChannel(channel, connection);
        }
        throw new MyException("获取消息失败");
    }

    /**
     * 消费者方式获取消息
     *
     * @param queueName 队列名称
     */
    public static void consume(String queueName) {
        Channel channel = getChannel();

        try {
            channel.queueDeclare(queueName, true, false, false, null);

            channel.basicQos(1);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException {

                    if (null != envelope) {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        System.out.println("队列中存储的消息 ：" + new String(body));
                    }
                }
            };
            channel.basicConsume(queueName, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                long count = channel.messageCount(queueName);
                if (count <= 0) {
                    Connection connection = channel.getConnection();
                    closeChannel(channel, connection);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接
     *
     * @param channel    当前通道
     * @param connection 当前连接
     */
    public static void closeChannel(Channel channel, Connection connection) {
        try {
            if (null != channel) {
                channel.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}