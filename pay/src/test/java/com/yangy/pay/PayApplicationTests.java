package com.yangy.pay;

import com.yangy.common.rabbitmq.RabbitMQService;
import com.yangy.common.rabbitmq.enums.RabbitMQConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApplicationTests {

    @Autowired
    RabbitMQService rabbitMQService;


    @Test
    public void test() {
        rabbitMQService.sendDelay("shakdhsj", RabbitMQConstants.DEAD_QUEUE, RabbitMQConstants.EXCHANGE, 1_000L);
    }

}
