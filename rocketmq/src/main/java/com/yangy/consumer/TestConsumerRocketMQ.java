//package com.yangy.consumer;
//
//import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
//import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import com.alibaba.rocketmq.client.exception.MQClientException;
//import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
//import com.alibaba.rocketmq.common.message.MessageExt;
//
//import java.util.List;
//
///**
// * @Author chenfanglin 【chenfanglincfl@163.com】
// * @Date 2017/4/2118:04
// */
//public class TestConsumerRocketMQ {
//    public static void main(String[] args) throws InterruptedException, MQClientException {
//
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testProducerGroupName");
//        consumer.setNamesrvAddr("192.168.2.235:9876");
//
//        //订阅testTopic主题下，标签为tagA或者tagB的消息
//        consumer.subscribe("testTopic", "tagA || tagB");
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//                                                            ConsumeConcurrentlyContext context) {
//                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
//                MessageExt msg = msgs.get(0);
//                if (msg.getTopic().equals("testTopic")) {
//                    if (msg.getTags() != null && msg.getTags().equals("tagA")) {
//                        // 执行tagA的消费
//                        String message = new String(msg.getBody());
//                        System.out.println("receive tagA message:"+message);
//                    }
//                    else if (msg.getTags() != null && msg.getTags().equals("tagB")) {
//                        // 执行tagB的消费
//                        String message = new String(msg.getBody());
//                        System.out.println("receive tagB message:"+message);
//                    }
//
//                }
//                //回执消息确认
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.start();
//        System.out.println("Consumer Started.");
//    }
//}