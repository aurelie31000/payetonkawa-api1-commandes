package com.payetonkawa.order.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.Serializable;
@Configuration
public class RabbitMQConfig {
    public static final String PRODUIT_CHANGE_QUEUE = "produit-change-queue";
    public static final String CLIENT_CHANGE_QUEUE = "client-change-queue";
}
