package com.yangy.common.rabbitmq;

public interface RabbitMQService {


    void send(Object info, String queueName, String exchangeName);

    void sendDelay(Object info, String queueName, String exchangeName, Long delayTime);

}
