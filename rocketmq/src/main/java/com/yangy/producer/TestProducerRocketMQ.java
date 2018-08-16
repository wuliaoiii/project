//package com.yangy.producer;
//
//import com.alibaba.rocketmq.client.exception.MQClientException;
//import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
//import com.alibaba.rocketmq.client.producer.SendResult;
//import com.alibaba.rocketmq.common.message.Message;
//
///**
// * @Author chenfanglin 【chenfanglincfl@163.com】
// * @Date 2017/4/2118:00
// */
//public class TestProducerRocketMQ {
//
//    public static void main(String[] args) throws MQClientException{
//
//        DefaultMQProducer producer = new DefaultMQProducer("producerGroupName");
//        producer.setNamesrvAddr("192.168.2.235:9876");
//        producer.start();
//        try {
//            {
//                //topic:主题 testTopic.tags:主题下的tag tagA.keys:keyA.body:具体业务消息体
//                Message msg = new Message("testTopic","tagA","keyA",("Hello World tagA!").getBytes());
//                for(int i=100;i>0;i--){
//                    if(i%2==0){
//                        SendResult sendResult = producer.send(msg);
//                        Thread.sleep(1000);
//                        System.out.println("tagA send result："+sendResult);
//                    }else{
//                        msg = new Message("testTopic","tagB","keyB",("Hello World tagB!").getBytes());
//                        SendResult sendResult = producer.send(msg);
//                        Thread.sleep(1000);
//                        System.out.println("tagB send result:"+sendResult);
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        //注销producer
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> producer.shutdown()));
//        System.exit(0);
//    }
//}