package com.yangy.common.rabbitmq.impl;

import com.yangy.common.rabbitmq.RabbitMQService;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yang yang
 * @create 2018/9/11
 * @since 1.0.0
 */
public class RabbitMQServiceImpl implements RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @param info
     * @param queueName
     * @param exchangeName
     */
    @Override
    public void send(Object info, String queueName, String exchangeName) {
        rabbitTemplate.convertAndSend(exchangeName, queueName, info);
    }

    /**
     * 发送消息到延迟队列
     *
     * @param info
     * @param queueName
     * @param exchangeName
     * @param delayTime
     */
    @Override
    public void sendDelay(Object info, String queueName, String exchangeName, Long delayTime) {
        MessagePostProcessor processor = message -> {
            message.getMessageProperties().setExpiration(delayTime + "");
            return message;
        };
        rabbitTemplate.convertAndSend(exchangeName, queueName, info, processor);
    }
}