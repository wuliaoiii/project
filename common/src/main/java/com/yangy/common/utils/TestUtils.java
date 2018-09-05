package com.yangy.common.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author yang yang
 * @create 2018/8/21
 * @since 1.0.0
 */
public class TestUtils {

//    public static void main(String[] args) {
//        try {
//            Connection connection = getConnection();
//            //获取通道
//            Channel channel = connection.createChannel();
//            //test 队列名称
//            channel.queueDeclare("com.yangy.queue.test", true, false, false, null);
//            //消息
//            channel.exchangeDeclare("com.yangy.exchange.test", BuiltinExchangeType.DIRECT, true);
//
//            channel.queueBind("com.yangy.queue.test", "com.yangy.exchange.test", "com.yangy.queue.test");
//
//            User user = new User();
//            User build = user.builder().address("chao yang").gender(1).phone("13300000000").username("yangy").userId(1).build();
//
//            //发送消息
//            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
//                    .builder()
//                    .contentType("application/json")
//                    .contentEncoding("UTF-8")
//                    .expiration("1000")
//                    .priority(1)
//                    .build();
//
//            channel.confirmSelect();
//
//            channel.basicPublish("com.yangy.exchange.test", "com.yangy.queue.test", true, MessageProperties.PERSISTENT_TEXT_PLAIN, JSON.toJSONBytes(build));
//
//            channel.addReturnListener(new ReturnListener() {
//                @Override
//                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    System.out.println("返回的结果" + new String(body));
//                }
//            });
//
//            SortedSet<Long> unconfirmedSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
//
//            channel.addConfirmListener(new ConfirmListener() {
//                @Override
//                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
//                    if (multiple) {
//                        unconfirmedSet.headSet(deliveryTag - 1).clear();
//                    } else {
//                        unconfirmedSet.remove(deliveryTag);
//                    }
//                }
//
//                @Override
//                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
//                    if (multiple) {
//                        unconfirmedSet.headSet(deliveryTag - 1).clear();
//                    } else {
//                        unconfirmedSet.remove(deliveryTag);
//                    }
//                    //
//                }
//            });
//
//
//            long nextPublishSeqNo = channel.getNextPublishSeqNo();
//            unconfirmedSet.add(nextPublishSeqNo);
//
//            if (!channel.waitForConfirms()) {
//
//            }
//            //关闭频道 关闭连接
//            channel.close();
//            connection.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        List list = null;

        CollectionUtils.isEmpty(list);
        boolean empty = list.isEmpty();

    }

    public static Connection getConnection() throws IOException, TimeoutException {
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
        return connectionFactory.newConnection();
    }
}