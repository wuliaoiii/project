package com.yangy.rabbit.config;

import lombok.Builder;
import lombok.Data;

/**
 * rabbitMQ相关的配置
 *
 * @author yang yang
 * @create 2018/9/12
 * @since 1.0.0
 */
@Data
@Builder
public class RabbitMQConfig {

    public static String HOST = "127.0.0.1";
    public static int PORT = 5672;
    public static String USERNAME = "guest";
    public static String PASSWORD = "guest";
    public static int CONNECTIONTIMEOUT = 10_000;
    public static int SHUTDOWNTIMEOUT = 10_000;


}