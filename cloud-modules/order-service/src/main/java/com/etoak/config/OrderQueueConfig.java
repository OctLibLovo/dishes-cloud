package com.etoak.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 订单延迟交换机队列
 */
@Configuration
public class OrderQueueConfig {
    public static final String EXCHANGE = "order";
    public static final String QUEUE = "order";
    public static final String KEY = "order";

    public static final String X_DELAYED_TYPE = "x-delayed-type";
    public static final String X_DELAYED_MESSAGE = "x-delayed-message";

    @Bean
    public CustomExchange orderExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put(X_DELAYED_TYPE, ExchangeTypes.DIRECT);

        return new CustomExchange(EXCHANGE, X_DELAYED_MESSAGE, Boolean.TRUE, Boolean.FALSE, args);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, CustomExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(KEY).noargs();
    }

}
